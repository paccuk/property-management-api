package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.AggregateRoot;
import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.RentFee;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Owners.OwnerId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.SharedKernel.SystemClock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Getter
public class LeaseAgreement extends Entity implements AggregateRoot {
    //    private final ArrayList<AgreementMember> members;
    //    private LessorId lessorId;

    private final LeaseAgreementId id;
    private final PropertyId propertyId;

    private final ArrayList<Tenant> tenants;

    private RentFee monthlyRentFee; // TODO: change class name
    private Boolean isRentedOut;

    private final LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private LocalDateTime leaseStartDate;
    private LocalDateTime leaseEndDate;


    private LeaseAgreement(PropertyId propertyId, OwnerId ownerId, LocalDateTime leaseStartDate, LocalDateTime leaseEndDate) {
        this.id = new LeaseAgreementId(UUID.randomUUID());
        this.propertyId = propertyId;
        this.ownerId = ownerId;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.createdDate = SystemClock.now();
        this.tenants = new ArrayList<>();
    }

    public static LeaseAgreement createNew(PropertyId propertyId, OwnerId ownerId, LocalDateTime leaseStartDate, LocalDateTime leaseEndDate) {
        return new LeaseAgreement(propertyId, ownerId, leaseStartDate, leaseEndDate);
    }

    public void updateLeaseDates(LocalDateTime leaseStartDate, LocalDateTime leaseEndDate) {
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.modifiedDate = SystemClock.now();
    }

    public void addTenant(Tenant tenant) {
    }
}
