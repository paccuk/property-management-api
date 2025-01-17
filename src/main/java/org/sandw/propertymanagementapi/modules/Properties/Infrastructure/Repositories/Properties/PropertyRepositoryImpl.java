package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Repositories.Properties;


import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Mappers.Properties.PropertyMapper;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.RowMappers.Properties.PropertyRowMapper;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PropertyRepositoryImpl implements PropertyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final TransactionManager transactionManager;

    public PropertyRepositoryImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, TransactionManager transactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.transactionManager = transactionManager;
    }


    @Override
    public void save(Property property) {
        transactionManager.doInTransaction(() -> {
            var propertyDto = PropertyMapper.toDto(property);

            Map<String, Object> params = new HashMap<>();
            params.put("property_id", propertyDto.id());
            params.put("owner_id", propertyDto.ownerId());
            params.put("created_date", propertyDto.createdDate());
            params.put("name", propertyDto.name());
            params.put("location_address", propertyDto.locationAddress());
            params.put("location_postal_code", propertyDto.locationPostalCode());
            params.put("location_city", propertyDto.locationCity());
            params.put("type", propertyDto.type());
            params.put("status", propertyDto.status()); // TODO: I might want to separate 'status' in another scheme
            params.put("rental_limit", propertyDto.rentalLimit());

            simpleJdbcInsert.withTableName("property").execute(params);
        });
    }

    @Override
    public Optional<Property> findById(PropertyId propertyId) {
        String query = "SELECT * FROM property WHERE property_id = ?";

        return transactionManager.doInTransactionWithResult(() -> {
            var propertyDto = Optional.ofNullable(
                    jdbcTemplate.queryForObject(query, new PropertyRowMapper(), propertyId.getValue())
            );
            return propertyDto.map(PropertyMapper::toDomain);
        }); // TODO: Handle SQL exceptions
    }

}
