package views.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
import org.attoparser.dom.INestableNode;
import services.hibernate.HibernateFacade;
import services.mailing.GMailer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminAddEnrollment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddEnrollment() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HibernateFacade hibernate = HibernateFacade.getInstance();

		String action = request.getParameter("action");

		if ("search".equals(action)) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String studentGroupName = request.getParameter("studentgroupname");

			List<Student> resultats = hibernate.searchStudentByCriteria(nom, prenom, studentGroupName, null);
			request.setAttribute("resultats", resultats);

		} else if ("choisir class".equals(action)) {
			String selectedStudentEmail = request.getParameter("selected_student");
			List<HibernateEntity> students = hibernate.getAllWhere(Student.class, "email", selectedStudentEmail);

			if (!students.isEmpty()) {
				Student selectedStudent = (Student) students.get(0);
				request.setAttribute("selectedStudent", selectedStudent);

				List<HibernateEntity> allCourses = hibernate.getAll(Course.class);
				List<Enrollment> studentEnrollments = hibernate.getEnrollmentsByStudent(selectedStudent);

				Set<HibernateEntity> alreadyEnrolledCourses = new HashSet<>();
				for (Enrollment enrollment : studentEnrollments) {
					alreadyEnrolledCourses.add(enrollment.getCourse());
				}

				List<HibernateEntity> availableCourses = new ArrayList<>();
				for (HibernateEntity course : allCourses) {
					if (!alreadyEnrolledCourses.contains(course)) {
						availableCourses.add(course);
					}
				}
				request.setAttribute("courses", availableCourses);
			}

		} else if ("addEnrollment".equals(action)) {
			String selectedStudentEmail = request.getParameter("selected_student");
			String selectedCourse = request.getParameter("choosen_course");

			if (selectedStudentEmail != null && selectedCourse != null && !selectedCourse.isEmpty()) {
				Student student = (Student) hibernate.getAllWhere(Student.class, "email", selectedStudentEmail).get(0);
				Course course = (Course) hibernate.getAllWhere(Course.class, "className", selectedCourse).get(0);

				Enrollment newEnrollment = new Enrollment(student, course);
				hibernate.save(newEnrollment);
				//course.enrollments.add(newEnrollment);
                try {
                    GMailer mailer = new GMailer();
					mailer.sendNewInscriptionNotification(newEnrollment);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                request.setAttribute("message", "Inscription réussie !");
			} else {
				request.setAttribute("error", "Veuillez sélectionner un cours.");
			}
		}

		request.getRequestDispatcher("/WEB-INF/admin/addenrollment/addenrollment.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
