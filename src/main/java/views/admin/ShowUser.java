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

public class ShowUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ShowUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        List<HibernateEntity> userList = HibernateFacade.getInstance().getAllWhere(User.class, "uuid", uuid);

        User user = null;

        if (userList != null && !userList.isEmpty()) {
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

        getServletContext().getRequestDispatcher("/WEB-INF/admin/show_user/show_user.jsp").forward(request, response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
