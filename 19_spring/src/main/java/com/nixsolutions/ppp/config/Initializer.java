package com.nixsolutions.ppp.config;

import com.nixsolutions.ppp.model.dao.impl.RoleDaoImpl;
import com.nixsolutions.ppp.service.UserDetailsServiceImpl;
import com.nixsolutions.ppp.model.dao.impl.UserDaoImpl;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class Initializer implements WebApplicationInitializer {
   private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        ctx.register(SecurityConfig.class);
        ctx.register(UserDetailsServiceImpl.class);
        ctx.register(UserDaoImpl.class);
        ctx.register(RoleDaoImpl.class);
        servletContext.addListener(new ContextLoaderListener(ctx));
        System.out.println("initializer");


        ctx.setServletContext(servletContext);

        Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));


        servlet.addMapping("/");
        servlet.addMapping("/hello");
        servlet.setLoadOnStartup(1);

    }

}
