package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Configurations.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties.CreatePropertyUseCase;
import org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties.GetPropertyByIdUseCase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("propertyUseCasesConfiguration")
public class PropertyUseCasesConfig {

    private final PropertyRepository propertyRepository;

    public PropertyUseCasesConfig(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Bean
    public GetPropertyByIdUseCase getPropertyByIdUseCase() {
        return new GetPropertyByIdUseCase(propertyRepository);
    }

    @Bean
    public CreatePropertyUseCase createPropertyUseCase() {
        return new CreatePropertyUseCase(propertyRepository);
    }
}
