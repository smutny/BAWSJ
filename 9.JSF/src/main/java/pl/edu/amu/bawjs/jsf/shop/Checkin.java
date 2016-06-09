package pl.edu.amu.bawjs.jsf.shop;

/**
 * Created by rafal on 6/8/16.
 */
public class Checkin {
    private String cardNumber;
    private String pin;
    private double amount;

    public Checkin() {}

    public Checkin(String cardNumber, String pin, double amount) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
