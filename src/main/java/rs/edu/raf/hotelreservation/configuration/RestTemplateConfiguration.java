package rs.edu.raf.hotelreservation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfiguration {
    @Bean("userServiceTemplate")
    public RestTemplate getUserServiceTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8081"));
        return restTemplate;
    }

    @Bean("notificationServiceTemplate")
    public RestTemplate getNotificationServiceTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8082"));
        return restTemplate;
    }
}
