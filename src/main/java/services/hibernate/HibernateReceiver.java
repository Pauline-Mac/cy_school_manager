package services.hibernate;

import models.*;

import java.util.List;

/*
    Design Pattern Command
    Role : Receiver
*/
public class HibernateReceiver {
    private static HibernateReceiver instance;
    public HibernateAuthClient client;

    private HibernateReceiver() {
        this.client = new HibernateAuthClient();
    }

    public static HibernateReceiver getInstance() {
        if (instance == null) {
            instance = new HibernateReceiver();
        }
        return instance;
    }

    public Long getCountStudentByClass(Course course){return client.getCountStudentByClass(course);}

    public List<Course> getClassesByProfessor(Professor professor){return client.getClassesByProfessor(professor);}

    public List<Note> getNotesByProfessor(Professor professor){return client.getNotesByProfessor(professor);}

    public List<Enrollment> getEnrollmentByStudent(Student student){
        return client.getEnrollmentByStudent(student);
    }

    public boolean save(HibernateEntity entity) {
        return client.save(entity);
    }

    public HibernateEntity get(Class clazz, Integer id) {
        return client.get(clazz, id);
    }

    public List<HibernateEntity> getAll(Class clazz) {
        return client.getAll(clazz);
    }

    public List<HibernateEntity> getAllWhere(Class clazz, String attribute, String value) {
        return client.getAllWhere(clazz, attribute, value);
    }

    public Boolean update(HibernateEntity entity) {

        return client.update(entity);
    }
}
