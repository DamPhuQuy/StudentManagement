package com.mycompany.app.utilities.helpers;

import com.mycompany.app.utilities.constants.Role;

@FunctionalInterface
public interface Menu {
    void showMenu(Role role); 
}