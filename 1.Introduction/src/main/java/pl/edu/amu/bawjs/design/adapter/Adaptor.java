package pl.edu.amu.bawjs.design.adapter;

/**
 * Created by mbocian on 2016-02-19.
 */
public class Adaptor
{
    private Adaptee adaptee;

    public Adaptor( Adaptee adaptee )
    {
        this.adaptee = adaptee;
    }

    public void adaptedMethod()
    {
        adaptee.method();
    }

}
