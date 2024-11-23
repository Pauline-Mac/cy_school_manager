package services.hibernate;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            HibernateFacade.getInstance();
            System.out.println("Hibernate initialized successfully.");
        } catch (Exception e) {
            System.err.println("Error initializing Hibernate: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
