package views.admin;

import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servlet implementation class Students
 */

public class AdminStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminStudents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateInvoker invoker = HibernateFacade.getInstance().hibernate;


			List<HibernateEntity> students = invoker.getAll(Student.class);
			request.setAttribute("users", students);
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/students/students.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Unable to retrieve student information: " + e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/students/students.jsp").forward(request, response);
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
