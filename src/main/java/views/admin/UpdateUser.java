package views.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.HibernateEntity;
import models.User;
import services.hibernate.HibernateFacade;

import java.io.IOException;
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

        // show user data

        System.out.println("update " + uuid);

        getServletContext().getRequestDispatcher("/WEB-INF/admin/update_user/update_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
