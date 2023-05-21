package org.jhay.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.domain.api.model.request.AccountRequest;
import org.jhay.domain.api.model.request.SaveAccountRequest;
import org.jhay.domain.api.model.request.VerifyAccountRequest;
import org.jhay.domain.api.model.response.VerifyAccountResponse;
import org.jhay.domain.repository.AccountRepository;
import org.jhay.domain.service.AccountService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public void saveUserAccount(SaveAccountRequest request){
        AccountRequest accountRequest = AccountRequest.builder()
                .build();
    }
//    private VerifyAccountResponse verifyUserAccount(VerifyAccountRequest request){
//
//    }
}
