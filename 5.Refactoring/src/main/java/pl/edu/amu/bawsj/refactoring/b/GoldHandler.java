package pl.edu.amu.bawsj.refactoring.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by rafal on 3/13/16.
 */
public class GoldHandler {
    List<Gold> goldList = new ArrayList<Gold>();

    public GoldHandler(BufferedReader reader) throws IOException, ParseException {
        if (reader == null)
            throw new IllegalArgumentException();

        parseGoldData(reader);
    }

    private void parseGoldData(BufferedReader reader) throws IOException, ParseException {

        String line;
        while((line = reader.readLine()) != null)
        {
            String[] split = line.split( "," );
            Gold gold = createGoldFromSplittedInfo(split);
            goldList.add(gold);
        }
    }

    private Gold createGoldFromSplittedInfo(String[] split) throws ParseException {
        Gold gold = new Gold();
        //TODO
        gold.setDate(parseDate(split[0], "dd-MMM-yyyy"));
        gold.setData1(Double.parseDouble(split[1]));
        gold.setData2(Double.parseDouble(split[2]));
        gold.setData3(Double.parseDouble(split[3]));
        gold.setData4(Double.parseDouble(split[5]));
        gold.setData5(Double.parseDouble(split[6]));
        gold.setData6(Double.parseDouble(split[7]));
        return gold;
    }

    private Date parseDate(String date, String format) throws ParseException {
        // tylko  tej jednej rzeczy brakowalo.
        return new SimpleDateFormat(format, Locale.ENGLISH).parse(date);
    }

    public Double biggestAverage() {
        Double average = Double.MIN_VALUE;

        for (Gold gold : goldList) {
            average = Math.max(average, countAverageForGold(gold));
        }
        return average;
    }

    private Double countAverageForGold(Gold gold) {
        Double average = (gold.getData1() + gold.getData2() + gold.getData3()) / 3.0;
        return average;
    }
}
