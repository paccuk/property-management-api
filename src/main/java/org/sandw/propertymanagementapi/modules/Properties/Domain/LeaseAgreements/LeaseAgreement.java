package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.AggregateRoot;
import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Events.*;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Rules.*;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.*;
import org.sandw.propertymanagementapi.modules.Properties.Domain.SharedKernel.SystemClock;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class LeaseAgreement extends Entity implements AggregateRoot {
    private final LeaseAgreementId id;
    private final PropertyId propertyId;
    private final LessorId lessorId;
    private final TenantId tenantId;

    private MoneyValue monthlyRentFee;
    private LeaseStatus status;

    private final LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private LocalDateTime leaseStartDate;
    private LocalDateTime leaseEndDate;


    public static LeaseAgreement createNew(
            PropertyId propertyId,
            LessorId lessorId,
            TenantId tenantId,
            MoneyValue monthlyRentFee,
            LocalDateTime leaseStartDate,
            LocalDateTime leaseEndDate
    ) {
        return new LeaseAgreement(
                new LeaseAgreementId(UUID.randomUUID()),
                propertyId,
                lessorId,
                tenantId,
                monthlyRentFee,
                LeaseStatus.Active,
                SystemClock.now(),
                SystemClock.now(),
                leaseStartDate,
                leaseEndDate
        );
    }

    public static LeaseAgreement createNew(
            LeaseAgreementId leaseAgreementId,
            PropertyId propertyId,
            LessorId lessorId,
            TenantId tenantId,
            MoneyValue monthlyRentFee,
            LeaseStatus status,
            LocalDateTime createdDate,
            LocalDateTime modifiedDate,
            LocalDateTime leaseStartDate,
            LocalDateTime leaseEndDate
    ) {
        return new LeaseAgreement(
                leaseAgreementId,
                propertyId,
                lessorId,
                tenantId,
                monthlyRentFee,
                status,
                createdDate,
                modifiedDate,
                leaseStartDate,
                leaseEndDate
        );
    }

    private LeaseAgreement(
            LeaseAgreementId leaseAgreementId,
            PropertyId propertyId,
            LessorId lessorId,
            TenantId tenantId,
            MoneyValue monthlyRentFee,
            LeaseStatus status,
            LocalDateTime createdDate,
            LocalDateTime modifiedDate,
            LocalDateTime leaseStartDate,
            LocalDateTime leaseEndDate
    ) {
        this.id = leaseAgreementId;
        this.propertyId = propertyId;
        this.lessorId = lessorId;
        this.tenantId = tenantId;
        this.monthlyRentFee = monthlyRentFee;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
    }

    public void terminate(String reason) {
        this.checkRule(new LeaseAgreementCannotBeTerminatedTwice(this.status));

        this.status = LeaseStatus.Terminated;
    }

    public void updateLeaseStartDate(LocalDateTime leaseStartDate) {
        this.checkRule(new LeaseStartDateMustBeBeforeEndDateRule(this.leaseEndDate, leaseStartDate));

        this.leaseStartDate = leaseStartDate;
        this.modifiedDate = SystemClock.now();
    }

    public void updateLeaseEndDate(LocalDateTime leaseEndDate) {
        this.checkRule(new LeaseEndDateMustBeAfterStartDateRule(this.leaseStartDate, leaseEndDate));

        this.leaseEndDate = leaseEndDate;
        this.modifiedDate = SystemClock.now();
    }

    public void updateMonthlyRentFee(MoneyValue monthlyRentFee) {
        this.monthlyRentFee = monthlyRentFee;
        this.modifiedDate = SystemClock.now();
    }
}
