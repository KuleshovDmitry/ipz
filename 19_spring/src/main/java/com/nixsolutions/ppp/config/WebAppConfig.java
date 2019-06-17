package com.nixsolutions.ppp.config;

import com.nixsolutions.ppp.model.dao.AuthorDao;
import com.nixsolutions.ppp.model.dao.GenreDao;
import com.nixsolutions.ppp.model.dao.impl.AuthorDaoImpl;
import com.nixsolutions.ppp.model.dao.impl.GenreDaoImpl;
import com.nixsolutions.ppp.service.RoleService;
import com.nixsolutions.ppp.service.UserDetailsServiceImpl;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.nixsolutions.ppp")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/WEB-INF/pages/");
    }


    @Bean
    public HibernateUtil getHibernateUtil() {
        return new HibernateUtil();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Autowired HibernateUtil hibernateUtil) {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(hibernateUtil.getSessionFactory());
        return transactionManager;
    }


    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public static DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:tcp://localhost:9092/~/kuleshov");
        dataSource.setUser("dk");
        dataSource.setPassword("");
        return dataSource;
    }
}

