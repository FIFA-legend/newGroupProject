package by.clowns.config;

import by.clowns.formatters.RegionFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"by.clowns.controllers", "by.clowns.formatters"})
@PropertySource("classpath:config.properties")
@Import({ThymeleafConfig.class, InternationalizationConfig.class})
public class WebConfiguration extends WebMvcConfigurerAdapter {

    private RegionFormatter regionFormatter;

    private LocaleChangeInterceptor interceptor;

    @Autowired
    public void setRegionFormatter(RegionFormatter regionFormatter) {
        this.regionFormatter = regionFormatter;
    }

    @Autowired
    public void setInterceptor(LocaleChangeInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(regionFormatter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/WEB-INF/static/css/");
    }
}
