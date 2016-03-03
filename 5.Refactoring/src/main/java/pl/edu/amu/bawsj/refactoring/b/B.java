package pl.edu.amu.bawsj.refactoring.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class B
{
    public static void main( String[] args ) throws IOException
    {
        InputStream inputStream = B.class.getClassLoader().getResourceAsStream( "gold.csv" );
        BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );

        double avg = Double.MIN_VALUE;

        while( true )
        {
            String s = reader.readLine();
            if( s == null )
            {
                break;
            }
            String[] split = s.split( "," );
            avg = Math.max( avg, (Double.parseDouble( split[ 1 ] ) + Double.parseDouble( split[ 2 ] ) + Double
                .parseDouble( split[ 3 ] )) / 3.0 );
        }
        System.out.println( avg );

    }
}
