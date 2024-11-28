package views.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
import services.hibernate.HibernateFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoveUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateFacade hibernate = HibernateFacade.getInstance();

        try {
            String uuid = request.getParameter("uuid");

            List<HibernateEntity> userList = hibernate.getAllWhere(User.class, "uuid", uuid);

            if (userList.isEmpty()) {
                throw new IllegalStateException("No user found with UUID: " + uuid);
            }
            User user = (User) userList.get(0);

            if ("STUDENT".equals(user.getRole())) {


                Student student = (Student) user;

                List<Enrollment> enrollments = hibernate.getEnrollmentsByStudent(student);
                for (Enrollment enrollment : enrollments) {
                    List<Note> notes = new ArrayList<>(enrollment.getNotes());
                    for (Note note : notes) {
                        enrollment.getNotes().remove(note);
                        hibernate.delete(note);
                    }
                    hibernate.delete(enrollment);
                }
                hibernate.delete(student);
            } else if ("PROFESSOR".equals(user.getRole())) {


                    Professor professor = (Professor) user;
                    List<Course> courses = new ArrayList<>(professor.getCourses());
                    for (Course course : courses) {
                        List<Enrollment> enrollments = new ArrayList<>(course.getEnrollments());
                        for (Enrollment enrollment : enrollments) {
                            List<Note> notes = new ArrayList<>(enrollment.getNotes());
                            for (Note note : notes) {
                                enrollment.getNotes().remove(note);
                                hibernate.delete(note);
                            }

                            course.getEnrollments().remove(enrollment);
                            hibernate.delete(enrollment);
                        }

                        professor.getCourses().remove(course);
                        hibernate.delete(course);
                    }

                    hibernate.delete(professor);

            } else {
                throw new IllegalArgumentException("Unsupported role for deletion: " + user.getRole());
            }

            response.sendRedirect(request.getContextPath() + "/admin/index");

        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);

            e.printStackTrace();
        }
    }

}
