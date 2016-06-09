package pl.edu.amu.bawjs.jpa.model;

/**
 * Created by rafal on 6/7/16.
 */
public class CheckinMapper {
    private String cardNumber;
    private String pin;
    private double amount;

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
