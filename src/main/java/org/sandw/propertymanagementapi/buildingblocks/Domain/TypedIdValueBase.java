package org.sandw.propertymanagementapi.buildingblocks.Domain;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class TypedIdValueBase { // TODO: Refactor to use lombok's approach
    private final UUID value;

    public TypedIdValueBase(UUID value) {
        if (value == null || value.equals(new UUID(0, 0))) {
            throw new IllegalArgumentException("Id value cannot be empty!");
        }
        this.value = value;
    }

    public boolean equals(TypedIdValueBase other) {
        return Objects.equals(this.value, other.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        TypedIdValueBase other = (TypedIdValueBase) obj;
        return this.equals(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static boolean isEqual(TypedIdValueBase obj1, TypedIdValueBase obj2) {
        return obj1.equals(obj2);
    }

    public static boolean isNotEqual(TypedIdValueBase obj1, TypedIdValueBase obj2) {
        return !isEqual(obj1, obj2);
    }
}
