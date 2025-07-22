package com.mycompany.app.utilities.helpers;

import com.mycompany.app.models.Account;
import com.mycompany.app.utilities.constants.Gender;

public interface PersonProfile {
    Account getAccount(); 
    String getFullname();
    String getDob();
    Gender getGender();
    String getEmail();
    String getAddress();
    String getPhone();
}
