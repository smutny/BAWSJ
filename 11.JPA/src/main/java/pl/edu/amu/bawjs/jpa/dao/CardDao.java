package pl.edu.amu.bawjs.jpa.dao;

import pl.edu.amu.bawjs.jpa.model.Card;

/**
 * Created by rafal on 6/7/16.
 */
public class CardDao extends GenericDao<Card>{
    public CardDao() {
        super(Card.class);
    }
}
