package edu.skku.map.finalproject;

import android.text.TextUtils;

public class LoginInteractor {

    private Contract.LoginListener loginListener;

    public LoginInteractor(Contract.LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void login(LoginModel loginModel){
        if (hasError(loginModel)){
            return;
        }
        loginListener.onSuccess();
    }

    private boolean hasError(LoginModel loginModel){
        String username = loginModel.getUsername();
        String password = loginModel.getPassword();
        String check_login = loginModel.getCheck_login();


        if(TextUtils.isEmpty(username)){
            loginListener.onFailed("Empty username");
            return true;
        }
        if(TextUtils.isEmpty(password)){
            loginListener.onFailed("Empty password");
            return true;
        }
        if(check_login.contains("false")){
            loginListener.onFailed("Incorrect ID/PW");
            return true;
        }

        return false;

    }
}
