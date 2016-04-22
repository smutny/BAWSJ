package pl.edu.amu.bawsj.exceptions;

/**
 * Created by rafal on 3/29/16.
 */
// dlaczego wszystkie wyjatki dziedzicza po exception, a akurat ten jest runtime? Jak go chcesz potem obsluzyc bez pomocy kompilatora?
public class WrongIdException extends RuntimeException {
}
