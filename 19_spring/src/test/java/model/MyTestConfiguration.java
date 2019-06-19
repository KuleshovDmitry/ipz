package model;

import configration.HibernateUtil;
import model.dao.impl.RoleDaoImpl;
import model.dao.UserDao;
import model.dao.impl.UserDaoImpl;
import org.h2.tools.Server;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootConfiguration
public class MyTestConfiguration {
    @Bean
    public Server getServer() throws SQLException {
        return Server.createTcpServer();

    }
    @Bean
    public HibernateUtil getHibernateUtil() {
        return new HibernateUtil("src/test/java/hibernate.cfg.xml");
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Bean
    public RoleDaoImpl getRoleDao() {
        return new RoleDaoImpl();
    }

}
