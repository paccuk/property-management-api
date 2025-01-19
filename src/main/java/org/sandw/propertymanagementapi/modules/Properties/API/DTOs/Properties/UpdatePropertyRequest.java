package org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties;


import lombok.Getter;

import java.util.Optional;

@Getter
public class UpdatePropertyRequest {
    private final Optional<String> name = Optional.empty();
    private final Optional<String> locationAddress = Optional.empty();
    private final Optional<String> locationCity = Optional.empty();
    private final Optional<String> locationPostalCode = Optional.empty();
    private final Optional<String> type = Optional.empty();
    private final Optional<String> status = Optional.empty();
    private final Optional<Integer> rentalLimit = Optional.empty();
}
