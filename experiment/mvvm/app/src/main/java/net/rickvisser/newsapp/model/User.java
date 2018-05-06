package net.rickvisser.newsapp.model;

/**
 * Created by Rick on 6-5-2018.
 */

public class User {
    private String mUserName;

    private String mEmailAddress;

    public User() {
        this("", "");
    }

    public User(String pUserName, String pEmailAddress) {
        mUserName = pUserName;
        mEmailAddress = pEmailAddress;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String pUserName) {
        mUserName = pUserName;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setEmailAddress(String pEmailAddress) {
        mEmailAddress = pEmailAddress;
    }
}
