package org.jhay.domain.api.services;

import org.apache.tomcat.util.json.ParseException;
import org.jhay.domain.api.model.request.VerifyAccountRequest;
import org.jhay.domain.api.model.response.SaveAccountResponse;
import org.springframework.stereotype.Service;

@Service
public interface AccountApiService {
    SaveAccountResponse saveUserAccount(Long userId, VerifyAccountRequest request) throws ParseException;
}
