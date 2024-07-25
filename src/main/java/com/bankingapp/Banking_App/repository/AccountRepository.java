package com.bankingapp.Banking_App.repository;

import com.bankingapp.Banking_App.entitiy.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
