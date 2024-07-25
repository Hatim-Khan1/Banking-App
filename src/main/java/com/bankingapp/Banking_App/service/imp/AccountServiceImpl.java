package com.bankingapp.Banking_App.service.imp;

import com.bankingapp.Banking_App.dto.AccountDto;
import com.bankingapp.Banking_App.entitiy.Account;
import com.bankingapp.Banking_App.mapper.AccountMapper;
import com.bankingapp.Banking_App.repository.AccountRepository;
import com.bankingapp.Banking_App.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private AccountRepository accountRepository;
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account =accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount =accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does Not Exist"));
        double balance = account.getBalance();
        if(balance<amount){
            RuntimeException runtimeException = new RuntimeException("Not Sufficient Balance");
            throw runtimeException;
        }

            double total = account.getBalance() - amount;
            account.setBalance(total);
            Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(() ->new RuntimeException("Account Doesn't exist"));
        accountRepository.deleteById(account.getId());
    }
}
