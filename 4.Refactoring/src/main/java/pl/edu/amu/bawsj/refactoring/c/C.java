package pl.edu.amu.bawsj.refactoring.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// znajdź błąd
public class C {

    public static void main(String[] args) throws IOException {
        main1("Jan");
    }

    public static void main1(String... args) throws IOException {
        if (args.length == 0) {
            System.out.println("Nie podałeś imienia!");
            return;
        }

        InputStream inputStream = C.class.getClassLoader().getResourceAsStream("imieniny.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        mainLoop:
        for (int i = 0; i < args.length; i++) {
            boolean found = false;
            while (true) {
                String s = reader.readLine();
                if (s == null) {
                    break;
                }
                if (s.trim().equals(args[i].trim())) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println(args[i] + " istnieje!");
            } else {
                System.out.println(args[i] + " nie istnieje!");
            }
        }
    }
}
