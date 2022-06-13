package edu.skku.map.finalproject;

public interface Contract {
    interface LoginView{
        void onSuccess();

        void onFailed(String message);

    }

    interface LoginListener{
        void onSuccess();

        void onFailed(String message);
    }

}
