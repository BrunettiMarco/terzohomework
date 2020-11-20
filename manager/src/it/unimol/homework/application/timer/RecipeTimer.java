package it.unimol.homework.application.timer;

import it.unimol.homework.application.converter.SecondsConverter;
import it.unimol.homework.application.recipes.Recipe;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * Avvia un timer con un valore pari a {@code recipe.getTime()}, il quale viene considerato sempre e solo come se
 * rappresentasse dei secondi
 *
 * @author Marco Brunetti
 * @version 1.0
 */
public class RecipeTimer implements Runnable {

    private int secondsRemaining;

    private Recipe recipe;

    /**
     * @param recipe
     *
     * @throws NotPositiveTimerValueException Se {@code recipe.getTime()} ritorna un valore inferiore a 1, in quanto
     *                                        non è possibile iniziare il conto alla rovescia
     */
    public RecipeTimer( Recipe recipe ) throws NotPositiveTimerValueException {
        assert recipe != null : "La ricetta passata è null";

        if ( recipe.getTime() < 1 )
            throw new NotPositiveTimerValueException();

        this.recipe = recipe;
        this.secondsRemaining = this.recipe.getTime();
    }

    /**
     * Fà partire il conto alla rovescia che servirà per vedere l'effettivo scorrere del tempo
     *
     * Se lanciato con {@code recipeTimer.start()} il codice viene eseguito nel nuovo thread, se lanciato con
     * {@code recipeTimer.run()} verrà eseguito nel thread in uso ( generalmente il main Thread ).
     *
     * Quando {@code this.secondsRemaining} è equivalente a 0 viene lanciato un messaggio che avvisa che il tempo di
     * esecuzione della ricetta è scaduto, successivamente il thread viene chiuso
     */
    @Override
    public void run() {
        while ( this.secondsRemaining > 0 ) {
            try {
                Thread.sleep(1000 );

                this.secondsRemaining--;
                if ( this.secondsRemaining == 0 ) {
                    SecondsConverter secondsConverter = new SecondsConverter( this.recipe );

                    String message =  "Il tempo per la preparazione della ricetta \"" +
                                      this.recipe.getTitle()                          +
                                      "\" ("                                          +
                                      secondsConverter.getTimeFormat()                +
                                      ") è terminato.";

                    launchMessage( message );
                }
            } catch ( InterruptedException e ) {
                launchMessage( e.getMessage() );

                break;
            }
        }
    }

    private void launchMessage( String message ) {
        JFrame jFrame = new JFrame();

        JOptionPane.showMessageDialog(
                jFrame,
                message
        );

        jFrame.dispatchEvent( new WindowEvent( jFrame, WindowEvent.WINDOW_CLOSING ) );
    }
}