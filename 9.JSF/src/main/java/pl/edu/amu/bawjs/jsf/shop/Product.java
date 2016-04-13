package pl.edu.amu.bawjs.jsf.shop;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mbocian on 2016-03-11.
 */
public class Product implements Serializable
{
    private String description;
    private String name;
    private BigDecimal price;

    public Product(String name, String description, BigDecimal price)
    {
        this.description = description;
        this.name = name;
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
}
