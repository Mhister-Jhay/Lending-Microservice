package org.jhay.domain.service.address;

import org.jhay.application.model.request.AddressRequest;
import org.jhay.application.model.response.AddressResponse;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    AddressResponse saveUserAddress(AddressRequest addressRequest);

    AddressResponse getUserAddress();

    AddressResponse editUserAddress(Long addressId, AddressRequest addressRequest);
}
