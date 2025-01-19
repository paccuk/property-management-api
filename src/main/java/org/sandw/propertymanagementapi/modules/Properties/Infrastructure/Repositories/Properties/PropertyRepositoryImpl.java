package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Repositories.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.OwnerId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Mappers.Properties.PropertyMapper;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.RowMappers.Properties.PropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PropertyRepositoryImpl implements PropertyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public PropertyRepositoryImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }


    @Override
    public Property save(Property property) {
        var propertyDto = PropertyMapper.toDto(property);

        Map<String, Object> params = new HashMap<>();
        params.put("property_id", propertyDto.id());
        params.put("owner_id", propertyDto.ownerId());
        params.put("name", propertyDto.name());
        params.put("location_address", propertyDto.locationAddress());
        params.put("location_postal_code", propertyDto.locationPostalCode());
        params.put("location_city", propertyDto.locationCity());
        params.put("type", propertyDto.type());
        params.put("status", propertyDto.status());
        params.put("rental_limit", propertyDto.rentalLimit());
        params.put("created_date", propertyDto.createdDate());

        simpleJdbcInsert.withTableName("property").execute(params);

        return property;
    }

    @Override
    public Optional<Property> update(Property property) {
        var propertyDto = PropertyMapper.toDto(property);

        String query = """
                UPDATE property SET
                name = ?,
                location_address = ?,
                location_postal_code = ?,
                location_city = ?,
                type = ?,
                status = ?,
                rental_limit = ?
                WHERE id = ?""";

        Object[] params = {
                propertyDto.name(),
                propertyDto.locationAddress(),
                propertyDto.locationPostalCode(),
                propertyDto.locationCity(),
                propertyDto.type(),
                propertyDto.status(),
                propertyDto.rentalLimit(),
                propertyDto.id()
        };

        jdbcTemplate.update(query, params);

        return Optional.of(property);
    }

    @Override
    public void deleteById(PropertyId propertyId) {
        String query = "DELETE FROM property WHERE property_id = ?";

        jdbcTemplate.update(query, propertyId);
    }

    @Override
    public Optional<Property> findById(PropertyId propertyId) {
        String query = "SELECT * FROM property WHERE property_id = ?";

        var propertyDto = Optional.ofNullable(
                jdbcTemplate.queryForObject(query, new PropertyRowMapper(), propertyId.getValue())
        );

        return propertyDto.map(PropertyMapper::toDomain);
    }

    @Override
    public Optional<Property> findByOwnerId(OwnerId ownerId) {
        String query = "SELECT * FROM property WHERE owner_id = ?";

        var propertyDto = Optional.ofNullable(
                jdbcTemplate.queryForObject(query, new PropertyRowMapper(), ownerId.getValue())
        );

        return propertyDto.map(PropertyMapper::toDomain);
    }
}
