package com.example.pgutierrezd.exampleloginfirebase;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by pgutierrezd on 18/10/2016.
 */
@IgnoreExtraProperties
public class User {

    public String email;
    public String password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
