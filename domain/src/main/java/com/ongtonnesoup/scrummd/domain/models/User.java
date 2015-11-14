package com.ongtonnesoup.scrummd.domain.models;

public class User {

    private String mUser;

    public User(String user) {
        mUser = user;
    }

    public String id() {
        return mUser;
    }
}
