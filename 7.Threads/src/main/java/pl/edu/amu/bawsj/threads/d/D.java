package pl.edu.amu.bawsj.threads.d;

import java.util.concurrent.*;

public class D
{
    private static final ExecutorService threadpool = Executors.newFixedThreadPool( 3 );

    public static void main( String args[] ) throws InterruptedException, ExecutionException
    {
        FactorialCalculator task = new FactorialCalculator( 10 );
        System.out.println( "Submitting..." );
        Future future = threadpool.submit( task );
        System.out.println( "Submitted" );
        while( !future.isDone() )
        {
            System.out.println( "Not completed yet...." );
            Thread.sleep( 1 );
            System.out.println( "Task is completed, let's check result" );
            long factorial = (long)future.get();
            System.out.println( "Factorial of 1000000 is : " + factorial );
            threadpool.shutdown();
        }
    }

    private static class FactorialCalculator implements Callable
    {
        private final int number;

        public FactorialCalculator( int number )
        {
            this.number = number;
        }

        @Override
        public Long call()
        {
            long output = 0;
            try
            {
                output = factorial( number );
            }
            catch( InterruptedException ex )
            {
                System.out.println( ex );
            }
            return output;
        }

        private long factorial( int number ) throws InterruptedException
        {
            if( number < 0 )
            {
                throw new IllegalArgumentException();
            }
            long result = 1;
            while( number > 0 )
            {
                Thread.sleep( 1 );
                result = result * number;
                number--;
            }
            return result;
        }
    }
}