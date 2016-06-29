package pl.edu.amu.bawsj.atmjpa.model;

/**
 * Created by rafal on 6/28/16.
 */
public class Withdraw {
    private String number;
    private String pin;
    private int amount;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
