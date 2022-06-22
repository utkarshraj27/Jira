package com.lla.kpidashboard.data.exporter.jira.lambda;

/**
 * @author argu
 */
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.lla.kpidashboard.data.exporter.jira.domain.Story;
import com.lla.kpidashboard.data.exporter.jira.domain.Epic;
import com.lla.kpidashboard.data.exporter.jira.domain.Task;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
@Slf4j
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (null != sessionFactory)
            return sessionFactory;
        
        Configuration configuration = new Configuration();
        log.info("*********Taking Configurations******");

        String jdbcUrl = "jdbc:mysql://"
                + System.getenv("RDS_HOSTNAME")
                + "/"
                + System.getenv("RDS_DB_NAME");
        
        configuration.setProperty("hibernate.connection.url", jdbcUrl);
        log.info("*************************"+jdbcUrl);
        
        configuration.setProperty("hibernate.connection.username", System.getenv("RDS_USERNAME"));
        configuration.setProperty("hibernate.connection.password", System.getenv("RDS_PASSWORD"));
        configuration.addAnnotatedClass(Task.class);
        configuration.addAnnotatedClass(Epic.class);
        configuration.addAnnotatedClass(Story.class);
        configuration.configure();
        log.info("*********Configured******");
        
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        log.info("*******Getting serviceRegistry *************");
        try {
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
        return sessionFactory;
    }
}
