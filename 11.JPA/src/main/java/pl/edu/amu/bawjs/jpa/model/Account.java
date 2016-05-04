package pl.edu.amu.bawjs.jpa.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mbocian on 2016-05-04.
 */
@Entity
@Table
public class Account implements Serializable
{
    @Id
    @GeneratedValue
    private long id;

    @Column
    private double balance;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance( double balance )
    {
        this.balance = balance;
    }
}
