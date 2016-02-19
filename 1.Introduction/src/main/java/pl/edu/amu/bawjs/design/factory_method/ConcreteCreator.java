package pl.edu.amu.bawjs.design.factory_method;

/**
 * Created by mbocian on 2016-02-19.
 */
public class ConcreteCreator implements Creator
{

    @Override
    public Product factoryMethod()
    {
        return new ConcreteProduct();
    }

    private class ConcreteProduct implements Product
    {
    }
}
