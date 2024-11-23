package services.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.HibernateEntity;
import models.Student;
import models.StudentGroup;
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
        config.configure("hibernate.cfg.xml");
        config.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
        config.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"));
        config.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));
        config.addAnnotatedClass(User.class);
        config.addAnnotatedClass(StudentGroup.class);
        config.addAnnotatedClass(Student.class);
        SessionFactory sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public boolean save(HibernateEntity entity){
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
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
}
