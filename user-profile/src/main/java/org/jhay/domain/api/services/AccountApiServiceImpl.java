package org.jhay.domain.api.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.jhay.application.model.request.BankRequest;
import org.jhay.common.utils.ApiConnection;
import org.jhay.common.utils.ConnectionString;
import org.jhay.common.utils.DateUtils;
import org.jhay.domain.api.model.Banks;
import org.jhay.domain.api.model.request.AccountRequest;
import org.jhay.domain.api.model.request.SaveAccountRequest;
import org.jhay.domain.api.model.request.VerifyAccountRequest;
import org.jhay.domain.api.model.response.AccountResponse;
import org.jhay.domain.api.model.response.SaveAccountResponse;
import org.jhay.domain.api.model.response.VerifyAccountResponse;
import org.jhay.domain.api.repository.BankRepository;
import org.jhay.domain.model.Account;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.AccountRepository;
import org.jhay.domain.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountApiServiceImpl implements ApplicationRunner {
    private final RestTemplate restTemplate;
    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ApiConnection connection;

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

    public SaveAccountResponse saveUserAccount(SaveAccountRequest request) throws ParseException {
        User user = userRepository.findByEmail(request.getEmail());
        VerifyAccountRequest verifyAccountRequest = VerifyAccountRequest.builder()
                .account_bank(request.getBankCode())
                .account_number(request.getAccountNumber())
                .build();
        AccountRequest accountRequest = AccountRequest.builder()
                .account_bank(request.getBankCode())
                .account_number(request.getAccountNumber())
                .business_email(user.getEmail())
                .business_mobile(user.getPhoneNumber())
                .business_name(user.getFirstName()+" "+user.getLastName())
                .country("NG")
                .split_type("percentage")
                .split_value(0.1)
                .build();
        String savedAccount = connection.connectAndPost(accountRequest,ConnectionString.SAVE_ACCOUNT);
        AccountResponse response = new ObjectMapper().convertValue(new JSONParser(Objects.requireNonNull(savedAccount))
                .object().get("data"), new TypeReference<AccountResponse>() {});
        Account account = accountRepository.save(Account.builder()
                .accountName(response.getFull_name())
                .accountNumber(response.getAccount_number())
                .bankCode(request.getBankCode())
                .bankName(response.getBank_name())
                .createdAt(DateUtils.saveLocalDate(LocalDateTime.now()))
                .user(user)
                .build());
        return SaveAccountResponse.builder()
                .message("Account Saved Successfully")
                .accountBank(account.getBankName())
                .accountName(account.getAccountName())
                .build();
    }
    public String verifyUserAccount(VerifyAccountRequest request) throws ParseException {
        String userAccount = connection.connectAndPost(request,ConnectionString.VERIFY_ACCOUNT);
        VerifyAccountResponse response = new ObjectMapper().convertValue(new JSONParser(Objects.requireNonNull(userAccount))
                .object().get("data"), new TypeReference<VerifyAccountResponse>() {});
        System.out.println(response.getAccount_name());
        return response.getAccount_name();
    }
}
