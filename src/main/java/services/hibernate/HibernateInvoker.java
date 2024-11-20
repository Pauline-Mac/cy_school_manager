package services.hibernate;

import models.HibernateEntity;
import services.hibernate.commands.GetAll;
import services.hibernate.commands.GetAllWhere;
import services.hibernate.commands.HibernateCommand;
import services.hibernate.commands.Get;

import java.util.List;

/*
    Design Pattern Command
    Role : Invoker
*/
public class HibernateInvoker {
    HibernateCommand command;

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
