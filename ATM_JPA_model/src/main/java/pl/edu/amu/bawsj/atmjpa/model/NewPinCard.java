package pl.edu.amu.bawsj.atmjpa.model;

/**
 * Created by rafal on 6/28/16.
 */
public class NewPinCard {
    private String number;
    private String pin;
    private String newPin;

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

    public String getNewPin() {
        return newPin;
    }

    public void setNewPin(String newPin) {
        this.newPin = newPin;
    }
}