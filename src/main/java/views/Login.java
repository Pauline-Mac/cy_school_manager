package views;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.*;
import services.authentication.AuthService;
import services.hibernate.HibernateFacade;
import java.io.IOException;

/**
 * Servlet implementation class AdminLogin
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/login/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HibernateFacade hibernate = HibernateFacade.getInstance();

		/*
		Pour nous simplifier la vie
		 */

		/*StudentGroup studentGroup = new StudentGroup("GSI-1");
		hibernate.save(new User("admin@admin.com", "password", "CY", "Admin", LocalDate.now(), "000-0000", "ADMIN"));
		hibernate.save(studentGroup);
		Student student1 = new Student("jane.doe@example.com",
				"password123",
				"Doe",
				"Jane",
				LocalDate.of(2002, 3, 15),
				"0612345678",
				studentGroup);
		hibernate.save(student1);
		Student student2 = new Student(
				"john.smith@example.com",
				"securePass!789",
				"Smith",
				"John",
				LocalDate.of(2001, 5, 20),
				"0654321876",studentGroup);
		hibernate.save(student2);
		StudentGroup studentGroup2 = new StudentGroup("GSI-2");
		hibernate.save(studentGroup2);
		StudentGroup studentGroup3 = new StudentGroup("GSI-3");
		hibernate.save(studentGroup3);

		Student student3 = new Student(
				"alice.brown@example.com",
				"passAlice2023",
				"Brown",
				"Alice",
				LocalDate.of(2003, 1, 10),
				"0687654321",
				studentGroup2);
		hibernate.save(student3);

		Student student4 = new Student(
				"michael.jones@example.com",
				"mikeJ!oneS",
				"Jones",
				"Michael",
				LocalDate.of(2000, 12, 25),
				"0678943212",
				studentGroup2);
		hibernate.save(student4);

		Student student5 = new Student(
				"emily.wilson@example.com",
				"Emily$Wilson",
				"Wilson",
				"Emily",
				LocalDate.of(2004, 7, 8),
				"0643218765",
				studentGroup3
		);
		hibernate.save(student5);
		Student student6 = new Student(
				"david.johnson@example.com",
				"D@vidJ123",
				"Johnson",
				"David",
				LocalDate.of(1999, 11, 3),
				"0698765432",
				studentGroup3
		);
		hibernate.save(student6);

		Professor professor1 = new Professor(
				"cdumont@school.com",
				"password123",
				"Dumont",
				"Clara",
				LocalDate.of(1985, 3, 15),
				"0123456789"
		);
		hibernate.save(professor1);

		Professor professor2 = new Professor(
				"mlefebvre@school.com",
				"securepass",
				"Lefebvre",
				"Marc",
				LocalDate.of(1978, 6, 22),
				"0987654321"
		);
		hibernate.save(professor2);

		Professor professor3 = new Professor(
				"smartins@school.com",
				"bioExpert!45",
				"Martins",
				"Sofia",
				LocalDate.of(1990, 9, 10),
				"0678901234"
		);
		hibernate.save(professor3);

		Professor professor4 = new Professor(
				"pnguyen@school.com",
				"Code4Life!",
				"Nguyen",
				"Paul",
				LocalDate.of(1983, 11, 5),
				"0654321987"
		);
		hibernate.save(professor4);

		Professor professor5 = new Professor(
				"lcaron@school.com",
				"LitMaster88",
				"Caron",
				"Lucie",
				LocalDate.of(1992, 1, 20),
				"0789123456"
		);
		hibernate.save(professor5);

		Course course1 = new Course("Introduction to Mathematics", professor1);
		Course course2 = new Course("Advanced Physics", professor2);
		Course course3 = new Course("Biology for Beginners", professor3);
		Course course4 = new Course("Computer Science Basics", professor4);
		Course course5 = new Course("French Literature", professor5);
		hibernate.save(course1);
		hibernate.save(course2);
		hibernate.save(course3);
		hibernate.save(course4);
		hibernate.save(course5);
		professor1.getCourses().add(course1);
		professor2.getCourses().add(course2);
		professor3.getCourses().add(course3);
		professor4.getCourses().add(course4);
		professor5.getCourses().add(course5);

		Enrollment enrollment1 = new Enrollment(student1, course1);
		Enrollment enrollment2 = new Enrollment(student2, course2);
		Enrollment enrollment3 = new Enrollment(student3, course3);
		Enrollment enrollment4 = new Enrollment(student4, course4);
		Enrollment enrollment5 = new Enrollment(student5, course5);
		Enrollment enrollment12 = new Enrollment(student1, course2);


		hibernate.save(enrollment1);
		hibernate.save(enrollment2);
		hibernate.save(enrollment3);
		hibernate.save(enrollment4);
		hibernate.save(enrollment5);
		hibernate.save(enrollment12);

		Note note1 = new Note(20.0, enrollment1); //student1
		Note note2 = new Note(15.0, enrollment1); //student 1
		Note note3 = new Note(12.0, enrollment2);
		Note note4 = new Note(15.0, enrollment12); // student 1 cours 2
		hibernate.save(note1);
		hibernate.save(note2);
		hibernate.save(note3);
		hibernate.save(note4);
		enrollment1.getNotes().add(note1);
		enrollment1.getNotes().add(note2);
		enrollment2.getNotes().add(note3);
		enrollment12.getNotes().add(note4);*/



		try {
			User user = (User) hibernate.getAllWhere(User.class, "email", email).get(0);
			if(new AuthService().authenticate(user, email, password)) {
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) {
					oldSession.invalidate();
				}
				HttpSession newSession = request.getSession(true);
				newSession.setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/" +
						user.getRole().toLowerCase() + "/index");
			} else {
				throw new Exception("Authentication failed");
			}
		} catch (Exception e){
			request.setAttribute("errorMessage", "Incorrect email or password");
			request.getRequestDispatcher("login_failed").forward(request, response);
		}
	}
}
