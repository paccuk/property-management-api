//package org.sandw.propertymanagementapi.modules.Properties.Domain;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.*;
//import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
//import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.*;
//
//import java.util.UUID;
//
//class PropertyTest {
//    @Test
//    void updateName_isSuccessful() {
//        var property = new PropertyBuilder().build();
//
//        String newName = "Updated name";
//        property.updateName(newName);
//
//        var propertyNameUpdated = TestBase.assertPublishedDomainEvent(property, PropertyNameUpdatedDomainEvent.class);
//
//        Assertions.assertEquals(newName, propertyNameUpdated.getName());
//    }
//
//    @Test
//    void updateLocation_isSuccessful() {
//        var property = new PropertyBuilder().build();
//
//        Location newLocation = Location.of("321 Main St", "Sometown", "54321");
//        property.updateLocation(newLocation);
//
//        var propertyLocationUpdated = TestBase.assertPublishedDomainEvent(property, PropertyLocationUpdatedDomainEvent.class);
//
//        Assertions.assertEquals(newLocation, propertyLocationUpdated.getLocation());
//    }
//
//    @Test
//    void updateType_isSuccessful() {
//        var property = new PropertyBuilder().build();
//
//        PropertyType newType = PropertyType.of("House");
//        property.updateType(newType);
//
//        var propertTypeUpdated = TestBase.assertPublishedDomainEvent(property, PropertyTypeUpdatedDomainEvent.class);
//
//        Assertions.assertEquals(newType, propertTypeUpdated.getPropertyType());
//    }
//
//    @Test
//    void updateRentalLimit_isSuccessful() {
//        var property = new PropertyBuilder().build();
//
//        RentalPlacesLimit newRentalLimit = RentalPlacesLimit.of(5);
//        property.updateRentalLimit(newRentalLimit);
//
//        var propertyRentalLimitUpdated = TestBase.assertPublishedDomainEvent(property, PropertyRentalLimitUpdatedDomainEvent.class);
//
//
//        Assertions.assertEquals(newRentalLimit, propertyRentalLimitUpdated.getRentalLimit());
//    }
//
//    @Test
//    void updateStatus_isSuccessful() {
//        var property = new PropertyBuilder().build();
//
//        PropertyStatus newStatus = PropertyStatus.FullyOccupied;
//        property.updateStatus(newStatus);
//
//        var propertyStatusChanged = TestBase.assertPublishedDomainEvent(property, PropertyStatusChangedDomainEvent.class);
//
//        Assertions.assertEquals(newStatus, propertyStatusChanged.getStatus());
//    }
//
//
//    static class PropertyBuilder {
//        private OwnerId ownerId = new OwnerId(UUID.randomUUID());
//        private String name = "Apartment in Main Street";
//        private Location location = Location.of("123 Main St", "Anytown", "12345");
//        private PropertyType type = PropertyType.of("Apartment");
//        private RentalPlacesLimit rentalLimit = RentalPlacesLimit.of(3);
//
//
//        public PropertyBuilder withOwnerId(OwnerId ownerId) {
//            this.ownerId = ownerId;
//            return this;
//        }
//
//        public PropertyBuilder withName(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public PropertyBuilder withLocation(Location location) {
//            this.location = location;
//            return this;
//        }
//
//        public PropertyBuilder withType(PropertyType type) {
//            this.type = type;
//            return this;
//        }
//
//        public PropertyBuilder withRentalLimit(RentalPlacesLimit rentalLimit) {
//            this.rentalLimit = rentalLimit;
//            return this;
//        }
//
//        public Property build() {
//            var property = Property.createNew(this.ownerId, this.name, this.location, this.type, this.rentalLimit);
//
//            DomainEventsTestHelper.clearAllDomainEvents(property);
//
//            return property;
//        }
//    }
//}
