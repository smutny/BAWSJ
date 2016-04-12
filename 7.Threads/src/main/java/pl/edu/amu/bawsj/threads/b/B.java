package pl.edu.amu.bawsj.threads.b;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class B
{
    ExecutorService executor = Executors.newFixedThreadPool( 2 );

    public static void main( String[] args ) throws InterruptedException
    {
        new B().start();
    }

    private void start() throws InterruptedException
    {
        List<Future<String>> futures =
            executor.invokeAll( Arrays.asList( new PrintCallable( "A" ), new PrintCallable( "B" ) ) );
        System.out.println( Thread.currentThread().getName() + " Printed already?" );
        futures.stream().forEach( call -> {
            try
            {

                System.out.println( Thread.currentThread().getName() + " " + call.get() );
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
        } );
        System.out.println( Thread.currentThread().getName() + " Printed now?" );
    }

    class PrintCallable implements Callable<String>
    {
        private String text;

        public PrintCallable( String text )
        {
            this.text = text;
        }

        @Override
        public String call() throws Exception
        {
            TimeUnit.SECONDS.sleep( 1 );
            return text;
        }
    }
}
