package services.mailing;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import models.Enrollment;
import models.HibernateEntity;
import org.apache.commons.codec.binary.Base64;
import models.Student;
import models.User;
import services.hibernate.HibernateFacade;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;

public class GMailer {

    private final Gmail service;

    public GMailer() throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("CY School Manager")
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(Objects.requireNonNull(GMailer.class.getResourceAsStream("/client_secrets.json"))));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


    public void sendMail(String subject, String message, String mail_recipient) throws Exception {
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(System.getenv("APP_MAIL")));
            email.addRecipient(TO, new InternetAddress(mail_recipient));
            email.setSubject(subject);
            email.setText(message);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message msg = new Message();
            msg.setRaw(encodedEmail);

            try {
                msg = service.users().messages().send("me", msg).execute();
                System.out.println("Message id: " + msg.getId());
                System.out.println(msg.toPrettyString());
            } catch (GoogleJsonResponseException e) {
                GoogleJsonError error = e.getDetails();
                if (error.getCode() == 403) {
                    System.err.println("Unable to send message: " + e.getDetails());
                } else {
                    throw e;
                }
            }
        }

        public void sendClassModification(Student student) throws Exception {

            HibernateFacade hibernate = HibernateFacade.getInstance();
            List<Enrollment> enrollments = hibernate.getEnrollmentsByStudent(student);
            StringBuilder listEnrollments = new StringBuilder();
            for (Enrollment enrollment1 : enrollments) {
                listEnrollments.append("\n - ").append(enrollment1.getCourse().getClassName()).append(" avec ").append(enrollment1.getCourse().getProfessor().getFirstName()).append(" ").append(enrollment1.getCourse().getProfessor().getLastName());
            }

            String message = "Bonjour " +student.getFirstName()+" "+student.getLastName()+
                    ", vos inscription ont été modifiés"+
                    "\n\nVoici un récapitulatif des cours auxquels vous êtes inscrit" +
                    "\n";

            message += listEnrollments.toString();


            sendMail("Changement de vos cours",message,student.getEmail());
        }

        public void sendInfoModificationConfirmation(User user) throws Exception {

            String message = "Bonjour "+ user.getFirstName()+" "+user.getLastName()+",\n"
                    +"Vos informations personnelles ont bien été modifiées avec succès.\n\n"
                    +"Voici un récapitulatif de vos informations :\n" +
                    "Prénom : \" + user.getFirstName()\n"+
                    "Nom : " + user.getLastName()+"\n"+
                    "Email : " + user.getEmail()+"\n"+
                    "Date de naissance : " + user.getBirthDate()+"\n"+
                    "Téléphone : " + user.getPhone()+"\n";


            sendMail("Modification de vos informations personnelles", message, user.getEmail());
        }

        public void sendNewNoteNotification(Student student) throws Exception {

            sendMail("Nouvelles notes disponibles",
                    "Bonjour " +student.getFirstName()+" "+student.getLastName()+
                            ",\n De nouvelles notes ont été ajoutées ou modifiées à votre dossier",
                    student.getEmail());
        }

        public void sendNewInscriptionNotification(Enrollment enrollment) throws Exception {

            sendMail("Validation de l'inscription",
                    "Bonjour " +enrollment.getStudent().getFirstName()+" "+enrollment.getStudent().getLastName()+
                            ",\n Votre inscription scolaire a été validée pour le cours : "+enrollment.getCourse().getClassName()+ " avec le ou la professeur "+enrollment.getCourse().getProfessor().getLastName(),
                    enrollment.getStudent().getEmail());
        }

    }