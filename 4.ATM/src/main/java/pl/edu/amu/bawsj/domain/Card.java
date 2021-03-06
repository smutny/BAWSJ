package pl.edu.amu.bawsj.domain;

/**
 * Created by rafal on 4/10/16.
 */
public class Card {
    private String number;
    private int pin;
    private double cashAmount;

    public Card(String number, int pin, double cashAmount) {
        this.number = number;
        this.pin = pin;
        this.cashAmount = cashAmount;
    }

    public String getNumber() {
        return number;
    }

    public int getPin() {
        return pin;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }
}
