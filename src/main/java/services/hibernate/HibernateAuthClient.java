package services.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.HibernateEntity;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateAuthClient {
    public Session session;
    public HibernateAuthClient() {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
        config.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"));
        config.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));
        config.addAnnotatedClass(User.class);
        SessionFactory sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public HibernateEntity get(Class clazz, Integer id) {
        return (HibernateEntity) session.get(clazz, id);
    }

    public List<HibernateEntity> getAll(Class clazz) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<HibernateEntity> cr = cb.createQuery(clazz);
        Root<HibernateEntity> root = cr.from(clazz);
        cr.select(root);

        Query<HibernateEntity> query = session.createQuery(cr);
        return query.getResultList();
    }

    public List<HibernateEntity> getAllWhere(Class clazz, String attribute, String value) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<HibernateEntity> cr = cb.createQuery(clazz);
        Root<HibernateEntity> root = cr.from(clazz);

        cr.select(root).where(cb.like(root.get(attribute), value));
        Query<HibernateEntity> query = session.createQuery(cr);
        return query.getResultList();
    }


    public Boolean update(HibernateEntity entity) {

//
//        Transaction tx = session.beginTransaction();
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaUpdate<HibernateEntity> cu = cb.createCriteriaUpdate(class_);
//        Root<HibernateEntity> root = cu.from(class_);
//
//        cu.set(root.get(attribute), value);
//
//        cu.where(cb.equal(root.get(attribute), value));
//
//
//        int affectedRows = session.createQuery(cu).executeUpdate();

        try {
            Transaction tx = session.beginTransaction();
            session.persist(entity);

            tx.commit();
        } catch (Exception e) {

            throw new RuntimeException("Error during update operation", e);
        }

        return true;


    }
}
