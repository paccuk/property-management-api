package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects;

import lombok.*;

@Value
public class LeaseStatus {

    public static final LeaseStatus Active = new LeaseStatus("Active");
    public static final LeaseStatus Terminated = new LeaseStatus("Terminated");

    @With
    String value;

    private LeaseStatus(@NonNull String value) {
        this.value = value;
    }

    public static LeaseStatus of(@NonNull String value) {
        return new LeaseStatus(value); // TODO: Handle if given status not exist.
    }
}
