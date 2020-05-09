package apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer
{
    @Value("#{'${allowed.cross.origin}'.split(',')}")
    private List<String> allowedCrossOrigin;

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(getOrigin())
                        .allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }

    public String[] getOrigin() {
        int size = allowedCrossOrigin.size();
        String[] originArray = new String[size];
        return allowedCrossOrigin.toArray(originArray);
    }
}
