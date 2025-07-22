package com.mycompany.app.models;

import com.mycompany.app.utilities.constants.Role;
import com.mycompany.app.utilities.helpers.Pair;

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

    public Pair<String, String> getAccount() {
        return account;
    }
    public void setAccount(Pair<String, String> account) {
        this.account = account;
    }
}