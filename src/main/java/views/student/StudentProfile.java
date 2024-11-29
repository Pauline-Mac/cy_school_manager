package views.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.hibernate.HibernateFacade;

import java.io.IOException;

/**
 * Servlet implementation class Profile
 */

public class StudentProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/student/profile/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			User user = (User) request.getSession().getAttribute("user");

			if (!request.getParameter("first_name").isBlank())
				user.setFirstName(request.getParameter("first_name"));
			if (!request.getParameter("last_name").isBlank())
				user.setLastName(request.getParameter("last_name"));
			if (!request.getParameter("email").isBlank())
				user.setEmail(request.getParameter("email"));
			if (!request.getParameter("tel").isBlank())
				user.setPhone(request.getParameter("tel"));


			HibernateFacade.getInstance().update(user);

			request.getSession().setAttribute("success", "Changements effectués avec succès");

		} catch (Exception e) {
			request.getSession().setAttribute("error", "Une erreur est survenur lors de la modification de votre profil");
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/student/profile");
	}
}
