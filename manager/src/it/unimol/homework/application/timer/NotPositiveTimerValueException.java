package it.unimol.homework.application.timer;

/**
 * @author Marco Brunetti
 * @version 1.0
 */
public class NotPositiveTimerValueException extends Exception {
    NotPositiveTimerValueException(){
        super( "Il valore tempo passato non è un numero maggiore di 0, non è possibile avviare il timer" );
    }
}
