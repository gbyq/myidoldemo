package com.example.myidoldemo.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Han on 2017/12/22.
 */

public class UserInfo extends DataSupport {
    private int id;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
