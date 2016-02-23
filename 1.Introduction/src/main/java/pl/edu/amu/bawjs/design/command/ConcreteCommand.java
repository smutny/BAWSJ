package pl.edu.amu.bawjs.design.command;

/**
 * Created by mbocian on 2016-02-23.
 */
public class ConcreteCommand implements Command
{
    private Receiver receiver;

    public ConcreteCommand( Receiver receiver )
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        receiver.someAction();
    }
}
