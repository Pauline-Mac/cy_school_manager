package org.openjfx.schoolmanager;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserCrud {
    public User FindByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE email = :email";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }
}
