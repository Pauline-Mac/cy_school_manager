package services.hibernate.commands;

import models.Enrollment;
import models.HibernateEntity;
import models.Student;
import services.hibernate.HibernateReceiver;

import java.util.List;

public class GetEnrollmentByStudent implements HibernateCommand{
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    Student student;
    public GetEnrollmentByStudent(Student student) {
        this.student = student;
    }

    public List<Enrollment> execute() {
        return receiver.getEnrollmentByStudent(student);
    }
}
