package it.unimol.homework.application.converter;

import it.unimol.homework.application.recipes.Recipe;

/**
 * Converte il valore tempo passato ( accetta solo i secondi ) in 3 possibili formati:
 * 1. ore - minuti - secondi
 * 2. minuti - secondi
 * 3. secondi
 * La funzione sceglie in automatico il tipo di output ottimale, il che comporta l'impossibilità di scegliere un
 * output specifico, inoltre se un parametro è pari a 0 non verrà stampato, vedere esempi di uso riportati in seguito.
 *
 * Se le ore sono pari o superiori a 24 la classe continuerà a restituire una stringa contenente il dato passato in
 * ore, non tenendo conto di giorni, mesi e anni.
 *
 * Esempio uso corretto: {@code SecondsConverter seconds = new SecondsConverter( 62 ); } ritornerà "1 minuto,
 * 2 secondi"
 *
 *1  Esempio uso corretto: {@code SecondsConverter seconds = new SecondsConverter( 3601 ); } ritornerà "1 ora, 1
 *  secondo"
 *
 * Esempio uso scorretto: {@code SecondsConverter seconds = new SecondsConverter( 93600 ); } ritornerà "26 ore" e non
 * "1 giorno, 2 ore" come ci si potrebbe aspettare
 *
 * @author Marco Brunetti
 * @version 1.0
 */
public class SecondsConverter {

    private int seconds;

    /**
     * @param recipe
     */
    public SecondsConverter( Recipe recipe ) {
        assert recipe != null : "La ricetta ha valore \"null\"";
        assert recipe.getTime() > 0 : "Non è possibile che i secondi necessari siano minori di 1";

        this.seconds = recipe.getTime();
    }

    /**
     * @return  stringa in formato "x ore, y minuti, z secondi", può variare in base al valore
     *          di {@code this.seconds}:
     *          @see SecondsConverter la documentazione all'inizio della classe fornisce dati aggiuntivi sul
     *          contenuto del valore di ritorno
     */
    public String getTimeFormat() {
        String outputTimeFormatString;
        if ( this.seconds >= 3600 ) {
            outputTimeFormatString = this.getFormatWithHours();
        } else if ( this.seconds >= 60 ) {
            outputTimeFormatString = this.getFormatWithMinutes();
        } else {
            outputTimeFormatString = this.getFormatWithSeconds();
        }

        return outputTimeFormatString;
    }

    private String getFormatWithHours() {
        assert this.seconds >= 3600 : "Funzione richiamata in un contesto errato";

        int hours = 0;
        int minutes = 0;

        while ( this.seconds >= 3600 ) {
            this.seconds -= 3600;
            hours++;
        }

        while ( this.seconds >= 60 ) {
            this.seconds -= 60;
            minutes++;
        }

        StringBuilder stringBuilderTimeTotal = new StringBuilder();
        stringBuilderTimeTotal.append( chooseBetweenSingularAndPlural(
                hours,
                " ora",
                " ore"
                )
        );
        if( minutes > 0 ) {
            stringBuilderTimeTotal.append( ", " );
            stringBuilderTimeTotal.append(chooseBetweenSingularAndPlural(
                    minutes,
                    " minuto",
                    " minuti"
                    )
            );
        }
        if( this.seconds > 0) {
            stringBuilderTimeTotal.append( ", " );
            stringBuilderTimeTotal.append(chooseBetweenSingularAndPlural(
                    this.seconds,
                    " secondo",
                    " secondi"
                    )
            );
        }

        return stringBuilderTimeTotal.toString();
    }

    private String getFormatWithMinutes() {
        assert this.seconds >= 60 : "Funzione richiamata in un contesto errato";

        int minutes = 0;

        while ( this.seconds >= 60 ) {
            this.seconds -= 60;
            minutes++;
        }

        StringBuilder stringBuilderTimeTotal = new StringBuilder();
        stringBuilderTimeTotal.append( chooseBetweenSingularAndPlural(
                minutes,
                " minuto",
                " minuti"
                )
        );
        if ( this.seconds > 0 ) {
            stringBuilderTimeTotal.append( ", " );
            stringBuilderTimeTotal.append( chooseBetweenSingularAndPlural(
                    this.seconds,
                    " secondo",
                    " secondi"
                    )
            );
        }

        return stringBuilderTimeTotal.toString();
    }

    private String getFormatWithSeconds() {
        assert this.seconds < 60 && this.seconds > 0 : "Funzione richiamata in un contesto errato";

        StringBuilder stringBuilderTimeTotal = new StringBuilder();
        stringBuilderTimeTotal.append( chooseBetweenSingularAndPlural(
                this.seconds,
                " secondo",
                " secondi"
                )
        );

        return stringBuilderTimeTotal.toString();
    }

    private String chooseBetweenSingularAndPlural( int integerValue, String singolarString, String pluralString ) {
        assert integerValue > 0 : "valore variabile \"integerValue\" errato";

        StringBuilder singolarOrPluralString = new StringBuilder();
        singolarOrPluralString.append( integerValue );
        if ( integerValue == 1 ) {
            singolarOrPluralString.append( singolarString );
        } else {
            singolarOrPluralString.append( pluralString );
        }

        return singolarOrPluralString.toString();
    }
}
