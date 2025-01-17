package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.AggregateRoot;
import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.*;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.*;
import org.sandw.propertymanagementapi.modules.Properties.Domain.SharedKernel.SystemClock;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Property extends Entity implements AggregateRoot {
    private final PropertyId id;
    private final OwnerId ownerId;

    private String name;
    private Location location;
    private PropertyType type;
    private PropertyStatus status;
    private RentalPlacesLimit rentalLimit;

    private final LocalDateTime createdDate;


    public static Property createNew(
            OwnerId ownerId,
            String name,
            Location location,
            PropertyType propertyType,
            RentalPlacesLimit rentalLimit
    ) {
        return new Property(
                new PropertyId(UUID.randomUUID()),
                ownerId,
                SystemClock.now(),
                name,
                location,
                propertyType,
                PropertyStatus.Available,
                rentalLimit
        );
    }

    public static Property createNew(
            PropertyId propertyId,
            OwnerId ownerId,
            LocalDateTime createdDate,
            String name,
            Location location,
            PropertyType propertyType,
            PropertyStatus propertyStatus,
            RentalPlacesLimit rentalLimit
    ) {
        return new Property(
                propertyId,
                ownerId,
                createdDate,
                name,
                location,
                propertyType,
                propertyStatus,
                rentalLimit
        );
    }

    private Property(
            PropertyId propertyId,
            OwnerId ownerId,
            LocalDateTime createdDate,
            String name,
            Location location,
            PropertyType propertyType,
            PropertyStatus propertyStatus,
            RentalPlacesLimit rentalLimit
    ) {
        this.id = propertyId;
        this.ownerId = ownerId;
        this.name = name;
        this.location = location;
        this.type = propertyType;
        this.status = propertyStatus;
        this.rentalLimit = rentalLimit;
        this.createdDate = createdDate;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateLocation(Location location) {
        this.location = location;
    }

    public void updateType(PropertyType propertyType) {
        this.type = propertyType;
    }

    public void updateRentalLimit(RentalPlacesLimit rentalLimit) {
        this.rentalLimit = rentalLimit;
    }

    public void updateStatus(PropertyStatus status) {
        this.status = status;
    }

//    public LeaseAgreement createLeaseAgreement(LocalDateTime startDate, LocalDateTime endDate, RentFee monthlyRent) {
//        this.checkRule(new PropertyMustAllowNewLeaseRule(this.status));
//
//        var leaseAgreement = LeaseAgreement.createNew(this.id, ownerId, startDate, endDate, monthlyRent);
//
//        this.leaseAgreements.add(leaseAgreement);
//
//        this.updateStatus();
//
//        return leaseAgreement;
//    }
//
//    public void updateStatus() { // TODO: Звернути увагу при написанні функціоналу історій договорів найму
//        var countOfActiveLeaseAgreements = this.leaseAgreements.stream().filter(leaseAgreement -> leaseAgreement.getStatus == LeaseStatus.Active).count();
//
//        if (countOfActiveLeaseAgreements == 0) {
//            this.status = PropertyStatus.Available;
//        } else if (countOfActiveLeaseAgreements == this.rentalLimit.getValue()) {
//            this.status = PropertyStatus.FullyOccupied;
//        } else {
//            this.status = PropertyStatus.PartiallyOccupied;
//        }
//
//        this.addDomainEvent(new PropertyStatusChangedDomainEvent(this.id));
//    }
//
//    public void terminateLeaseAgreement(LeaseAgreementId leaseAgreementId, String reason) {
//        var properLeaseAgreement = this.leaseAgreements.stream().map(leaseAgreement -> leaseAgreement.getId() == leaseAgreementId && leaseAgreement.getStatus() == LeaseStatus.Active);
//
//        this.checkRule(new PropertyLeaseAgreementMustExistRule(properLeaseAgreement));
//
//        properLeaseAgreement.Terminate(reason);
//
//        this.updateStatus();
//    }

}
