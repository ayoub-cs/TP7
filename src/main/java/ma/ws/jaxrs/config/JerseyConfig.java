// src/main/java/ma/ws/jaxrs/config/JerseyConfig.java
package ma.ws.jaxrs.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig {

    @Bean
    public ServletRegistrationBean<ServletContainer> jerseyServlet() {
        ResourceConfig config = new ResourceConfig();
        config.packages("ma.ws.jaxrs.controllers"); // Scan des @Path
        config.register(org.glassfish.jersey.server.spring.SpringComponentProvider.class); // INTÉGRATION SPRING

        ServletRegistrationBean<ServletContainer> reg =
                new ServletRegistrationBean<>(new ServletContainer(config), "/api/*");
        reg.setLoadOnStartup(1);
        return reg;
    }
}