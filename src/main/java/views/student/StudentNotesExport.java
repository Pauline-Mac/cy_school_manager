package views.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Student;
import services.pdfgenerator.PdfGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StudentNotesExport extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = (Student) request.getSession().getAttribute("user");

		if (student == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Étudiant non trouvé.");
			return;
		}

		String pdfPath = PdfGenerator.generateReportForStudent(student);
		if (pdfPath == null || !(new File(pdfPath).exists())) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Échec de la génération du PDF.");
			return;
		}

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new File(pdfPath).getName() + "\"");

		try (FileInputStream fis = new FileInputStream(pdfPath); OutputStream os = response.getOutputStream()) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		}
	}
}
