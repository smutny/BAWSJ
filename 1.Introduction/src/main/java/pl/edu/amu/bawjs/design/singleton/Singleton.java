package pl.edu.amu.bawjs.design.singleton;

/**
 * Created by mbocian on 2016-02-19.
 */
public class Singleton
{
    private static Singleton INSTANCE;

    private Singleton()
    {
    }

    public static Singleton getInstance()
    {
        if( INSTANCE == null )
        {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }

    public void method()
    {

    }
}
