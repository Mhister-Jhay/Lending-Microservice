package org.jhay.domain.service.address;

import lombok.RequiredArgsConstructor;
import org.jhay.application.model.request.AddressRequest;
import org.jhay.application.model.response.AddressResponse;
import org.jhay.common.exceptions.AddressAlreadyExistException;
import org.jhay.common.exceptions.AddressNotFoundException;
import org.jhay.common.exceptions.UserNotFoundException;
import org.jhay.domain.model.Address;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.AddressRepository;
import org.jhay.domain.repository.UserRepository;
import org.jhay.domain.service.notification.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final NotificationService notificationService;
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public AddressResponse saveUserAddress(Long userId, AddressRequest addressRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        if(addressRepository.existsByUser(user)){
            throw new AddressAlreadyExistException("Address is already saved");
        }
        Address address = addressRepository.save(Address.builder()
                        .city(addressRequest.getCity())
                        .state(addressRequest.getState())
                        .user(user)
                        .country(addressRequest.getCountry())
                        .street(addressRequest.getStreet())
                        .landmark(addressRequest.getLandmark())
                        .postalCode(addressRequest.getPostalCode())
                .build());
        notificationService.sendAddressMessage("Address Saved Successfully");
        return modelMapper.map(address,AddressResponse.class);
    }

    @Override
    public AddressResponse getUserAddress(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Address address = addressRepository.findByUser(user)
                .orElseThrow(()-> new AddressNotFoundException("User has no address saved"));
        return modelMapper.map(address, AddressResponse.class);
    }

    @Override
    public AddressResponse editUserAddress(Long userId, AddressRequest addressRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Address address = addressRepository.findByUser(user)
                .orElseThrow(()-> new AddressNotFoundException("User has no address saved"));
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setLandmark(addressRequest.getLandmark());
        address.setState(addressRequest.getState());
        address.setPostalCode(addressRequest.getPostalCode());
        return modelMapper.map(addressRepository.save(address),AddressResponse.class);
    }
}
