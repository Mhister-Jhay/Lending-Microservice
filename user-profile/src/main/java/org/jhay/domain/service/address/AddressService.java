package org.jhay.domain.service.address;

import org.jhay.application.model.request.AddressRequest;
import org.jhay.application.model.response.AddressResponse;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    AddressResponse saveUserAddress(Long userId, AddressRequest addressRequest);

    AddressResponse getUserAddress(Long userId);

    String editUserAddress(Long userId, AddressRequest addressRequest);
}
