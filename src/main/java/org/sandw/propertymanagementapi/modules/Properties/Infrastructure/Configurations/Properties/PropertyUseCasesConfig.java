package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Configurations.Properties;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties.*;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("propertyUseCasesConfiguration")
public class PropertyUseCasesConfig {
    private final PropertyRepository propertyRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public PropertyUseCasesConfig(
            PropertyRepository propertyRepository,
            TransactionManager transactionManager,
            EventPublisher eventPublisher
            ) {
        this.propertyRepository = propertyRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    @Bean
    public CreatePropertyUseCase createPropertyUseCase() {
        return new CreatePropertyUseCase(propertyRepository, transactionManager, eventPublisher);
    }

    @Bean
    public DeletePropertyByIdUseCase deletePropertyByIdUseCase() {
        return new DeletePropertyByIdUseCase(propertyRepository, transactionManager, eventPublisher);
    }

    @Bean
    public UpdatePropertyByIdUseCase updatePropertyByIdUseCase() {
        return new UpdatePropertyByIdUseCase(propertyRepository, transactionManager, eventPublisher);
    }

    @Bean
    public GetPropertyByIdUseCase getPropertyByIdUseCase() {
        return new GetPropertyByIdUseCase(propertyRepository);
    }

    @Bean
    public GetPropertyByOwnerIdUseCase getPropertyByOwnerIdUseCase() {
        return new GetPropertyByOwnerIdUseCase(propertyRepository);
    }
}
