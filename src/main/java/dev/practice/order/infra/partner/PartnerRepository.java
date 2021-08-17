package dev.practice.order.infra.partner;

import dev.practice.order.domain.partner.Partner;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface PartnerRepository extends Repository<Partner, Long> {
    Optional<Partner> findByPartnerToken(String partnerToken);
    Partner save(Partner partner);
}
