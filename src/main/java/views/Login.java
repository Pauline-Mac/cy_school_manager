package views;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.authentification.AuthService;
import services.hibernate.HibernateFacade;
import java.io.IOException;

/**
 * Servlet implementation class AdminLogin
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/login/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HibernateFacade hibernate = HibernateFacade.getInstance();
		try {
			User user = (User) hibernate.getAllWhere(User.class, "email", email).get(0);
			if(new AuthService().authenticate(user, email, password)) {
				request.getSession().setAttribute("user", user);
				System.out.println("Authentication succeeded, role is " + user.getRole() +" !");
				response.sendRedirect(request.getContextPath() + "/" + user.getRole().toLowerCase() + "/students");

			}
		} catch (Exception e){
			request.setAttribute("errorMessage", "Incorrect email or password");
			request.getRequestDispatcher("login_failed").forward(request, response);
		}
	}
}
