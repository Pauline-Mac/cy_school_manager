package views.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Note;
import models.Professor;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Index
 */

public class ProfessorNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfessorNotes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateInvoker invoker = HibernateFacade.getInstance().hibernate;
			List<Note> notes = invoker.getNotesByProfessor((Professor) (request.getSession().getAttribute("user")));
			request.setAttribute("notes", notes);
			this.getServletContext().getRequestDispatcher("/WEB-INF/professor/notes/notes.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Unable to retrieve professor notes: " + e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/professor/index").forward(request, response);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/professor/notes/notes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
