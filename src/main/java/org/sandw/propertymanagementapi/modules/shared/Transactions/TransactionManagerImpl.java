package org.sandw.propertymanagementapi.modules.shared.Transactions;

import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.atomic.AtomicReference;

public class TransactionManagerImpl implements TransactionManager {

    private final TransactionTemplate transactionTemplate;
    private final TransactionTemplate readOnlyTransactionTemplate;

    public TransactionManagerImpl(TransactionTemplate transactionTemplate, TransactionTemplate readOnlyTransactionTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.readOnlyTransactionTemplate = readOnlyTransactionTemplate;
    }


    @Override
    public void rollback() {
        try {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            // TODO: logs
        } catch (NoTransactionException e) {
            // TODO: do something
        }
    }

    @Override
    public void doInTransaction(boolean readOnly, TransactionRunnableWithoutResult runnableWithoutResult) {
        if (readOnly) {
            readOnlyTransactionTemplate.executeWithoutResult(transactionStatus -> runnableWithoutResult.run());
        } else {
            transactionTemplate.executeWithoutResult(transactionStatus -> runnableWithoutResult.run());
        }
    }

    @Override
    public <R> R doInTransactionWithResult(boolean readOnly, TransactionRunnableWithResult<R> runnableWithResult) {
        if (readOnly) {
            return readOnlyTransactionTemplate.execute(transactionStatus -> runnableWithResult.run());
        } else {
            return transactionTemplate.execute(transactionStatus -> runnableWithResult.run());
        }
    }

    @Override
    public void doAfterCommit(TransactionRunnableWithoutResult runnableWithoutResult) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            runnableWithoutResult.run();
            return;
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED) {
                    runnableWithoutResult.run();
                }
            }
        });
    }

    @Override
    public <R> R doAfterCommitWithResult(TransactionRunnableWithResult<R> runnableWithResult) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            return runnableWithResult.run();
        }

        final AtomicReference<R> result = new AtomicReference<>();

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED) {
                    result.set(runnableWithResult.run());
                }
            }
        });

        return result.get();
    }

    @Override
    public void doAfterRollback(TransactionRunnableWithoutResult runnableWithoutResult) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            runnableWithoutResult.run();
        } else {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCompletion(int status) {
                    if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                        runnableWithoutResult.run();
                    }
                }
            });
        }
    }

    @Override
    public <R> R doAfterRollbackWithResult(TransactionRunnableWithResult<R> runnableWithResult) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            return runnableWithResult.run();
        }

        final AtomicReference<R> result = new AtomicReference<>();

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                    result.set(runnableWithResult.run());
                }
            }
        });

        return result.get();

    }
}
