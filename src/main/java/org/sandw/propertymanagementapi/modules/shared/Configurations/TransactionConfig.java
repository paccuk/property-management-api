package org.sandw.propertymanagementapi.modules.shared.Configurations;

import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration("transactionConfiguration")
public class TransactionConfig {

    private final TransactionTemplate transactionTemplate;
    private final TransactionTemplate readOnlyTransactionTemplate;

    public TransactionConfig(TransactionTemplate transactionTemplate, TransactionTemplate readOnlyTransactionTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.readOnlyTransactionTemplate = readOnlyTransactionTemplate;
    }

    @Bean(name = "customTransactionManager")
    public TransactionManager transactionManager() {
        return new TransactionManagerImpl(transactionTemplate, readOnlyTransactionTemplate);
    }
}
