package services.hibernate;

import models.HibernateEntity;
import models.Student;
import services.hibernate.commands.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/*
    Design Pattern Command
    Role : Invoker
*/
public class HibernateInvoker {
    HibernateCommand command;



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
