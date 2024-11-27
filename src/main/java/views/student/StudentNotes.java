package views.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;

import javax.mail.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class Index
 */

public class StudentNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentNotes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateFacade facade = HibernateFacade.getInstance();
			User user = (User) request.getSession().getAttribute("user");
			List<Enrollment> enrollments = facade.getEnrollmentsByStudent((Student) user);
			Map<String, Object> data = new HashMap<>();

			if (!enrollments.isEmpty()) {
				List<Map<String, Object>> matieres = new ArrayList<>();

				for (Enrollment enrollment : enrollments) {

					if (!enrollment.getNotes().isEmpty()) {
						int i = 0;
						for (Note note : enrollment.getNotes()) {
							Map<String, Object> noteData = new HashMap<>();
							noteData.put("note", String.format("%.2f", note.getValue()));
							noteData.put("nom", enrollment.getCourse().getClassName());
							noteData.put("date", note.getCreatedAt());
							noteData.put("libelle", "Partiel session " + (i + 1));

							matieres.add(noteData);
							i++;
						}
					} else {
						Map<String, Object> matiereData = new HashMap<>();
						matiereData.put("nom", enrollment.getCourse().getClassName());
						matiereData.put("note", "Aucune note");
						matiereData.put("date", "N/A");
						matiereData.put("libelle", "N/A");
						matieres.add(matiereData);
					}
				}
				data.put("matieres", matieres);
			}

			request.setAttribute("matieres", data.get("matieres"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/student/notes/notes.jsp").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("error", "Unable to retrieve student information: " + e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/student/notes/notes.jsp").forward(request, response);
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
