package org.jhay.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.domain.repository.AccountRepository;
import org.jhay.domain.service.AccountService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
}
