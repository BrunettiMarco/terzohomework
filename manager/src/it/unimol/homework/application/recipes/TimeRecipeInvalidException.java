package it.unimol.homework.application.recipes;

/**
 * @author Marco Brunetti
 * @version 1.0
 */
public class TimeRecipeInvalidException extends Exception {
    TimeRecipeInvalidException() {
        super( "Valore tempo ricetta non valido" );
    }
}
