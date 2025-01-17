package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.LeaseAgreements;

import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.LeaseAgreement;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.LeaseAgreementRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;


public class GetLeaseAgreementByIdUseCase {
    private final LeaseAgreementRepository leaseAgreementRepository;

    public GetLeaseAgreementByIdUseCase(LeaseAgreementRepository leaseAgreementRepository) {
        this.leaseAgreementRepository = leaseAgreementRepository;
    }

    public LeaseAgreement execute(LeaseAgreementId leaseAgreementId) {
        return leaseAgreementRepository.findById(leaseAgreementId).orElseThrow(); // TODO: Handle null exceptions
    }

}
