package com.mycompany.app.models;

import com.mycompany.app.utilities.Gender;

public interface PersonProfile {
    Account getAccount(); 
    String getFullname();
    String getDob();
    Gender getGender();
    String getEmail();
    String getAddress();
    String getPhone();
}
