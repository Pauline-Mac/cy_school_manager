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
    HibernateInvoker invoker = new HibernateInvoker();

    public Save(HibernateEntity entity) {
        this.entity = entity;
    }


    public Object execute(HibernateEntity entity) {
        invoker.save(entity);
        return null;
    }

    @Override
    public Object execute() {
        return null;
    }
}
