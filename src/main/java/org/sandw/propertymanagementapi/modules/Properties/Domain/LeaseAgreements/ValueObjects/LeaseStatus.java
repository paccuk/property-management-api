package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects;

import lombok.*;

@Value
public class LeaseStatus {

    public static final LeaseStatus Active = new LeaseStatus("Active");
    public static final LeaseStatus Terminated = new LeaseStatus("Terminated");

    @With
    @Getter(AccessLevel.PRIVATE)
    String status;

    private LeaseStatus(@NonNull String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
