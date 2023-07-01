package org.jhay.common.events;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.MessageResponse;
import org.jhay.common.exception.UserNotFoundException;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressEventHandler {
    private static final Gson GSON = new Gson();
    private final UserRepository userRepository;

    private void handleAddressSaved(String accountDetails){
        MessageResponse response = GSON.fromJson(accountDetails, MessageResponse.class);
        User user = userRepository.findById(response.getUserId())
                .orElseThrow(()-> new UserNotFoundException("User does not exist"));
        if(response.getMessage().equals("Address Saved Successfully")){
            user.setIsAddressSaved(true);
            user.setSubAccountId(response.getSubAccountId());
            userRepository.save(user);
            System.out.println("Address Saved By User");
        }

    }
}
