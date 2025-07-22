package com.mycompany.app.models;

import java.time.LocalDate;
import java.util.HashMap;

import com.mycompany.app.services.AuthService;
import com.mycompany.app.utilities.animation.Effect;
import com.mycompany.app.utilities.constants.Role;
import com.mycompany.app.utilities.helpers.Pair;
import com.mycompany.app.utilities.io.InformMessage;

public class Account {

    Pair<String, String> account;
    Role role;

    public Account() {}

    public Account(Pair<String, String> account, Role role) {
        this.account = account;
        this.role = role;
    }

    public String getUsername() {
        return account.getFirst();
    }

    public void setUsername(String username) {
        account.setFirst(username);
    }

    public String getPassword() {
        return account.getSecond(); 
    }

    public void setPassword(String password) {
        account.setSecond(password); 
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public boolean login(HashMap<String, Pair<String, Role>> accounts) {
        Effect.progressBar();
        if (AuthService.authenticate(account, accounts)) {
            InformMessage.success("Login successfully!");
            return true;
        } else {
            InformMessage.error("Login failed!");
            return false;
        }
    }

    public boolean register(
        HashMap<String, Pair<String, Role>> accounts,
        Pair<String, String> newAccount,
        Role role
    ) {
        Effect.progressBar();
        if (!AuthService.authenticate(newAccount, accounts)) {
            InformMessage.success("Your new account registered successfully!");
            Pair<String, Role> passRole = new Pair<>(
                newAccount.getSecond(),
                role
            );
            accounts.put(newAccount.getFirst(), passRole);
            return true;
        } else {
            InformMessage.error("Your new account failed to register!");
            InformMessage.error(
                "Please check if the account is existed or invalid"
            );
            return false;
        }
    }

    public String lastLogin() {
        return LocalDate.now().toString();
    }

    public Pair<String, String> getAccount() {
        return account;
    }

    public void setAccount(Pair<String, String> account) {
        this.account = account;
    }
}
