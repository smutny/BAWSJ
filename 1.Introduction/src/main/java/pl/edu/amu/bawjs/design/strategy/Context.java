package pl.edu.amu.bawjs.design.strategy;

/**
 * Created by mbocian on 2016-02-23.
 */
public class Context
{
    private Strategy strategy;

    public void setStrategy( Strategy strategy )
    {
        this.strategy = strategy;
    }

    public void doWithStratedy()
    {
        this.strategy.execute();
    }

}
