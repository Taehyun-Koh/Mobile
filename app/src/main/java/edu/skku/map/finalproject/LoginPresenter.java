package edu.skku.map.finalproject;

public class LoginPresenter implements Contract.LoginListener{

    private Contract.LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenter(Contract.LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractor(this);
    }

    public void start(LoginModel loginModel){
        loginInteractor.login(loginModel);
    }
    @Override
    public void onSuccess() {
        loginView.onSuccess();
    }

    @Override
    public void onFailed(String message) {
        loginView.onFailed(message);
    }
}
