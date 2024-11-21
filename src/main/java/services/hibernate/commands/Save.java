package services.hibernate.commands;

import models.HibernateEntity;
import services.hibernate.HibernateReceiver;

/*
    Design Pattern Command
    Role : Command
*/
public class Save implements HibernateCommand {

    HibernateEntity entity;

    public Save(HibernateEntity entity) {
        this.entity = entity;
    }

    @Override
    public HibernateEntity execute() {
        try {
            HibernateReceiver.getInstance().client.session.beginTransaction();
            HibernateReceiver.getInstance().client.session.persist(entity);
            HibernateReceiver.getInstance().client.session.getTransaction().commit();
        } catch (Exception e) {
            HibernateReceiver.getInstance().client.session.getTransaction().rollback();
            throw new RuntimeException("Error during save operation", e);
        }
        return entity;
    }

}
