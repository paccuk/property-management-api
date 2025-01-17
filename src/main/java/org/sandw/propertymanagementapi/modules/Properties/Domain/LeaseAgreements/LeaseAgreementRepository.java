package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements;

import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;

import java.util.Optional;

public interface LeaseAgreementRepository {
    void save(LeaseAgreement leaseAgreement);
    Optional<LeaseAgreement> findById(LeaseAgreementId leaseAgreementId);
}
