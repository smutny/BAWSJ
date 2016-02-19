package pl.edu.amu.bawjs.design.decorator;

/**
 * Created by mbocian on 2016-02-19.
 */
public class Decorator implements Component
{
    private final Component component;

    public Decorator( Component component )
    {
        this.component = component;
    }

    public void operation()
    {
        // decorate
        component.operation();
        // decorate
    }
}
