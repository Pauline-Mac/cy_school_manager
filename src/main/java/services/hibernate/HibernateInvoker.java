package services.hibernate;

import models.HibernateEntity;
import models.Student;
import services.hibernate.commands.*;

import java.util.Date;
import java.util.List;

/*
    Design Pattern Command
    Role : Invoker
*/
public class HibernateInvoker {
    HibernateCommand command;


    public Student createStudent(String email, String password, String lastName, String firstName, Date birthDate, String phone, String studentGroupName) {
        Create createCommand = new Create();
        Student student = createCommand.createStudent(email, password, lastName, firstName, birthDate, phone, studentGroupName);
        return student;
    }


    public void saveStudent(Student student) {
        Save saveCommand = new Save(student);
        executeCommand(saveCommand);
    }

    public HibernateFacade get(Class clazz, Integer id) {
        return (HibernateFacade) executeCommand(new Get(clazz, id));
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
