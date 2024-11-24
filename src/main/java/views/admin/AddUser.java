package views.admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

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

        String lastname = req.getParameter("last_name");
        String firstname = req.getParameter("first_name");
        String email = req.getParameter("email");
        String tel = req.getParameter("tel");
        String birthDate = req.getParameter("birth_date");
        String username = req.getParameter("username");

        String role = req.getParameter("role");


        String[] classes = req.getParameterValues("class[]");

//        int[] classesId = new int[classes.length];
//
//        for (int i = 0; i < classes.length; i++) {
//            classesId[i] = Integer.parseInt(classes[i]);
//        }

        System.out.println(lastname);
        System.out.println(firstname);
        System.out.println(email);
        System.out.println(tel);
        System.out.println(birthDate);
        System.out.println(username);
        System.out.println(role);

        if (classes != null) {
            for (String className : classes) {
                System.out.println(className);
            }
        }


        resp.sendRedirect("professors");

        // TODO: ajouter la personne dans la bdd






    }


}
