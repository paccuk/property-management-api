package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties;

import org.sandw.propertymanagementapi.buildingblocks.Domain.AggregateRoot;
import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.LeaseAgreement;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseStatus;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.RentFee;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Owners.OwnerId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.PropertyCreatedDomainEvent;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.PropertyStatusChangedDomainEvent;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.PropertyGeneralAttributesEditedDomainEvent;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Rules.PropertyMustAllowNewLeaseRule;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.*;
import org.sandw.propertymanagementapi.modules.Properties.Domain.SharedKernel.SystemClock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Property extends Entity implements AggregateRoot {

    private final PropertyId id;
    private final OwnerId ownerId;

    private final LocalDateTime createdDate;

    private final ArrayList<LeaseAgreement> leaseAgreements; // TODO: Можливо краще взагалі не зберігати договори.

    private String name;
    private Location location;
    private PropertyType propertyType; // TODO
    private PropertyStatus status; // TODO
    private RentalPlacesLimit rentalLimit; // TODO


    public static Property createNew(OwnerId ownerId, String name, Location address, PropertyType propertyType, RentalPlacesLimit rentalLimit) {
        return new Property(ownerId, name, address, propertyType, rentalLimit);
    }

    private Property(OwnerId ownerId, String name, Location address, PropertyType propertyType, RentalPlacesLimit rentalLimit) {
        this.id = new PropertyId(UUID.randomUUID());
        this.leaseAgreements = new ArrayList<>();
        this.createdDate = SystemClock.now();

        this.status = PropertyStatus.Available;

        this.ownerId = ownerId;
        this.name = name;
        this.location = address;
        this.propertyType = propertyType;
        this.rentalLimit = rentalLimit;

        this.addDomainEvent(new PropertyCreatedDomainEvent(this.id, ownerId));
    }

    public void editGeneralAttributes(String title, Location address, PropertyType propertyType, RentalPlacesLimit rentalLimit) {

        this.name = title;
        this.location = address;
        this.propertyType = propertyType;
        this.rentalLimit = rentalLimit;

        this.addDomainEvent(new PropertyGeneralAttributesEditedDomainEvent(this.id, this.name, this.location, this.propertyType, this.rentalLimit));
    }

    public LeaseAgreement createLeaseAgreement(OwnerId ownerId, LocalDateTime startDate, LocalDateTime endDate, RentFee monthlyRent) {
        this.checkRule(new PropertyMustAllowNewLeaseRule(this.status));

        var leaseAgreement = LeaseAgreement.createNew(this.id, ownerId, startDate, endDate, monthlyRent);

        this.leaseAgreements.add(leaseAgreement);

        this.updateStatus();

        return leaseAgreement;
    }

    public void updateStatus() { // TODO: Звернути увагу при написанні функціоналу історій договорів найму
        var countOfActiveLeaseAgreements = this.leaseAgreements.stream().filter(leaseAgreement -> leaseAgreement.getStatus == LeaseStatus.Active).count();

        if (countOfActiveLeaseAgreements == 0) {
            this.status = PropertyStatus.Available;
        } else if (countOfActiveLeaseAgreements == this.rentalLimit.getValue()) {
            this.status = PropertyStatus.FullyOccupied;
        } else {
            this.status = PropertyStatus.PartiallyOccupied;
        }

        this.addDomainEvent(new PropertyStatusChangedDomainEvent(this.id));
    }

    public void terminateLeaseAgreement(LeaseAgreementId leaseAgreementId, String reason) {
        var properLeaseAgreement = this.leaseAgreements.stream().map(leaseAgreement -> leaseAgreement.getId() == leaseAgreementId && leaseAgreement.getStatus() == LeaseStatus.Active);

        this.checkRule(new PropertyLeaseAgreementMustExistRule(properLeaseAgreement));

        properLeaseAgreement.Terminate(reason);

        this.updateStatus();
    }

}
