package pl.edu.amu.bawsj.atmjpa.model;

import pl.edu.amu.bawsj.atmjpa.model.exception.IncorrectPINException;

import java.io.Serializable;

public class PIN implements Serializable {
    private static final int NUMBER_OF_DIGITS = 4;

    private static final String PIN_REGEXP = "^([0-9]{" + NUMBER_OF_DIGITS + "})$";

    private long id;

    public PIN(byte[] digits) throws IncorrectPINException {
        if (digits.length != NUMBER_OF_DIGITS) {
            throw new IncorrectPINException();
        }
        for (int digit : digits) {
            if (digit < 0 || digit > 9) {
                throw new IncorrectPINException();
            }
        }
        this.digits = digits;
    }

    public PIN(String pinDigitsStr) throws IncorrectPINException {
        if (!pinDigitsStr.matches(PIN_REGEXP)) {
            throw new IncorrectPINException();
        }
        byte[] tmpDigits = new byte[NUMBER_OF_DIGITS];
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            tmpDigits[i] = Byte.parseByte(pinDigitsStr.substring(i, i + 1));
        }
        this.digits = tmpDigits;
    }

    public PIN() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(this.getClass().equals(object.getClass()))) {
            return false;
        }
        PIN otherPIN = (PIN) object;
        return null == getDigits() ? null == otherPIN.getDigits() : isDigitsArrayEquals(otherPIN.getDigits());
    }

    public String digitsToString() {
        String str = "";
        for (byte digit : digits) {
            str += String.valueOf(digit);
        }
        return str;
    }

    private boolean isDigitsArrayEquals(byte[] otherDigits) {
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != otherDigits[i]) {
                return false;
            }
        }
        return true;
    }

    private byte[] digits;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getDigits() {
        return digits;
    }

    public void setDigits(byte[] digits) {
        this.digits = digits;
    }
}
