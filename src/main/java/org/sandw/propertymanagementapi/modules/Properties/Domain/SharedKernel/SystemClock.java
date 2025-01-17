package org.sandw.propertymanagementapi.modules.Properties.Domain.SharedKernel;

import java.time.LocalDateTime;

public class SystemClock {
    private static LocalDateTime customDateTime;

    public static LocalDateTime now() {
        if (customDateTime != null) {
            return customDateTime;
        }
        return LocalDateTime.now();
    }

    public static void set(LocalDateTime customDateTime) {
        SystemClock.customDateTime = customDateTime;
    }

    public static void reset() {
        customDateTime = null;
    }
}
