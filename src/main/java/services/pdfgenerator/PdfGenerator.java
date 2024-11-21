package services.pdfgenerator;

import models.Student;
import models.StudentGroup;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PdfGenerator {

    public static void main(String[] args) throws Exception {

        Student student = getStudentFromDatabase(1L);  // Remplacer par l'ID d'un étudiant existant dans la DB
        generateReportForStudent(student);
    }

    public static void generateReportForStudent(Student student) {
        try {

            Map<String, Object> data = prepareData(student);

            TemplateEngine templateEngine = configureTemplateEngine();

            Context context = new Context();
            context.setVariables(data);
            String renderedHtml = templateEngine.process("rapport_notes", context);


            System.out.println(renderedHtml);


            Files.createDirectories(Paths.get("rapports"));

            String pdfPath = Paths.get("rapports", "rapport_notes_" + student.getFirstName() + "_" + student.getLastName() + ".pdf").toString();
            generatePdf(renderedHtml, pdfPath);

            System.out.println("PDF généré avec succès : " + pdfPath);
        } catch (Exception e) {
            System.err.println("Erreur lors de la génération du PDF :");
            e.printStackTrace();
        }
    }

    private static Map<String, Object> prepareData(Student student) {
        Map<String, Object> data = new HashMap<>();

        data.put("prenom", student.getFirstName());
        data.put("nom", student.getLastName());
        data.put("classe", student.getStudentGroup().getStudentGroupName());
        data.put("date", LocalDate.now().toString());
        return data;
    }

    private static void generatePdf(String htmlContent, String outputPath) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(fos);
        }
    }

    public static Student getStudentFromDatabase(Long studentId) {
        // Configuration Hibernate
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(StudentGroup.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        Student student = null;

        try {
            session.beginTransaction();
            student = session.get(Student.class, studentId);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

        return student;
    }

    private static TemplateEngine configureTemplateEngine() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("src/main/resources/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }
}
