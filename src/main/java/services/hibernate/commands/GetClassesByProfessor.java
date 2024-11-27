package services.hibernate.commands;

import models.Course;
import models.Note;
import models.Professor;
import services.hibernate.HibernateReceiver;

import java.util.List;

public class GetClassesByProfessor implements HibernateCommand{
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    Professor professor;
    public GetClassesByProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Course> execute() {
        return receiver.getClassesByProfessor(professor);
    }
}
