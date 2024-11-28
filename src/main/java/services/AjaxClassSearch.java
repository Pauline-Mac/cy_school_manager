package services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import models.HibernateEntity;
import services.hibernate.HibernateFacade;
import services.hibernate.HibernateInvoker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AjaxClassSearch extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AjaxClassSearch() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String search = request.getParameter("class");
        HibernateInvoker hibernate = HibernateFacade.getInstance().hibernate;
        List<HibernateEntity> allCourses = hibernate.getAll(Course.class);


        List<Course> courses = new ArrayList<Course>();

        for (HibernateEntity entity : allCourses) {
            Course course = (Course) entity;
            if (course.getClassName().toLowerCase().contains(search.toLowerCase())) {
                courses.add(course);
            }
        }

        int courseNum = courses.size();
        out.println("{");
        out.println("\"classes\": [");
        for (int i = 0; i < courseNum; i++) {
            Course course = courses.get(i);
            out.println("{");
            out.println("\"name\": \"" + course.getClassName() + "\",");
            out.println("\"id\":\"" + course.getClassId() + "\"");
            out.println("}");
            if (i != courseNum - 1) {
                out.println(",");
            }
        }

        out.println("]");
        out.println("}");

        out.flush();


    }
}
