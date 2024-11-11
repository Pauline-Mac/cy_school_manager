package org.openjfx.schoolmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        // Configuration de Hibernate
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Charge la configuration
                .buildSessionFactory();


        // Ouverture d'une session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Démarrer une transaction
            transaction = session.beginTransaction();

/*
            // Créer un nouvel user
            User user = new User();
            user.setFirstName("Jack");
            user.setLastName("Smith");
            user.setEmail("jsmith@gmail.com");
            user.setPassword("password");
            user.setRole("USER");
            user.setUuid(UUID.randomUUID().toString());
            user.setBirthDate(new Date(2003,6,19));
            user.setPhone("123-456-7890");
            user.setUsername("exampleUsername");

            session.persist(user);
            // Commit de la transaction
            transaction.commit();
*/
            UserCrud userCrud = new UserCrud();
            User retrievedUser = userCrud.FindByEmail("jsmith@gmail.com");
            if (retrievedUser != null) {
                System.out.println("User retrieved: " + retrievedUser.getFirstName() + " " + retrievedUser.getLastName());
            } else {
                System.out.println("User not found!");
            }





        } catch (Exception e) {
           if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback en cas d'erreur
            }
            e.printStackTrace();
        } finally {
            session.close(); // Ferme la session
            sessionFactory.close(); // Ferme le session factory
        }
    }
}
