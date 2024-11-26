package services.hibernate.commands;
import models.HibernateEntity;
import services.hibernate.HibernateReceiver;


public class Update implements HibernateCommand {
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    HibernateEntity entity;
    public Update(HibernateEntity entity) {
        this.entity = entity;
    }


    @Override
    public Boolean execute() {
        return receiver.update(entity);
    }
}