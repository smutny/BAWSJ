package pl.edu.amu.bawsj.refactoring.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

public class B
{
    public static void main( String[] args ) throws IOException, ParseException {
        InputStream inputStream = B.class.getClassLoader().getResourceAsStream( "gold.csv" );
        BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );

        GoldHandler goldHandler = new GoldHandler(reader);
        System.out.println(goldHandler.biggestAverage());
    }
}
