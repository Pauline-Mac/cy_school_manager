package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import models.UserCrud;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("user") == null) {
//            resp.sendRedirect("login");
            session.setAttribute("user", "a.a@a.a");
        }

        else {
            if (session.getAttribute("user_data") == null) {


                UserCrud userCrud = new UserCrud();

                User user = userCrud.findByEmail((String) session.getAttribute("user"));

                session.setAttribute("user_data", user);

            }


            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(((User) session.getAttribute("user_data")).getBirthDate());
            System.out.println("formattedDate: " + formattedDate);

            getServletContext().getRequestDispatcher("/WEB-INF/user/user.jsp").forward(req, resp);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}
