package org.jhay.domain.api.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.jhay.application.model.request.BankRequest;
import org.jhay.common.exceptions.UserNotFoundException;
import org.jhay.common.utils.ApiConnection;
import org.jhay.common.utils.ConnectionString;
import org.jhay.common.utils.DateUtils;
import org.jhay.domain.api.model.Banks;
import org.jhay.domain.api.model.request.AccountRequest;
import org.jhay.domain.api.model.request.VerifyAccountRequest;
import org.jhay.domain.api.model.response.AccountResponse;
import org.jhay.domain.api.model.response.BankResponse;
import org.jhay.domain.api.model.response.SaveAccountResponse;
import org.jhay.domain.api.model.response.VerifyAccountResponse;
import org.jhay.domain.api.repository.BankRepository;
import org.jhay.domain.model.Account;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.AccountRepository;
import org.jhay.domain.repository.UserRepository;
import org.jhay.domain.service.notification.NotificationService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountApiServiceImpl implements AccountApiService, ApplicationRunner {
    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ApiConnection connection;
    private final NotificationService notificationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String banks = connection.connectAndGet(ConnectionString.GET_BANK);
        new ObjectMapper().convertValue(new JSONParser(Objects.requireNonNull(banks))
                .object().get("data"), new TypeReference<List<BankRequest>>() {}).forEach(bankRequest ->
                bankRepository.save(Banks.builder()
                        .name(bankRequest.getName())
                        .code(bankRequest.getCode())
                        .build()));
        List<Banks> banksList = bankRepository.findAll();
        System.out.println(banksList);
    }

    @Override
    public SaveAccountResponse saveUserAccount(Long userId, VerifyAccountRequest request) throws ParseException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        AccountRequest accountRequest = AccountRequest.builder()
                .business_name(verifyUserAccount(request))
                .account_number(request.getAccount_number())
                .settlement_bank(request.getAccount_bank())
                .percentage_charge(0.1)
                .description("Creation of Sub Account for user with email "+ user.getEmail())
                .primary_contact_email(user.getEmail())
                .primary_contact_phone(user.getPhoneNumber())
                .build();
        BankResponse bankResponse = connection.connectAndPost(accountRequest,ConnectionString.SAVE_ACCOUNT, BankResponse.class);
        System.out.println(bankResponse);
        AccountResponse response = bankResponse.getData();
        Account account = accountRepository.save(Account.builder()
                .id(response.getSubaccount_code())
                .accountName(response.getBusiness_name())
                .accountNumber(response.getAccount_number())
                .bankCode(request.getAccount_bank())
                .bankName(response.getSettlement_bank())
                .createdAt(DateUtils.saveLocalDate(LocalDateTime.now()))
                .user(user)
                .build());
        notificationService.sendAccountMessage("Account Saved Successfully");
        return SaveAccountResponse.builder()
                .message("Account Saved Successfully")
                .accountBank(account.getBankName())
                .accountName(account.getAccountName())
                .build();
    }

    private String verifyUserAccount(VerifyAccountRequest request) throws ParseException {
        String userAccount = connection.connectAndGet(ConnectionString.verifyAccount(request));
        VerifyAccountResponse response = new ObjectMapper().convertValue(new JSONParser(Objects.requireNonNull(userAccount))
                .object().get("data"), new TypeReference<VerifyAccountResponse>() {});
        System.out.println(response.getAccount_name());
        return response.getAccount_name();
    }

}
