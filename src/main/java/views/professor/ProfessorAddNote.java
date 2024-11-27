package views.professor;

import com.google.api.services.gmail.Gmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
import services.hibernate.HibernateFacade;
import services.mailing.GMailer;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ProfessorAddNote
 */
public class ProfessorAddNote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfessorAddNote() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HibernateFacade hibernate = HibernateFacade.getInstance();

		List<Course> courses = hibernate.getClassesByProfessor((Professor) request.getSession().getAttribute("user"));
		request.setAttribute("courses", courses);

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String studentgroupname = request.getParameter("studentgroupname");
		String chosenCourse = request.getParameter("choosen_course");

		if (chosenCourse == null || chosenCourse.isEmpty()) {
			request.setAttribute("error", "Veuillez sélectionner une matière.");
		} else {
			List<Student> resultats = hibernate.searchStudentByCriteria(prenom, nom, studentgroupname, chosenCourse);
			request.setAttribute("resultats", resultats);
		}

		request.getRequestDispatcher("/WEB-INF/professor/addnotes/addnotes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		HibernateFacade hibernate = HibernateFacade.getInstance();

		if ("search".equals(action)) {
			doGet(request, response);

		} else if ("addNote".equals(action)) {
			Student selected_student = (Student) hibernate.getAllWhere(Student.class, "email", request.getParameter("selected_student")).get(0);
			String noteValueStr = request.getParameter("note_value");
			Enrollment selected_student_enrollment = hibernate.getEnrollmentsByStudent(selected_student).get(0);

			try {
				double noteValue = Double.parseDouble(noteValueStr);
				hibernate.save(new Note(noteValue, selected_student_enrollment ));
				GMailer mailer = new GMailer();
				mailer.sendNewNoteNotification(selected_student);
				request.setAttribute("success", "La note a été attribuée avec succès.");
			} catch (NumberFormatException e) {
				request.setAttribute("error", "Veuillez entrer une note valide.");
			} catch (Exception e) {
				request.setAttribute("error", "Erreur lors de l'attribution de la note : " + e.getMessage());
			}

			doGet(request, response);
		}
	}
}
