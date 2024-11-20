package services.hibernate.commands;

import models.HibernateEntity;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateReceiver;
/*
    Design Pattern Command
    Role : Command
*/
public class Get implements HibernateCommand {
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    Class clazz;
    Integer id;

    public Get(Class clazz, Integer id) {
        this.clazz = clazz;
        this.id = id;
    }

    public HibernateEntity execute() {
        return receiver.get(clazz, id);
    }
}
