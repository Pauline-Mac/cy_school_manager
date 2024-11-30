package views.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;
import services.mailing.GMailer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpdateUser extends HttpServlet {

    public static final long serialVersionUID = 1L;

    public UpdateUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        List<HibernateEntity> userList = HibernateFacade.getInstance().getAllWhere(User.class, "uuid", uuid);
        User user = null;

        if (!userList.isEmpty()) {
            user = (User) userList.get(0);
        }


        assert user != null;
        List<HibernateEntity> enrollmentList = HibernateFacade.getInstance().getAll(Enrollment.class);

        List<Course> courseList = new ArrayList<>();

        for (HibernateEntity e : enrollmentList) {
            Enrollment enrollment = (Enrollment) e;
            if (enrollment.getStudent().equals(user))
                courseList.add(enrollment.getCourse());
        }

        request.setAttribute("user", user);
        request.setAttribute("courseList", courseList);
        request.getSession().setAttribute("courseList", courseList);
        request.getSession().setAttribute("update-user", user);


        getServletContext().getRequestDispatcher("/WEB-INF/admin/update_user/update_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("update-user");
            List<Course> courseList = (List<Course>) request.getSession().getAttribute("courseList");

        if (!request.getParameter("first_name").isBlank())
            user.setFirstName(request.getParameter("first_name"));
        if (!request.getParameter("last_name").isBlank())
            user.setLastName(request.getParameter("last_name"));
        if (!request.getParameter("email").isBlank())
            user.setEmail(request.getParameter("email"));
        if (!request.getParameter("tel").isBlank())
            user.setPhone(request.getParameter("tel"));

            String birthDate = request.getParameter("birth_date");
            if (birthDate != null) {
                LocalDate parsedDate = LocalDate.parse(birthDate);
                user.setBirthDate(parsedDate);
            }

            HibernateInvoker hibernate = HibernateFacade.getInstance().hibernate;
            hibernate.save(user);


            try {
                GMailer mailer = new GMailer();
                mailer.sendInfoModificationConfirmation(user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (user.getRole().equals("STUDENT")) {
                String[] courses = request.getParameterValues("class[]");

                if (courses != null) {
                boolean courseFound = true;
                for (String strcourse : courses) {
                    courseFound = false;
                    for (int j = 0; j < courseList.size(); j++) {
                        Course course = courseList.get(j);
                        if (course.getClassId() == Integer.parseInt(strcourse)) {
                            courseList.remove(course);
                            courseFound = true;
                        }
                    }
                    if (!courseFound) {
                        Course course = (Course) HibernateFacade.getInstance().get(Course.class, Integer.parseInt(strcourse));
                        Student student = (Student) HibernateFacade.getInstance().get(Student.class, user.getUserId().intValue());
                        Enrollment enrollment = new Enrollment(student, course);

                        HibernateFacade.getInstance().save(enrollment);
                    }
                }
                }
                for (Course course : courseList) {
                    List<Enrollment> enrollments = hibernate.getEnrollmentByStudent((Student) user);
                    assert enrollments != null;
                    for (Enrollment enrollment : enrollments) {
                        for (Course course1 : courseList) {
                            if (course1.getClassName().equals(enrollment.getCourse().getClassName())) {
                                course1.getEnrollments().remove(enrollment);
                                hibernate.delete(enrollment);
                            }
                        }
                    }
                }
            }
            request.getSession().setAttribute("success", "Ajout effectué avec succès");
            request.getSession().removeAttribute("error");

            try {
                GMailer mailer = new GMailer();
                mailer.sendClassModification((Student) user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();

            request.getSession().setAttribute("error", "Echec lors de la modification d'un utilisateur");
            request.getSession().removeAttribute("success");
        }
        response.sendRedirect(request.getContextPath() + "/admin/index");

    }
}
