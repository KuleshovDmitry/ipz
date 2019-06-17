package com.nixsolutions.ppp.model;

import com.nixsolutions.ppp.config.HibernateUtil;
import com.nixsolutions.ppp.model.dao.impl.RoleDaoImpl;
import com.nixsolutions.ppp.model.dao.UserDao;
import com.nixsolutions.ppp.model.dao.impl.UserDaoImpl;
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
