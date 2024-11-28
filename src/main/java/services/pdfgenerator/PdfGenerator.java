package services.pdfgenerator;

import models.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import services.hibernate.HibernateFacade;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.*;

public class PdfGenerator {

    private static final String REPORTS_DIRECTORY = "C:/tomcat/reports";

    public static String generateReportForStudent(Student student) {
        String pdfPath = null;
        try {
            Map<String, Object> data = prepareData(student);
            TemplateEngine templateEngine = configureTemplateEngine();

            Context context = new Context();
            context.setVariables(data);
            String renderedHtml = templateEngine.process("rapport_notes", context);

            File reportsDir = new File(REPORTS_DIRECTORY);
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            String fileName = String.format("rapport_notes_%s_%s.pdf",
                    student.getFirstName(),
                    student.getLastName());

            pdfPath = new File(reportsDir, fileName).getAbsolutePath();

            generatePdf(renderedHtml, pdfPath);

            System.out.println("PDF généré avec succès. Chemin : " + pdfPath);

        } catch (Exception e) {
            System.err.println("Erreur lors de la génération du PDF :");
            e.printStackTrace();
        }
        return pdfPath;
    }

    private static Map<String, Object> prepareData(Student student) {
        Map<String, Object> data = new HashMap<>();

        data.put("prenom", student.getFirstName());
        data.put("nom", student.getLastName());
        data.put("classe", student.getStudentGroup().getStudentGroupName());
        data.put("date", LocalDate.now().toString());

        HibernateFacade hibernate = HibernateFacade.getInstance();
        List<Enrollment> enrollments = hibernate.getEnrollmentsByStudent(student);

        List<Map<String, Object>> matieres = new ArrayList<>();
        double totalMoyennes = 0;
        int nombreMatieresAvecNotes = 0;

        for (Enrollment enrollment : enrollments) {
            Map<String, Object> matiereData = new HashMap<>();
            matiereData.put("nom", enrollment.getCourse().getClassName());

            if (!enrollment.getNotes().isEmpty()) {
                double sumNotes = 0;
                int countNotes = enrollment.getNotes().size();
                for (Note note : enrollment.getNotes()) {
                    sumNotes += note.getValue();
                }

                double averageNote = sumNotes / countNotes;
                matiereData.put("note", String.format("%.2f", averageNote));

                totalMoyennes += averageNote;
                nombreMatieresAvecNotes++;
            } else {
                matiereData.put("note", "Aucune note");
            }

            matieres.add(matiereData);
        }

        double moyenneGenerale = nombreMatieresAvecNotes > 0
                ? totalMoyennes / nombreMatieresAvecNotes
                : 0;

        data.put("matieres", matieres);
        data.put("moyenne", nombreMatieresAvecNotes > 0 ? String.format("%.2f", moyenneGenerale) : "Non calculable");

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

    private static TemplateEngine configureTemplateEngine() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(1);
        resolver.setCacheable(false);

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }
}
