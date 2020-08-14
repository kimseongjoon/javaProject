package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
     private  static SessionFactory buildSessionFactory() {
         try {
             Configuration configuration = new Configuration();
             configuration.configure("hibernate.cfg.xml");
             return configuration.buildSessionFactory();
         } catch (Throwable e) {
             e.printStackTrace();
             throw new ExceptionInInitializerError(e);
         }
     }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static Session getSession() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory.openSession();
    }
}
