package com.lcc.basic.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        buildSessionFactory();
    }
    private static void buildSessionFactory() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session openSession() {
        return sessionFactory.getCurrentSession();
//        return sessionFactory.openSession();
    }
    public static void close(Session session) {
        if (session != null) {
            session.close();
        }
    }
}
