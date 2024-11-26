package views.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import models.User;
import models.UserCrud;


public class UpdateUserController extends HttpServlet {


    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/user/updateuser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        User user = (User)session.getAttribute("user");
        User user = new User();
        UserCrud userCrud = new UserCrud();


        if (!req.getParameter("firstname").isBlank())
            user.setFirstName(req.getParameter("firstname"));
        if (!req.getParameter("lastname").isBlank())
            user.setLastName(req.getParameter("lastname"));
        if (!req.getParameter("email").isBlank())
            user.setEmail(req.getParameter("email"));
        if (!req.getParameter("tel").isBlank())
            user.setPhone(req.getParameter("tel"));
        if (!req.getParameter("birthdate").isBlank())
            user.setBirthDate(LocalDate.parse(req.getParameter("birthdate"), DateTimeFormatter.ISO_LOCAL_DATE));

        boolean updateSuccess = userCrud.updateUser(user);

        if (updateSuccess) {
            session.setAttribute("user_update_success", true);
        } else {
            session.setAttribute("user_update_success", false);
        }



//        getServletContext().getRequestDispatcher("/WEB-INF/user/user.jsp").forward(req, resp);
        resp.sendRedirect("user");
    }
}
