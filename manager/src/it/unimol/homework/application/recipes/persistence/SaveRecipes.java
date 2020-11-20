package it.unimol.homework.application.recipes.persistence;

import it.unimol.homework.application.recipes.Recipe;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Set;

/**
 *  Garantisce la persistenza delle nuove ricette inserite, salva su un file una copia dell'oggetto {@code Set<Recipe>}.
 *
 *  Deve essere lanciata su un thread diverso da quello principale, vedi {@link Thread} per sapere come inizializzare
 *  l'oggetto.
 *
 *  In caso di fallimento notifica tramite un messaggio all'utente l'effettiva impossibilità di salvare lo stato
 *  attuale del programma
 *
 * @author Marco Brunetti
 * @version 1.0
 */
public class SaveRecipes implements Runnable {

    private Set<Recipe> recipeSet;

    public SaveRecipes(Set<Recipe> recipeSet) {
        assert recipeSet != null : "recipeSet non può essere null";

        this.recipeSet = recipeSet;
    }

    @Override
    public void run() {
        assert recipeSet.size() > 0 : "Non è presente alcun elemento da salvare, questa funzione non viene mai " +
                                      "chiamata quando ha 0 elementi";

        this.saveIntoFile();
    }

    private void saveIntoFile() {
        try (
                FileOutputStream f = new FileOutputStream("recipes.sr" );
                ObjectOutputStream o = new ObjectOutputStream( f );
        ) {
                o.writeObject( this.recipeSet );
        } catch ( IOException e ) {
            assert false : e.getMessage();

            JFrame jFrame = new JFrame();

            JOptionPane.showMessageDialog(
                    jFrame,
                    e.getMessage()
            );

            jFrame.dispatchEvent( new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING ) );
        }
    }
}
