package views.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
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



        String birthDate = req.getParameter("birthdate");
        if (birthDate != null) {
            String[] birthDateComponents = birthDate.split("-");
            int year = Integer.parseInt(birthDateComponents[0]);
            int month = Integer.parseInt(birthDateComponents[1]);
            int day = Integer.parseInt(birthDateComponents[2]);
            user.setBirthDate(new Date(year - 1900, month - 1, day));
        }

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
