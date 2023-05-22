package org.jhay.common.utils;

import org.jhay.domain.api.model.request.VerifyAccountRequest;

public class ConnectionString {
    public static final String GET_BANK = "https://api.paystack.co/bank?country=nigeria";
    public static final String SAVE_ACCOUNT = "https://api.paystack.co/subaccount";
    public static String verifyAccount(VerifyAccountRequest request){
        return "https://api.paystack.co/bank/resolve?account_number="+request.getAccount_number()+"&bank_code="+request.getAccount_bank();
    }
}
