package org.sandw.propertymanagementapi.modules.shared.Transactions;

@FunctionalInterface
public interface TransactionRunnableWithResult<R> {
    R run();
}
