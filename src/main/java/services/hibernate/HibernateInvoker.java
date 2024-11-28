package services.hibernate;

import models.*;
import org.hibernate.query.Query;
import services.hibernate.commands.*;
import services.hibernate.commands.*;
import services.hibernate.commands.*;

import java.util.List;

/*
    Design Pattern Command
    Role : Invoker
*/
public class HibernateInvoker {
    HibernateCommand command;

    public List<Professor> searchProfessorByCriteria(String firstname, String lastname, String email){
        return (List<Professor>) executeCommand(new SearchProfessorByCriteria(firstname, lastname, email));
    }

    public List<Student> searchStudentByCriteria(String firstname, String lastname, String studentgroupname, String classname){
        return (List<Student>) executeCommand(new SearchStudentByCriteria(firstname, lastname, studentgroupname, classname));
    }

    public Long getCountStudentByClass(Course course) {

        return (Long) executeCommand(new GetCountStudentByClass(course));
    }

    public List<Course> getClassesByProfessor(Professor professor) {

        return (List<Course>) executeCommand(new GetClassesByProfessor(professor));
    }

    public List<Note> getNotesByProfessor(Professor professor) {

        return (List<Note>) executeCommand(new GetNotesByProfessor(professor));
    }

    public List<Enrollment> getEnrollmentByStudent(Student student) {

        return (List<Enrollment>) executeCommand(new GetEnrollmentByStudent(student));
    }

    public Boolean save(HibernateEntity entity) {
        Save saveCommand = new Save(entity);
        return (Boolean) executeCommand(saveCommand);
    }

    public HibernateEntity get(Class clazz, Integer id) {
        return (HibernateEntity) executeCommand(new Get(clazz, id));
    }

    public List<HibernateEntity> getAll(Class clazz) {
        return (List<HibernateEntity>) executeCommand(new GetAll(clazz));
    }

    public List<HibernateEntity> getAllWhere(Class clazz, String attribute, String value) {
        return (List<HibernateEntity>) executeCommand(new GetAllWhere(clazz, attribute, value));
    }

    public Object executeCommand(HibernateCommand command) {
        try {
            return command.execute();
        } catch (Exception e) {
            System.err.println("Hibernate Error:" + command.getClass().getName() + "\n" + e.getMessage());
            return null;
        }
    }

    public Boolean update(HibernateEntity entity) {
        return (Boolean) executeCommand(new Update(entity));
    }
}
