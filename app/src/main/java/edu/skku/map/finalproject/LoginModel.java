package edu.skku.map.finalproject;

public class LoginModel {

    private String username;
    private String password;
    private String check_login;

//    constructor
    public LoginModel(String username, String password, String check_login) {
        this.username = username;
        this.password = password;
        this.check_login = check_login;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCheck_login() {
        return check_login;
    }

    public void setCheck_login(String check_login) {
        this.check_login = check_login;
    }
}

