package pl.edu.amu.bawjs.jpa.model;

import java.io.Serializable;

/**
 * Created by rafal on 6/28/16.
 */
public class Balance implements Serializable{
    double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
