package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account){

        if (account.getUsername().length() > 0 && account.getPassword().length() >= 4){
            return accountRepository.save(account);
        }
        return null;
    }

    public Account loginAccount(Account account){
        Account existingAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (existingAccount != null){
            return existingAccount;
        }
        return null;
    }
}
