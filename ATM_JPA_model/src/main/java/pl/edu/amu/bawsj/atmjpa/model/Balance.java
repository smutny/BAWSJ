package pl.edu.amu.bawsj.atmjpa.model;

import java.io.Serializable;

/**
 * Created by rafal on 6/28/16.
 */
public class Balance implements Serializable {
    double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
