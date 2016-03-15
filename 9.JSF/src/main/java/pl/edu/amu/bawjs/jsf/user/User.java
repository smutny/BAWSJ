package pl.edu.amu.bawjs.jsf.user;

import pl.edu.amu.bawjs.jsf.shop.Cart;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by mbocian on 2016-03-11.
 */
@SessionScoped
@ManagedBean
public class User  implements Serializable {
    private String login;

    public User() {
        login = "quest";
    }

    public String getLogin() {
        return login;
    }

}
