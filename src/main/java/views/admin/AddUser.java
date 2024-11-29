package views.admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AddUser extends HttpServlet {

    private static long serialVersionUID = 1L;


    public AddUser() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/admin/add_user/add_user.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String lastname = req.getParameter("last_name");
            String firstname = req.getParameter("first_name");
            String email = req.getParameter("email");
            String tel = req.getParameter("tel");
            String birthDate = req.getParameter("birth_date");
            LocalDate localDate = LocalDate.parse(birthDate);

            String role = req.getParameter("role");

            String groupId = "";

            if (role.equals("student")) {
                groupId = req.getParameter("group_id");
            }


            String[] classes = req.getParameterValues("class[]");

            Student student = null;
            Professor professor = null;

            HibernateInvoker hibernate = HibernateFacade.getInstance().hibernate;

            Enrollment enrollment = null;
            Course course = null;


            if (role.equals("student")) {

                List<HibernateEntity> studentGroupList = HibernateFacade.getInstance().getAllWhere(StudentGroup.class, "studentGroupName", groupId);
                StudentGroup studentGroup = null;

                if (studentGroupList.size() > 0) {
                    studentGroup = (StudentGroup) studentGroupList.get(0);
                } else {
                    studentGroup = new StudentGroup(groupId);
                    hibernate.save(studentGroup);
                }

                student = new Student(email, "password", lastname, firstname, localDate, tel, studentGroup);
                hibernate.save(student);


                for (String className : classes) {
                    course = (Course) hibernate.getAllWhere(Course.class, "classId", className).get(0);

                    enrollment = new Enrollment(student, course);

                    hibernate.save(enrollment);
                }

            } else {
                professor = new Professor(email, "password", lastname, firstname, localDate, tel);
                hibernate.save(professor);

//            for (String className : classes) {
//                course = new Course(className, professor);
//
//                hibernate.save(course);
//            }
            }

            req.getSession().setAttribute("success", "Ajout effectué avec succès");
            req.getSession().removeAttribute("error");

        } catch (Exception e) {
            e.printStackTrace();

            req.getSession().setAttribute("error", "Echec lors de l'ajout d'un utilisateur");
            req.getSession().removeAttribute("success");
        }

        resp.sendRedirect("index");

    }

}
