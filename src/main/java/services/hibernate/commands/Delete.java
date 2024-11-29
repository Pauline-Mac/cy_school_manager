package services.hibernate.commands;

import models.HibernateEntity;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;
import services.hibernate.HibernateReceiver;
/*
    Design Pattern Command
    Role : Command
*/
public class Delete implements HibernateCommand {
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    HibernateEntity entity;

    public Delete(HibernateEntity entity) {
        this.entity = entity;
    }

    public Boolean execute() {
        return receiver.delete(entity);
    }
}
