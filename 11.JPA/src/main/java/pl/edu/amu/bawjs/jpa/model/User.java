package pl.edu.amu.bawjs.jpa.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mbocian on 2016-05-04.
 */
@Entity
@Table
public class User implements Serializable
{
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    public User()
    {

    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }
}
