package views.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;
import services.hibernate.HibernateFacade;

import java.io.IOException;
import java.util.List;

public class AdminAddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddClass() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin/addclass/addclass.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HibernateFacade hibernate = HibernateFacade.getInstance();

		if ("search".equals(action)) {

			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");

			List<Professor> resultats = hibernate.searchProfessorByCriteria(prenom, nom, email);
			System.out.println("La liste des résultats");
			for (Professor prof : resultats) {
				System.out.println("Les résultats : " + prof.getFirstName());
			}

			request.setAttribute("resultats", resultats);

		} else if ("createClass".equals(action)) {

			String newClassName = request.getParameter("new_class_name");
			String selectedProfessorEmail = request.getParameter("selected_professor");

			if (newClassName == null || newClassName.isEmpty()) {
				request.setAttribute("error", "Veuillez entrer un nom de classe.");
			} else if (selectedProfessorEmail == null || selectedProfessorEmail.isEmpty()) {
				request.setAttribute("error", "Veuillez sélectionner un professeur.");
			} else {

				List<HibernateEntity> existingClasses = hibernate.getAllWhere(Course.class, "className", newClassName);
				if (!existingClasses.isEmpty()) {
					request.setAttribute("error", "Une classe avec ce nom existe déjà.");
				} else {

					List<HibernateEntity> professors = hibernate.getAllWhere(Professor.class, "email", selectedProfessorEmail);
					if (!professors.isEmpty()) {
						Professor professor = (Professor) professors.get(0);

						Course newClass = new Course(newClassName, professor);
						hibernate.save(newClass);
						professor.getCourses().add(newClass);
						request.setAttribute("message", "La classe a été créée avec succès !");
					} else {
						request.setAttribute("error", "Le professeur sélectionné n'existe pas.");
					}
				}
			}
		}

		request.getRequestDispatcher("/WEB-INF/admin/addclass/addclass.jsp").forward(request, response);
	}
}
