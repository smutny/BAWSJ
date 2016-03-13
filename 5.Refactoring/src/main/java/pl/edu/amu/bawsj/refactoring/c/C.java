package pl.edu.amu.bawsj.refactoring.c;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// znajdź błąd
public class C
{

    public static void main( String[] args ) throws IOException
    {
        main1("Jan", "qwqw");
    }

    public static void main1( String... args ) throws IOException
    {
        InputStream inputStream = C.class.getClassLoader().getResourceAsStream("imieniny.txt");
        BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );

        ImieninyHandler imieninyHandler = new ImieninyHandler(reader);

        for (String arg : args) {
            if (imieninyHandler.isNameInReader(arg))
                System.out.println( arg + " istnieje!" );
            else
                System.out.println( arg + " nie istnieje!" );
        }
    }
}
