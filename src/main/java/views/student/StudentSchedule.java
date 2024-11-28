package views.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import models.Enrollment;
import models.Professor;
import models.Student;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class Index
 */

public class StudentSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateFacade facade = HibernateFacade.getInstance();
			Student student = (Student) request.getSession().getAttribute("user");

			List<Enrollment> enrollments = facade.getEnrollmentsByStudent(student);

			List<Course> courses = new ArrayList<>();
			for (Enrollment enrollment : enrollments) {
				courses.add(enrollment.getCourse());
			}

			request.setAttribute("classes", courses);
			this.getServletContext().getRequestDispatcher("/WEB-INF/student/schedule/schedule.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Unable to retrieve student schedule: " + e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/student/schedule/schedule.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
