package pl.edu.amu.bawsj.databases;

import pl.edu.amu.bawsj.domain.Card;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by rafal on 4/10/16.
 */
public interface CardDatabase {
    Card findCardByNumber(String cardNumber) throws IOException, ParseException;
    void substractMoneyFromCard(Card card, int amount) throws IOException;
    void addMoneyToCard(Card card, double amount) throws IOException;
}
