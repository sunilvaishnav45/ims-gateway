package apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration
{
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000",
                        "https://lalittanwar.github.io","https://sunilvaishnav45.github.io")
                        .allowedMethods("GET")
                        .allowedMethods("POST")
                        .allowedMethods("PUT")
                        .allowedMethods("DELETE");
            }
        };
    }
}
