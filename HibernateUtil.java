package com.hibernate.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.model.Student;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    // Method to initialize SessionFactory
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Configuration object
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/my_crud");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "Sagarp106");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");

                // Apply properties to configuration
                configuration.setProperties(properties);
                
                // Add annotated class
                configuration.addAnnotatedClass(Student.class);

                // Build ServiceRegistry
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                // Build SessionFactory
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                // Handle exception and possibly rethrow
                System.err.println("Initial SessionFactory creation failed." + e);
                e.printStackTrace();
                throw new ExceptionInInitializerError("Failed to initialize Hibernate SessionFactory");
            }
        }
        return sessionFactory;
    }

    // Method to shut down SessionFactory and release resources
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
