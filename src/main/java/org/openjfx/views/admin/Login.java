package org.openjfx.views.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.openjfx.schoolmanager.User;

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
        // TODO Auto-generated constructor stub
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

		User user = AuthService.authenticate(email, password);
		if (user != null) {
			request.getSession().setAttribute("user", email);
			request.getSession().setAttribute("role", user.getRole());
			response.sendRedirect("admin/index");
			System.out.println("Authentication check user role is" + request.getSession().getAttribute("role"));
		} else {
			request.setAttribute("errorMessage", "Incorrect email or password");
			request.getRequestDispatcher("admin/index").forward(request, response);
			System.out.println("Authentication failed");
		}
	}


}
