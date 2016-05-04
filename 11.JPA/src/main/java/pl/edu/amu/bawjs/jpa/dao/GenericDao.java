package pl.edu.amu.bawjs.jpa.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by mbocian on 2016-05-04.
 */
public abstract class GenericDao<T>
{
    @Inject
    private EntityManager entityManager;
    private Class<T> clazz;

    public GenericDao( Class<T> clazz )
    {
        this.clazz = clazz;
    }

    public void create( T entity )
    {
        entityManager.persist( entity );
    }

    public void remove( T entity )
    {
        entityManager.remove( entity );
    }

    public void update( T entity )
    {
        entityManager.merge( entity );
    }

    public List<T> findAll()
    {
        return (List<T>)entityManager.createNamedQuery( "FROM " + clazz.getSimpleName() ).getResultList();
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }
}
