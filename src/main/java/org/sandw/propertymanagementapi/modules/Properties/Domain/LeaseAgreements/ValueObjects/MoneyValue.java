package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects;

import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
public class MoneyValue {

    @With
    Double value;

    @With
    String currency;

    private MoneyValue(@NonNull Double value, @NonNull String currency) {
        this.value = value;
        this.currency = currency;
    }

    public static MoneyValue of(@NonNull Double value, @NonNull String currency) {
        return new MoneyValue(value, currency);
    }

    public static MoneyValue multiplyByInteger(MoneyValue moneyValue, Integer multiplier) {
        return MoneyValue.of(moneyValue.getValue() * multiplier, moneyValue.getCurrency());
    }
}
