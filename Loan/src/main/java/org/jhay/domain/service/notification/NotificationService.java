package org.jhay.domain.service.notification;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationService {
    private static final Gson GSON = new Gson();
    public void sendLoanRequestMessage(String loandetails){

    }
}
