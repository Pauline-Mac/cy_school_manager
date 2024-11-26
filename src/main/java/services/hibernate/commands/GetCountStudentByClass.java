package services.hibernate.commands;

import models.Course;
import models.Professor;
import services.hibernate.HibernateReceiver;

import java.util.List;

public class GetCountStudentByClass implements HibernateCommand{
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    Course course;
    public GetCountStudentByClass(Course course) {
        this.course = course;
    }

    public Long execute() {
        return receiver.getCountStudentByClass(course);
    }
}
