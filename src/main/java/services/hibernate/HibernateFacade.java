package services.hibernate;

import models.*;

import java.util.List;

/*
    Design Pattern Facade
    Design Pattern Singleton
    Design Pattern Command
    Role: Client
*/
public class HibernateFacade {
    private static HibernateFacade instance;
    public HibernateInvoker hibernate;

    private HibernateFacade() {
        this.hibernate = new HibernateInvoker();
    }

    public static HibernateFacade getInstance() {
        if (instance == null) {
            instance = new HibernateFacade();
        }
        return instance;
    }

    public List<Student> searchStudentByCriteria(String firstname, String lastname, String studentgroupname, String classname) {
        return hibernate.searchStudentByCriteria(firstname, lastname, studentgroupname, classname);
    }

    public Long getCountStudentByClass(Course course) {
        return hibernate.getCountStudentByClass(course);
    }

    public List<Course> getClassesByProfessor(Professor professor) {
        return hibernate.getClassesByProfessor(professor);
    }

    public List<Note> getNotesByProfessor(Professor professor) {
        return hibernate.getNotesByProfessor(professor);
    }

    public List<Enrollment> getEnrollmentsByStudent(Student student) {
       return hibernate.getEnrollmentByStudent(student);
    }


    public boolean save(HibernateEntity entity) {
        return hibernate.save(entity);
    }

    public HibernateEntity get(Class clazz, Integer id) {
        return hibernate.get(clazz, id);
    }

    public List<HibernateEntity> getAll(Class clazz) {
        return hibernate.getAll(clazz);
    }

    public List<HibernateEntity> getAllWhere(Class clazz, String attribute, String value) {
        return hibernate.getAllWhere(clazz, attribute, value);
    }


    public boolean update(HibernateEntity entity) {
        return hibernate.update(entity);
    }

}
