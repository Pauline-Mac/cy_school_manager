package services.hibernate.commands;


import models.Professor;
import models.Student;
import services.hibernate.HibernateReceiver;

import java.util.List;

public class SearchProfessorByCriteria implements HibernateCommand{
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    String lastname;
    String firstname;
    String email;

    public SearchProfessorByCriteria(String firstname, String lastname, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

    public List<Professor> execute() {
        return receiver.searchProfessorByCriteria(firstname, lastname, email);
    }
}
