package services.hibernate.commands;

import models.HibernateEntity;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateReceiver;

import java.util.List;

/*
    Design Pattern Command
    Role : Command
*/
public class GetAll implements HibernateCommand {
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    Class clazz;
    Integer id;

    public GetAll(Class clazz) {
        this.clazz = clazz;
        this.id = id;
    }

    public List<HibernateEntity> execute() {
        return receiver.getAll(clazz);
    }
}
