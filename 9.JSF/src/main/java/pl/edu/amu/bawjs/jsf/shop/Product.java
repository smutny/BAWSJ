package pl.edu.amu.bawjs.jsf.shop;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mbocian on 2016-03-11.
 */
@ManagedBean
@SessionScoped
public class Product implements Serializable
{
    private long id;
    private String description;
    private String name;
    private BigDecimal price;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price)
    {
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public void setProductValues(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName()
    {
        return name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public String getDescription()
    {
        return description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
