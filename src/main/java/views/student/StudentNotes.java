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
			HibernateInvoker invoker = HibernateFacade.getInstance().hibernate;
			User user = (User) request.getSession().getAttribute("user");
			List<Enrollment> enrollments = invoker.getEnrollmentByStudent((Student) user);
			Map<String, Object> data = new HashMap<>();

			if (!enrollments.isEmpty()) {
				List<Map<String, Object>> matieres = new ArrayList<>();

				for (Enrollment enrollment : enrollments) {
					Map<String, Object> matiereData = new HashMap<>();
					matiereData.put("nom", enrollment.getCourse().getClassName());

					if (!enrollment.getNotes().isEmpty()) {
						int i = 0;
						for (Note note : enrollment.getNotes()) {
							Map<String, Object> noteData = new HashMap<>();
							noteData.put("note", String.format("%.2f", note.getValue())); // Ajoute la note
							noteData.put("nom", enrollment.getCourse().getClassName()); // Nom du cours
							noteData.put("date", note.getCreatedAt()); // Date de la note
							noteData.put("libelle", "Partiel session " + (i + 1)); // Libellé de la session

							// Ajoute la donnée de note pour chaque session comme une nouvelle ligne
							matieres.add(noteData);
							i++;
						}
					} else {
						// Si aucune note n'est présente, ajoute une ligne avec des informations par défaut
						Map<String, Object> noteData = new HashMap<>();
						noteData.put("note", "Aucune note");
						noteData.put("date", "N/A");
						noteData.put("libelle", "N/A");
						matieres.add(noteData);
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
