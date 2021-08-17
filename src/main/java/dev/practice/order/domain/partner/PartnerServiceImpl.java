package dev.practice.order.domain.partner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerStore partnerStore;
    private final PartnerReader partnerReader;

    // command -> initPartner
    // initPartner save to db
    // partner -> PartnerInfo and return
    @Override
    @Transactional
    public PartnerInfo registerPartner(PartnerCommand command) {
        Partner initPartner = command.toEntity();
        Partner partner = partnerStore.store(initPartner);
        return new PartnerInfo(partner);
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerInfo getPartnerInfo(String partnerToken) {
        // partnerToken -> Partner
        // Partner -> PartnerInfo and return
        Partner partner = partnerReader.getPartner(partnerToken);
        return new PartnerInfo(partner);
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerInfo enablePartner(String partnerToken) {
        // partnerToken -> Partner
        // partner.enable();
        Partner partner = partnerReader.getPartner(partnerToken);
        partner.enable();
        return new PartnerInfo(partner);
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerInfo disablePartner(String partnerToken) {
        Partner partner = partnerReader.getPartner(partnerToken);
        partner.disable();
        return new PartnerInfo(partner);
    }
}
