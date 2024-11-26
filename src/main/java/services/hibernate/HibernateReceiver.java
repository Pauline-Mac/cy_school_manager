package services.hibernate;

import models.Enrollment;
import models.HibernateEntity;
import models.Student;

import java.util.HashMap;
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

    public List<Enrollment> getEnrollmentByStudent(Student student){
        return client.getEnrollmentByStudent(student);
    }

    public Boolean update(HibernateEntity entity) {
        return client.update(entity);
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
}
