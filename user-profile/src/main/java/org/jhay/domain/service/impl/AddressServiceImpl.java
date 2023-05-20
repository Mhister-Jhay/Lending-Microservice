package org.jhay.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.domain.repository.AddressRepository;
import org.jhay.domain.service.AddressService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
}
