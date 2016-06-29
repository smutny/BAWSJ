package pl.edu.amu.bawsj.atmjpa.model;

import pl.edu.amu.bawsj.atmjpa.model.exception.IncorrectCardNumberException;

import java.io.Serializable;

public class Card implements Serializable {
    private static final String CARD_NUMBER_REGEXP = "^([1-9][0-9]{15})$";

    private long number;    //TODO: 16 cyfr

    private PIN pin;

    public Card(long number, PIN pin) {
        this.number = number;
        this.pin = pin;
    }

    public Card(String numberStr, PIN pin) throws IncorrectCardNumberException {
        if (!numberStr.matches(CARD_NUMBER_REGEXP)) {
            throw new IncorrectCardNumberException();
        }
        this.number = Long.parseLong(numberStr);
        this.pin = pin;
    }

    public Card() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(this.getClass().equals(object.getClass()))) {
            return false;
        }
        Card otherCard = (Card) object;
        return getNumber() == otherCard.getNumber()
                && (null == getPin() ? null == otherCard.getPin() : getPin().equals(otherCard.getPin()));
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public PIN getPin() {
        return pin;
    }

    public void setPin(PIN pin) {
        this.pin = pin;
    }
}