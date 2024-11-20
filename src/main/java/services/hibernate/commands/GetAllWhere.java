package services.hibernate.commands;

import models.HibernateEntity;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateReceiver;

import java.util.List;

/*
    Design Pattern Command
    Role : Command
*/
public class GetAllWhere implements HibernateCommand {
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    Class clazz;
    String attribute;
    String value;

    public GetAllWhere(Class clazz, String attribute, String value) {
        this.clazz = clazz;
        this.attribute = attribute;
        this.value = value;
    }

    public List<HibernateEntity> execute() {
        return receiver.getAllWhere(clazz, attribute, value);
    }
}
