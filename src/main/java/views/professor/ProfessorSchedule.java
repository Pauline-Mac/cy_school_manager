package views.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
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

public class ProfessorSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfessorSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateInvoker invoker = HibernateFacade.getInstance().hibernate;
			List<Course> classes = invoker.getClassesByProfessor((Professor) (request.getSession().getAttribute("user")));
			List<Map<String, Object>> classesData = new ArrayList<>();
			for (Course course : classes) {
				Map<String, Object> classData = new HashMap<>();
				classData.put("className", course.getClassName());
				classData.put("countStudent", invoker.getCountStudentByClass(course));
				classesData.add(classData);
			}
			request.setAttribute("classes", classesData);
			this.getServletContext().getRequestDispatcher("/WEB-INF/professor/schedule/schedule.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Unable to retrieve professor classes: " + e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/professor/schedule/schedule.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
