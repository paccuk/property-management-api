package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Repositories.LeaseAgreements;

import lombok.RequiredArgsConstructor;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.LeaseAgreement;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.LeaseAgreementRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Mappers.LeaseAgreements.LeaseAgreementMapper;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.RowMappers.LeaseAgreements.LeaseAgreementRowMapper;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class LeaseAgreementRepositoryImpl implements LeaseAgreementRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final TransactionManager transactionManager;

    @Override
    public void save(LeaseAgreement leaseAgreement) {
        transactionManager.doInTransaction(() -> {
            var leaseAgreementDto = LeaseAgreementMapper.toDto(leaseAgreement);

            Map<String, Object> params = new HashMap<>();
            params.put("lease_agreement_id", leaseAgreementDto.id());
            params.put("property_id", leaseAgreementDto.propertyId());
            params.put("lessor_id", leaseAgreementDto.lessorId());
            params.put("tenant_id", leaseAgreementDto.tenantId());
            params.put("monthly_rent_fee", leaseAgreementDto.monthlyRentFee());
            params.put("currency", leaseAgreementDto.currency());
            params.put("status", leaseAgreementDto.status());
            params.put("created_date", leaseAgreementDto.createdDate());
            params.put("modified_date", leaseAgreementDto.modifiedDate());
            params.put("lease_start_date", leaseAgreementDto.leaseStartDate());
            params.put("lease_end_date", leaseAgreementDto.leaseEndDate());

            simpleJdbcInsert.withTableName("lease_agreement").execute(params);

        });
    }

    @Override
    public Optional<LeaseAgreement> findById(LeaseAgreementId leaseAgreementId) {
        return transactionManager.doInTransactionWithResult(() -> {
            String query = "SELECT * FROM lease_agreement WHERE lease_agreement_id = ?";
            var leaseAgreementDto = Optional.ofNullable(
                    jdbcTemplate.queryForObject(query, new LeaseAgreementRowMapper(), leaseAgreementId.getValue())
            );
            return leaseAgreementDto.map(LeaseAgreementMapper::toDomain);
        }); // TODO: Handle SQL exceptions
    }
}
