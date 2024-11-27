package views.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.HibernateEntity;
import models.User;
import models.UserCrud;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;

import java.io.IOException;
import java.time.LocalDate;
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

        request.setAttribute("user", user);
        request.getSession().setAttribute("update-user", user);

        System.out.println("update " + uuid);

        getServletContext().getRequestDispatcher("/WEB-INF/admin/update_user/update_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("update-user");


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

        response.sendRedirect(request.getContextPath() + "/admin/index");

    }
}
