package dev.practice.order.interfaces.partner;

import dev.practice.order.application.partner.PartnerFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.partner.PartnerCommand;
import dev.practice.order.domain.partner.PartnerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerApiController {
    private final PartnerFacade partnerFacade;

    @PostMapping
    public CommonResponse registerPartner(@RequestBody @Valid PartnerDto.RegisterRequest request ) {
        // 외부에서 전달된 파라미터 (dto) -> Command,Criteria convert
        // facade 호출 .. PartnerInfo return
        // partnerInfo -> CommonResponse convert and return

        PartnerCommand command = request.toCommand();
        PartnerInfo partnerInfo = partnerFacade.registerPartner(command);
        PartnerDto.RegisterResponse response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

}
