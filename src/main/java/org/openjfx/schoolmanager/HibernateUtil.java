package org.openjfx.schoolmanager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Crée la SessionFactory à partir de la configuration Hibernate
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed" + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
