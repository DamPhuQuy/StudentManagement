package com.mycompany.app.services;

import com.mycompany.app.utilities.*;
import java.util.HashMap;

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
