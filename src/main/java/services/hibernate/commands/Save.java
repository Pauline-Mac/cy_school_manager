package services.hibernate.commands;

import models.HibernateEntity;
import services.hibernate.HibernateInvoker;
import services.hibernate.HibernateReceiver;

/*
    Design Pattern Command
    Role : Command
*/
public class Save implements HibernateCommand {

    HibernateEntity entity;
    HibernateReceiver receiver = HibernateReceiver.getInstance();

    public Save(HibernateEntity entity) {
        this.entity = entity;
    }


    public Object execute(HibernateEntity entity) {
        receiver.save(entity);
        return null;
    }

    @Override
    public Boolean execute() {
        return receiver.save(entity);
    }
}
