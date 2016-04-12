package pl.edu.amu.bawjs.jsf.shop;

import java.math.BigDecimal;

/**
 * Created by mbocian on 2016-03-11.
 */
public class Product
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
