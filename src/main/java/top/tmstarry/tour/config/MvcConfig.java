package top.tmstarry.tour.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            /*@Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/404").setViewName("404");
                registry.addViewController("/500").setViewName("500");
                registry.addViewController("/index").setViewName("index");
                registry.addViewController("/manage").setViewName("manage");
                registry.addViewController("/login").setViewName("login");
            }*/
        };
    }

}
