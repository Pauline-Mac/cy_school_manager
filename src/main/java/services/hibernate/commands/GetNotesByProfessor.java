package services.hibernate.commands;

import models.Enrollment;
import models.Note;
import models.Professor;
import models.Student;
import services.hibernate.HibernateReceiver;

import java.util.List;

public class GetNotesByProfessor implements HibernateCommand{
    HibernateReceiver receiver = HibernateReceiver.getInstance();
    Professor professor;
    public GetNotesByProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Note> execute() {
        return receiver.getNotesByProfessor(professor);
    }
}
