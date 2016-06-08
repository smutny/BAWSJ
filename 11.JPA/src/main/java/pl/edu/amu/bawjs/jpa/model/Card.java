package pl.edu.amu.bawjs.jpa.model;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by rafal on 6/7/16.
 */
@Entity
@Table
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String number;

    @Column
    private int pin;

    @ManyToOne
    private Account account;

    public Card() {

    }

    public Card(String number, int pin) {
        this.number = number;
        this.pin = pin;
    }

    public String getNumber() {
        return number;
    }

    public int getPin() {
        return pin;
    }

    @XmlTransient
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
