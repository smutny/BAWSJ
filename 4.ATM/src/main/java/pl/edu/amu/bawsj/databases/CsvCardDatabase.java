package pl.edu.amu.bawsj.databases;

import pl.edu.amu.bawsj.domain.Card;
import pl.edu.amu.bawsj.exceptions.WrongIdException;
import pl.edu.amu.bawsj.utils.FileHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Pattern;

/**
 * Created by rafal on 4/10/16.
 */
public class CsvCardDatabase implements CardDatabase{
    FileHandler fileHandler;

    public CsvCardDatabase(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public Card findCardByNumber(String cardNumber) throws IOException, ParseException, WrongIdException{
        for (String[] card : fileHandler.getData()) {
            if (checkIfCardHasGivenCardNumber(card, cardNumber))
                return tryToCreateCard(card);
        }
        throw new WrongIdException();
    }

    private boolean checkIfCardHasGivenCardNumber(String[] cardData, String cardNumber) throws ParseException {
        Card card = tryToCreateCard(cardData);
        return card.getNumber().equals(cardNumber);
    }

    private Card tryToCreateCard(String[] cardData) throws ParseException {
        if (isStringDataParsableToCard(cardData)) {
            return new Card(cardData[0], Integer.parseInt(cardData[1]), Double.parseDouble(cardData[2]));
        } else {
            throw new ParseException("Errors in input files", 0);
        }
    }

    private boolean isStringDataParsableToCard(String[] card) {
        return card.length==3 && card[0] != null && card[1] != null && card[2] != null && isParsableToInt(card[1]) && isParsableToDouble(card[2]);
    }

    private boolean isParsableToInt(String stringToCheck) {
        String intPattern = "[0-9]{1,13}";
        return Pattern.matches(intPattern, stringToCheck);
    }

    private boolean isParsableToDouble(String stringToCheck) {
        String doublePattern = "[0-9]{1,13}(\\.[0-9]*)?";
        return Pattern.matches(doublePattern, stringToCheck);
    }

    @Override
    public void substractMoneyFromCard(Card card, int amount) throws IOException {
        double newAmount = card.getCashAmount() - amount;
        fileHandler.replaceTextInFile(card.getNumber()+","+card.getPin()+","+card.getCashAmount(),
                card.getNumber()+","+card.getPin()+","+newAmount);
    }
}
