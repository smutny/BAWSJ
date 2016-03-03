package pl.edu.amu.bawsj.threads.c;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class C
{
    ExecutorService executor = Executors.newFixedThreadPool( 2 );

    public static void main( String[] args ) throws InterruptedException
    {
        new C().start();
    }

    private void start() throws InterruptedException
    {
        System.out.println( Thread.currentThread().getName() + " Printed already?" );
        List<PrintCallable> printCallables =
            Arrays.asList( new PrintCallable( "A" ), new PrintCallable( "B" ) );
        printCallables.forEach( printCallable -> {
            try
            {
                executor.execute( printCallable );
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
        } );
        System.out.println( Thread.currentThread().getName() + " Printed now?" );

    }

    class PrintCallable implements Runnable
    {
        private String text;

        public PrintCallable( String text )
        {
            this.text = text;
        }

        @Override
        public void run()
        {
            try
            {
                TimeUnit.SECONDS.sleep( 1 );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            System.out.println( Thread.currentThread().getName() + " " + text );
        }
    }
}
