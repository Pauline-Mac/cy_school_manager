package services.hibernate;

import models.Enrollment;
import models.HibernateEntity;
import models.Student;
import org.hibernate.query.Query;
import services.hibernate.commands.*;
import java.util.List;

/*
    Design Pattern Command
    Role : Invoker
*/
public class HibernateInvoker {
    HibernateCommand command;

    public List<Enrollment> getEnrollmentByStudent(Student student) {

        return (List<Enrollment>) executeCommand(new GetEnrollmentByStudent(student));
    }

    public Boolean update(HibernateEntity entity) {
        return (Boolean) executeCommand(new Update(entity));
    }

    public Boolean save(HibernateEntity entity) {
        Save saveCommand = new Save(entity);
        return (Boolean) executeCommand(saveCommand);
    }

    public HibernateEntity get(Class clazz, Integer id) {
        return (HibernateEntity) executeCommand(new Get(clazz, id));
    }

    public List<HibernateEntity> getAll(Class clazz) {
        return (List<HibernateEntity>) executeCommand(new GetAll(clazz));
    }

    public List<HibernateEntity> getAllWhere(Class clazz, String attribute, String value) {
        return (List<HibernateEntity>) executeCommand(new GetAllWhere(clazz, attribute, value));
    }

    public Object executeCommand(HibernateCommand command) {
        try {
            return command.execute();
        } catch (Exception e) {
            System.err.println("Hibernate Error:" + command.getClass().getName() + "\n" + e.getMessage());
            return null;
        }
    }
}
