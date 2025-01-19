package org.sandw.propertymanagementapi;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.shared.Configurations.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PropertyManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyManagementApiApplication.class, args);
    }

	@Bean
    EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return applicationEventPublisher::publishEvent;
    }
}
