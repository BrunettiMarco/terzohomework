package it.unimol.homework.application.recipes;

import it.unimol.homework.application.recipes.persistence.SaveRecipes;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe di gestione delle ricette, la classe è instanziabile una sola una volta,
 * a seguito della prima chiamata di {@code RecipeManager.getInstance()}.
 *
 * All'avvio del programma controlla se vi sono ricette già inserite e le carica in memoria, inoltre salva in
 * automatico dopo ogni inserimento di una nuova ricetta.
 *
 * {{@link #saveStoredRecipes()}} avvia un thread che si occupa di salvare la ricetta, per impedire al programma di
 * fermarsi in fase di scrittura, mentre {{@link #loadStoredRecipes()}} che si occupa del caricamento dei dati agirà
 * esclusivamente all'avvio del programma
 *
 * @author Marco Brunetti
 * @version 1.0
 */
public class RecipesManager {

    private static RecipesManager ourInstance = new RecipesManager();

    public static RecipesManager getInstance() {
        return ourInstance;
    }

    private Set<Recipe> recipeSet;

    private RecipesManager() {
        this.recipeSet = new HashSet<>();

        File file = new File("recipes.sr" );
        if ( file.exists() )
            this.loadStoredRecipes();
    }

    public Set<Recipe> getRecipeSet() {
        return this.recipeSet;
    }

    /**
     * @param recipe
     * @return {@code true} se la ricetta è stata inserita, altrimenti {@code false}
     */
    public boolean addRecipe( Recipe recipe ) {
        assert recipe != null : "La ricetta passata è null";

        boolean isAdded = this.recipeSet.add( recipe );

        return isAdded;
    }

    /**
     * Genera un thread con il compito di salvare i dati dell'insieme {@code this.recipeSet}
     *
     * @throws InterruptedException Se non è stato possibile salvare la nuova ricetta, per maggiori informazioni
     *                              sulla generazione dell'errore:
     *                              @see java.lang.InterruptedException
     */
    public void saveStoredRecipes() throws InterruptedException {
        assert this.recipeSet.size() > 0 : "Salvataggio non necessario: l'insieme è vuoto";

        SaveRecipes saveRecipes = new SaveRecipes( this.recipeSet );
        Thread saveThread = new Thread( saveRecipes );

        saveThread.start();
    }

    /**
     * Valorizza la variabile {@code this.recipeSet} con un tipo {@code Set<Recipe>} prendendo il contenuto da un file.
     * Il file letto dovrà contenere esclusivamente dati dello stesso tipo con impostato {@code static final
     * long serialVersionUID = 1L}
     *
     * La funzione non controlla il tipo di dato inserito e/o se il file è vuoto, può dare problemi se usata in
     * contesti errati da quelli indicati
     */
    @SuppressWarnings("unchecked")
    void loadStoredRecipes() {
        assert new File("recipes.sr" ).exists() : "Il file da caricare non esiste";
        assert this.recipeSet.size() > 0 : "La lista non era vuota al momento del caricamento, gli elementi " +
                                           "sarebbero stati sovrascritti dopo la lettura del file";

        try (
                FileInputStream f = new FileInputStream("recipes.sr" );
                ObjectInputStream o = new ObjectInputStream( f );
        ) {
                this.recipeSet = (Set<Recipe>) o.readObject();
        } catch ( IOException | ClassNotFoundException e ) {
            assert false : e.getMessage();

            JFrame jFrame = new JFrame();

            JOptionPane.showMessageDialog(
                    jFrame,
                    e.getMessage()
            );

            jFrame.dispatchEvent( new WindowEvent( jFrame, WindowEvent.WINDOW_CLOSING ) );
        }
    }
}