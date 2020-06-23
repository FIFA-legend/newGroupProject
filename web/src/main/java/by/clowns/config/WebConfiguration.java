package by.clowns.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.clowns.controllers")
@PropertySource("classpath:config.properties")
@Import({ThymeleafConfig.class, InternationalizationConfig.class})
public class WebConfiguration {

}
