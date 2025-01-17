package org.sandw.propertymanagementapi.modules.shared.Transactions;


public interface TransactionManager {

    void rollback();

    default void doInTransaction(TransactionRunnableWithoutResult runnableWithoutResult) {
        doInTransaction(false, runnableWithoutResult);
    }

    void doInTransaction(boolean readOnly, TransactionRunnableWithoutResult runnableWithoutResult);

    default <R> R doInTransactionWithResult(TransactionRunnableWithResult<R> runnableWithResult) {
        return doInTransactionWithResult(false, runnableWithResult);
    }

    <R> R doInTransactionWithResult(boolean readOnly, TransactionRunnableWithResult<R> runnableWithResult);

    void doAfterCommit(TransactionRunnableWithoutResult runnableWithoutResult);

    <R> R doAfterCommitWithResult(TransactionRunnableWithResult<R> runnableWithResult);

    void doAfterRollback(TransactionRunnableWithoutResult runnableWithoutResult);

    <R> R doAfterRollbackWithResult(TransactionRunnableWithResult<R> runnableWithResult);
}
