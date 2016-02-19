package pl.edu.amu.bawjs.design.component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbocian on 2016-02-19.
 */
public class Composite implements Component
{
    private List<Component> children = new ArrayList<>();

    @Override

    public void operation()
    {
        children.forEach( c -> c.operation() );
    }

    public void add( Component component )
    {
        children.add( component );
    }

    public void remove( Component component )
    {
        children.remove( component );
    }

}
