package services.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateAuthClient {
    public Session session;
    public HibernateAuthClient() {
        try {
            Configuration config = new Configuration();

            config.configure("hibernate.cfg.xml");

            config.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
            config.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"));
            config.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));

            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(StudentGroup.class);
            config.addAnnotatedClass(Student.class);
            config.addAnnotatedClass(Professor.class);
            config.addAnnotatedClass(Note.class);
            config.addAnnotatedClass(Enrollment.class);
            config.addAnnotatedClass(Course.class);

            System.out.println("Hibernate configuration loaded.");

            SessionFactory sessionFactory = config.buildSessionFactory();

            session = sessionFactory.openSession();

            System.out.println("Hibernate session opened.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error initializing Hibernate: " + e.getMessage());
        }
    }

    public List<Student> searchStudentByCriteria(String firstname, String lastname, String studentgroupname, String classname) {
        String hql = "SELECT s FROM Student s WHERE 1=1";

        if (firstname != null && !firstname.isEmpty()) {
            hql += " AND LOWER(s.firstName) LIKE :firstname";
        }
        if (lastname != null && !lastname.isEmpty()) {
            hql += " AND LOWER(s.lastName) LIKE :lastname";
        }
        if (studentgroupname != null && !studentgroupname.isEmpty()) {
            hql += " AND LOWER(s.studentGroup.studentGroupName) LIKE :studentgroupname";
        }
        if (classname != null && !classname.isEmpty()) {
            hql += " AND EXISTS (" +
                    "   SELECT e FROM Enrollment e " +
                    "   JOIN e.course c " +
                    "   WHERE e.student = s AND LOWER(c.className) LIKE :classname" +
                    ")";
        }

        Query<Student> query = session.createQuery(hql, Student.class);

        if (firstname != null && !firstname.isEmpty()) {
            query.setParameter("firstname", firstname.toLowerCase() + "%");
        }
        if (lastname != null && !lastname.isEmpty()) {
            query.setParameter("lastname", lastname.toLowerCase() + "%");
        }
        if (studentgroupname != null && !studentgroupname.isEmpty()) {
            query.setParameter("studentgroupname", studentgroupname.toLowerCase() + "%");
        }
        if (classname != null && !classname.isEmpty()) {
            query.setParameter("classname", classname.toLowerCase() + "%");
        }

        return query.list();
    }





    public Long getCountStudentByClass(Course course) {
        String hql = "SELECT COUNT(e.student.id) " +
                "FROM Enrollment e " +
                "WHERE e.course.id = :courseId";

        Query<Long> query = session.createQuery(hql, Long.class);

        query.setParameter("courseId", course.getClassId());

        return query.uniqueResult();
    }

    public List<Course> getClassesByProfessor(Professor professor) {
        String hql = "SELECT c FROM Course c " +
                "WHERE c.professor.id = :professorId";

        Query<Course> query = session.createQuery(hql, Course.class);

        query.setParameter("professorId", professor.getUserId());

        return query.list();
    }


    public List<Note> getNotesByProfessor(Professor professor) {
        String hql = "SELECT n FROM Note n " +
                "JOIN n.enrollment e " +
                "JOIN e.course c " +
                "WHERE c.professor.id = :professorId";

        Query<Note> query = session.createQuery(hql, Note.class);

        query.setParameter("professorId", professor.getUserId());

        return query.list();
    }

    public List<Enrollment> getEnrollmentByStudent(Student student) {
        String hql = "FROM Enrollment e WHERE e.student = :student";

        Query<Enrollment> query = session.createQuery(hql, Enrollment.class);

        query.setParameter("student", student);

        return query.list();
    }

    public Boolean update(HibernateEntity entity) {
        try {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error during update operation", e);
        }
        return true;
    }

    public boolean save(HibernateEntity entity){
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
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
