package org.gerdoc.dao.impl;

import org.gerdoc.dao.ProfesorDao;
import org.gerdoc.model.Profesor;
import org.gerdoc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProfesorDaoImpl implements ProfesorDao
{
    private Session session;

    public ProfesorDaoImpl( )
    {
        session = HibernateUtil
                .getSessionFactory( )
                .openSession( );
    }

    @Override
    public List<Profesor> findAll()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Profesor> criteria =
                builder.createQuery(Profesor.class);
        criteria.from(Profesor.class);
        return session
                .createQuery(criteria)
                .getResultList();
    }

    @Override
    public Profesor findById(Long id)
    {
        return session.get( Profesor.class, id );
    }

    @Override
    public Profesor save(Profesor profesor)
    {
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            session.save( profesor );
            transaction.commit( );
            return profesor;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
            return null;
        }
    }

    @Override
    public void deleteById(Long id)
    {
        Transaction transaction = null;
        Profesor profesor = null;
        try
        {
            profesor = findById( id );
            if( profesor != null )
            {
                transaction = session.beginTransaction();
                session.delete( profesor );
                transaction.commit( );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
        }
    }

    @Override
    public Profesor update(Profesor profesor)
    {
        Transaction transaction = null;
        Profesor updatedProfesor = null;
        try
        {
            transaction = session.beginTransaction();
            updatedProfesor = (Profesor) session.merge( profesor );
            transaction.commit( );
            return updatedProfesor;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
            return null;
        }
    }
}
