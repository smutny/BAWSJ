package pl.edu.amu.bawsj.junit;

public class Add
{
    public int go( int a, int b ) {
        if (b > 0 ? a > Integer.MAX_VALUE - b
                : a < Integer.MIN_VALUE - b) {
            throw new ArithmeticException();
        }
        return a + b;
    }
}
