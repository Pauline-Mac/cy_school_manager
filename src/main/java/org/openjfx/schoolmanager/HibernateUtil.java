package org.openjfx.schoolmanager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Crée la SessionFactory à partir de la configuration Hibernate
            Configuration cfg = new Configuration();
            cfg.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
            cfg.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"));
            cfg.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));
            cfg.addAnnotatedClass(User.class);
            sessionFactory = cfg.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed" + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
