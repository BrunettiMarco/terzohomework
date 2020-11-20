package it.unimol.homework.application.timer;

import it.unimol.homework.application.converter.SecondsConverter;
import it.unimol.homework.application.recipes.Recipe;
import it.unimol.homework.application.recipes.TimeRecipeInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Classe di Testing di {@link SecondsConverter}.
 * Svolge attività di testing sul valore di output (di tipo String) inerente al tempo necessario per
 * realizzazione della ricetta; confronta le stringhe tramite il metodo {@code #equals}: se il
 * valore di ritorno è {@code 0} il test passa, altrimenti genera un errore
 *
 * @author Marco Brunetti
 * @version 1.0
 */
class SecondsConverterTest {

    @Test
    void testFormatContainingHours () throws TimeRecipeInvalidException {
        Recipe recipe1 = new Recipe(
                "test1",
                "",
                "",
                3600
        );
        Recipe recipe2 = new Recipe(
                "test2",
                "",
                "",
                3660
        );
        Recipe recipe3 = new Recipe(
                "test3",
                "",
                "",
                3601
        );
        Recipe recipe4 = new Recipe(
                "test4",
                "",
                "",
                3661
        );
        Recipe recipe5 = new Recipe(
                "test5",
                "",
                "",
                10800
        );
        Recipe recipe6 = new Recipe(
                "test6",
                "",
                "",
                10860
        );
        Recipe recipe7 = new Recipe(
                "test7",
                "",
                "",
                10980
        );
        Recipe recipe8 = new Recipe(
                "test8",
                "",
                "",
                10861
        );
        Recipe recipe9 = new Recipe(
                "test8",
                "",
                "",
                10983
        );

        String secondsConverterStringValueComparison;
        SecondsConverter secondsConverter;

        secondsConverterStringValueComparison = "1 ora";
        secondsConverter = new SecondsConverter( recipe1 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );

        secondsConverterStringValueComparison = "1 ora, 1 minuto";
        secondsConverter = new SecondsConverter( recipe2 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );

        secondsConverterStringValueComparison = "1 ora, 1 secondo";
        secondsConverter = new SecondsConverter( recipe3 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );

        secondsConverterStringValueComparison = "1 ora, 1 minuto, 1 secondo";
        secondsConverter = new SecondsConverter( recipe4 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );

        secondsConverterStringValueComparison = "3 ore";
        secondsConverter = new SecondsConverter( recipe5 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );

        secondsConverterStringValueComparison = "3 ore, 1 minuto";
        secondsConverter = new SecondsConverter( recipe6 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );

        secondsConverterStringValueComparison = "3 ore, 3 minuti";
        secondsConverter = new SecondsConverter( recipe7 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );

        secondsConverterStringValueComparison = "3 ore, 1 minuto, 1 secondo";
        secondsConverter = new SecondsConverter( recipe8 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );

        secondsConverterStringValueComparison = "3 ore, 3 minuti, 3 secondi";
        secondsConverter = new SecondsConverter( recipe9 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "Valore di ritorno errato" );
    }

    @Test
    void testFormatContainingMinutes () throws TimeRecipeInvalidException {
        Recipe recipe1 = new Recipe(
                "test1",
                "",
                "",
                60
        );
        Recipe recipe2 = new Recipe(
                "test2",
                "",
                "",
                180
        );
        Recipe recipe3 = new Recipe(
                "test3",
                "",
                "",
                61
        );
        Recipe recipe4 = new Recipe(
                "test4",
                "",
                "",
                63
        );


        String secondsConverterStringValueComparison;
        SecondsConverter secondsConverter;

        secondsConverterStringValueComparison = "1 minuto";
        secondsConverter = new SecondsConverter( recipe1 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail("I valori delle stringhe non corrispondono");

        secondsConverterStringValueComparison = "3 minuti";
        secondsConverter = new SecondsConverter( recipe2 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail("I valori delle stringhe non corrispondono");

        secondsConverterStringValueComparison = "1 minuto, 1 secondo";
        secondsConverter = new SecondsConverter( recipe3 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail("I valori delle stringhe non corrispondono");

        secondsConverterStringValueComparison = "1 minuto, 3 secondi";
        secondsConverter = new SecondsConverter( recipe4 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail("I valori delle stringhe non corrispondono");
    }

    @Test
    void testFormatContainingSeconds () throws TimeRecipeInvalidException {
        Recipe recipe1 = new Recipe(
                "test1",
                "",
                "",
                1
        );
        Recipe recipe2 = new Recipe(
                "test2",
                "",
                "",
                3
        );

        String secondsConverterStringValueComparison;
        SecondsConverter secondsConverter;

        secondsConverterStringValueComparison = "1 secondo";
        secondsConverter = new SecondsConverter( recipe1 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "I valori delle stringhe non corrispondono" );

        secondsConverterStringValueComparison = "3 secondi";
        secondsConverter = new SecondsConverter( recipe2 );
        if ( !secondsConverterStringValueComparison.equals( secondsConverter.getTimeFormat() ) )
            fail( "I valori delle stringhe non corrispondono" );
    }
}