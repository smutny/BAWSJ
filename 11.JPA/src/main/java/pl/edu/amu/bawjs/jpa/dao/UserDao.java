package pl.edu.amu.bawjs.jpa.dao;

import pl.edu.amu.bawjs.jpa.model.User;

import javax.ejb.Stateless;

/**
 * Created by mbocian on 2016-05-04.
 */
@Stateless
public class UserDao extends GenericDao<User>
{
    public UserDao()
    {
        super( User.class );
    }
}
