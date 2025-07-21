package com.mycompany.app.services;

import java.util.HashMap;

import com.mycompany.app.utilities.constants.Role;
import com.mycompany.app.utilities.helpers.Pair;

public class AuthService {

    public static boolean authenticate(
        Pair<String, String> account,
        HashMap<String, Pair<String, Role>> accounts
    ) {
        String username = account.getFirst();
        String password = account.getSecond();

        if (
            accounts.containsKey(username) &&
            accounts.get(username).getFirst().equals(password)
        ) {
            return true;
        }
        return false;
    }
}
