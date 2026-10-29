package org.gerdoc.dao.impl;

import org.gerdoc.dao.ProfesorMateriaDao;
import org.gerdoc.model.ProfesorMateria;
import org.gerdoc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProfesorMateriaDaoImpl implements ProfesorMateriaDao
{
    private Session session;

    public ProfesorMateriaDaoImpl( )
    {
        session = HibernateUtil
                .getSessionFactory( )
                .openSession( );
    }

    @Override
    public List<ProfesorMateria> findAll()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<ProfesorMateria> criteria =
                builder.createQuery(ProfesorMateria.class);
        criteria.from(ProfesorMateria.class);
        return session
                .createQuery(criteria)
                .getResultList();
    }

    @Override
    public ProfesorMateria findById(Long id)
    {
        return session.get( ProfesorMateria.class, id );
    }

    @Override
    public ProfesorMateria save(ProfesorMateria profesorMateria)
    {
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            session.save( profesorMateria );
            transaction.commit( );
            return profesorMateria;
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
        ProfesorMateria profesorMateria = null;
        try
        {
            profesorMateria = findById( id );
            if( profesorMateria != null )
            {
                transaction = session.beginTransaction();
                session.delete( profesorMateria );
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
    public ProfesorMateria update(ProfesorMateria profesorMateria )
    {
        Transaction transaction = null;
        ProfesorMateria updatedProfesorMateria = null;
        try
        {
            transaction = session.beginTransaction();
            updatedProfesorMateria = (ProfesorMateria) session.merge( profesorMateria );
            transaction.commit( );
            return updatedProfesorMateria;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
            return null;
        }
    }
}
