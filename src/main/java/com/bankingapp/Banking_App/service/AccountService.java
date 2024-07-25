package com.bankingapp.Banking_App.service;

import com.bankingapp.Banking_App.dto.AccountDto;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);


}
