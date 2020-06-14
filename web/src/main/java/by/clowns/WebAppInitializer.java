package by.clowns;

import by.clowns.config.SecurityConfiguration;
import by.clowns.config.ServiceConfiguration;
import by.clowns.config.WebConfiguration;
import by.clowns.configuration.DaoConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfiguration.class, ServiceConfiguration.class, DaoConfiguration.class, SecurityConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
