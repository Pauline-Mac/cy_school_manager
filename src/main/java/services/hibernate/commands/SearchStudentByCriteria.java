package services.hibernate.commands;


import models.Student;
import services.hibernate.HibernateReceiver;

import java.util.List;

public class SearchStudentByCriteria implements HibernateCommand{
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    String lastname;
    String firstname;
    String studentgroupname;
    String className;

    public SearchStudentByCriteria(String firstname, String lastname, String studentgroupname, String className) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.studentgroupname = studentgroupname;
        this.className = className;
    }

    public List<Student> execute() {
        return receiver.searchStudentByCriteria(firstname, lastname, studentgroupname, className);
    }
}
