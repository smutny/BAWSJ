package pl.edu.amu.bawjs.jpa.model;

/**
 * Created by rafal on 6/28/16.
 */
public class Withdraw {
    private String number;
    private String pin;
    private double amount;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
