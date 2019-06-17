package com.nixsolutions.ppp.config;

import com.nixsolutions.ppp.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.activation.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class HibernateUtil {
    private Server server;
    private static final Logger log = LogManager.getLogger(HibernateUtil.class.getName());
    private final StandardServiceRegistry serviceRegistry;
    private final SessionFactory sessionFactory;

    public HibernateUtil() {
        try {
            server = Server.createTcpServer().start();
        } catch (SQLException e) {
            log.error(e);
        }
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Genre.class);
        configuration.addAnnotatedClass(Review.class);
        configuration.addAnnotatedClass(RentedBook.class);
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Reservation.class);
        configuration.configure(new File("src/main/resources/hibernate.cfg.xml"));
        serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties())
                .build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    public HibernateUtil(String confPath) {
        try {
            server = Server.createTcpServer().start();
        } catch (SQLException e) {
            log.error(e);
        }
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Genre.class);
        configuration.addAnnotatedClass(Review.class);
        configuration.addAnnotatedClass(RentedBook.class);
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.configure(new File(confPath));
        serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties())
                .build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Bean
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
