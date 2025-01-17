package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Mappers.LeaseAgreements;

import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.LeaseAgreement;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.*;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.DTOs.LeaseAgreements.LeaseAgreementDto;

public class LeaseAgreementMapper {
    public static LeaseAgreement toDomain(LeaseAgreementDto leaseAgreementDto) {
        return LeaseAgreement.createNew(
                new LeaseAgreementId(leaseAgreementDto.id()),
                new PropertyId(leaseAgreementDto.propertyId()),
                new LessorId(leaseAgreementDto.lessorId()),
                new TenantId(leaseAgreementDto.tenantId()),
                MoneyValue.of(leaseAgreementDto.monthlyRentFee(), leaseAgreementDto.currency()),
                LeaseStatus.of(leaseAgreementDto.status()),
                leaseAgreementDto.createdDate(),
                leaseAgreementDto.modifiedDate(),
                leaseAgreementDto.leaseStartDate(),
                leaseAgreementDto.leaseEndDate()
        );
    }

    public static LeaseAgreementDto toDto(LeaseAgreement leaseAgreement) {
        return new LeaseAgreementDto(
                leaseAgreement.getId().getValue(),
                leaseAgreement.getPropertyId().getValue(),
                leaseAgreement.getLessorId().getValue(),
                leaseAgreement.getTenantId().getValue(),
                leaseAgreement.getMonthlyRentFee().getValue(),
                leaseAgreement.getMonthlyRentFee().getCurrency(),
                leaseAgreement.getStatus().getValue(),
                leaseAgreement.getCreatedDate(),
                leaseAgreement.getModifiedDate(),
                leaseAgreement.getLeaseStartDate(),
                leaseAgreement.getLeaseEndDate()
        );
    }
}
