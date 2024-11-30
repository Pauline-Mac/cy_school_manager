package views;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.*;
import services.authentication.AuthService;
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
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) {
					oldSession.invalidate();
				}
				HttpSession newSession = request.getSession(true);
				newSession.setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/" +
						user.getRole().toLowerCase() + "/index");
			} else {
				throw new Exception("Authentication failed");
			}
		} catch (Exception e){
			request.setAttribute("errorMessage", "Email ou mot de passe incorect");
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/login/login.jsp").forward(request, response);
		}
	}
}
