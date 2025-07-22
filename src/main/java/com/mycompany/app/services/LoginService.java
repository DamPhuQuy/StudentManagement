package com.mycompany.app.services;

import java.time.LocalDate;
import java.util.HashMap;

import com.mycompany.app.models.Account;
import com.mycompany.app.utilities.animation.Effect;
import com.mycompany.app.utilities.io.InformMessage;

public class LoginService {
    public LoginService() {}
    
    public static void loginMenu() {
        System.out.println("-------Login Menu-------");
        System.out.println("1. Login"); 
        System.out.println("2. Register"); 
        System.out.println("3. Exit"); 
        System.out.print("Choose an option: ");
    }

    public static boolean login(Account account, HashMap<String, Account> accounts) {
        Effect.progressBar();
        if (AuthService.authenticate(account, accounts)) {
            InformMessage.success("Login successfully!");
            return true;
        } else {
            InformMessage.error("Login failed!");
            return false;
        }
    }

    public static boolean register(
        HashMap<String, Account> accounts,
        Account newAccount
    ) {
        Effect.progressBar();
        if (!AuthService.authenticate(newAccount, accounts)) {
            InformMessage.success("Your new account registered successfully!");
            accounts.put(newAccount.getUsername(), newAccount);
            return true;
        } else {
            InformMessage.error("Your new account failed to register!");
            InformMessage.error(
                "Please check if the account is existed or invalid"
            );
            return false;
        }
    }

    public static String lastLogin() {
        return LocalDate.now().toString();
    }
}
