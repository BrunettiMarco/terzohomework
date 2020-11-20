package it.unimol.homework.application.recipes;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Contiene i dati delle singole ricette, è una classe serializzata, che garantisce l'unicità di un
 * ipotetico insieme che conterrà i suoi elementi confrontando esclusivamente il parametro {@code this.title}
 *
 * @author Marco Brunetti
 * @version 1.0
 */
public class Recipe implements  Comparator<Recipe>, Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String ingredients;
    private String preparation;

    private int time;

    /**
     * @param title
     * @param ingredients
     * @param preparation
     * @param time secondi necessari per la preparazione
     *
     * @exception TimeRecipeInvalidException Eccezione generata se viene passata un unità di tempo inferiore a 1
     */
    public Recipe(
            String title,
            String ingredients,
            String preparation,
            int time
    ) throws TimeRecipeInvalidException {
        if(time <= 0)
            throw new TimeRecipeInvalidException();

        this.time = time;
        this.title = title;
        this.ingredients = ingredients;
        this.preparation = preparation;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

    /**
     * @param o1
     * @param o2
     * @return @see java.lang.String {@code compareTo}
     */
    @Override
    public int compare( Recipe o1, Recipe o2 ) {
        return o1.title.compareTo( o2.title );
    }

    /**
     * @param o
     * @return {@code true} se i titoli sono uguali( case sensitive ), {@code false} altrimenti
     */
    @Override
    public boolean equals( Object o ) {
        if ( !( o instanceof Recipe ) )
            return false;

        Recipe anotherElement = ( Recipe ) o;

        return this.title.equals( anotherElement.title );
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public String getPreparation() {
        return this.preparation;
    }

    public int getTime() {
        return  this.time;
    }
}
