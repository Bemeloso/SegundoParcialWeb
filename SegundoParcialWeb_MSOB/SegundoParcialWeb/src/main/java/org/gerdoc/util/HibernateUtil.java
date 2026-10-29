package org.gerdoc.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sessionFactory = new Configuration( )
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory( );
        }
        catch (Throwable e)
        {
            System.err.println( "Error al iniciar Hibernate: " + e.getMessage( ) );
            throw new ExceptionInInitializerError( e );
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
