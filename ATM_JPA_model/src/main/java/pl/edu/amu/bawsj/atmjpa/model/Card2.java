package pl.edu.amu.bawsj.atmjpa.model;

import pl.edu.amu.bawsj.atmjpa.model.exception.IncorrectCardNumberException;

import java.io.Serializable;

public class Card2 implements Serializable {
    private static final String CARD_NUMBER_REGEXP = "^([1-9][0-9]{15})$";

    private long number;

    private PIN pin;

    public Card2(long number, PIN pin) throws IncorrectCardNumberException {
        if (!String.valueOf(number).matches(CARD_NUMBER_REGEXP)) {
            throw new IncorrectCardNumberException();
        }
        this.number = number;
        this.pin = pin;
    }

    public Card2(String numberStr, PIN pin) throws IncorrectCardNumberException {
        if (!numberStr.matches(CARD_NUMBER_REGEXP)) {
            throw new IncorrectCardNumberException();
        }
        this.number = Long.parseLong(numberStr);
        this.pin = pin;
    }

    public Card2() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(this.getClass().equals(object.getClass()))) {
            return false;
        }
        Card2 otherCard = (Card2) object;
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