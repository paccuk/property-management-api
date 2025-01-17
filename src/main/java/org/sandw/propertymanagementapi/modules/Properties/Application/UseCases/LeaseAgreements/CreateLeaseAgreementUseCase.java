package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.LeaseAgreements;

import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.LeaseAgreement;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.LeaseAgreementRepository;


public class CreateLeaseAgreementUseCase {
    private final LeaseAgreementRepository leaseAgreementRepository;

    public CreateLeaseAgreementUseCase(LeaseAgreementRepository leaseAgreementRepository) {
        this.leaseAgreementRepository = leaseAgreementRepository;
    }

    public void execute(LeaseAgreement leaseAgreement) {
        leaseAgreementRepository.save(leaseAgreement);
    }
}


