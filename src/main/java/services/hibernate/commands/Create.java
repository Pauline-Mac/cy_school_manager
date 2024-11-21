package services.hibernate.commands;


import models.Student;
import models.StudentGroup;
import models.User;


import java.util.Date;

/*
    Design Pattern Command
    Role : Command
*/
public class Create implements HibernateCommand {

    public Create() {
    }

    public Student createStudent(String email, String password, String lastName, String firstName, Date birthDate, String phone, String studentGroupName) {
        StudentGroup studentGroup = createStudentGroup(studentGroupName); // Création du groupe en mémoire
        return new Student(email, password, lastName, firstName, birthDate, phone, studentGroup);
    }

    public User createUser(String email, String password, String lastName, String firstName, Date birthDate, String phone, String role) {
        return new User(email, password, lastName, firstName, birthDate, phone, role);
    }

    public StudentGroup createStudentGroup(String name) {
        return new StudentGroup(name);
    }

    @Override
    public Object execute() {
        throw new UnsupportedOperationException("The Create command does not support execution.");
    }
}
