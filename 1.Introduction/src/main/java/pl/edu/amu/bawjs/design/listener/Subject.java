package pl.edu.amu.bawjs.design.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbocian on 2016-02-19.
 */
public class Subject
{
    List<Listener> listeners = new ArrayList<>();

    private void informListeners()
    {
        listeners.stream().forEach( l -> l.inform() );
    }

    public void addListener( Listener listener )
    {
        listeners.add( listener );
    }

    public void removeListener( Listener listener )
    {
        listeners.remove( listener );
    }
}
