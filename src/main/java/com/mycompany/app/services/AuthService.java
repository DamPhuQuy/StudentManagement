package com.mycompany.app.services;

import java.util.HashMap;

import com.mycompany.app.models.Account;

public class AuthService {

    public static boolean authenticate(
        Account account,
        HashMap<String, Account> accounts
    ) {
        String username = account.getUsername(); 
        String password = account.getPassword(); 

        if (
            accounts.containsKey(username) &&
            accounts.get(username).getPassword().equals(password)
        ) {
            return true;
        }
        return false;
    }
}
