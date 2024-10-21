package com.example.examen_frontend.login;

import com.example.examen_frontend.login.beans.User;

public interface ContractLoginUser {
    interface View {
        void successLogin(User user);
        void failureLogin(String err);
    }

    interface Presenter {
        void login(User user);
    }

    interface Model {
        interface OnLoginUserListener {
            void OnFinished(User user);
            void OnFailure(String err);
        }

        void loginAPI(User user, OnLoginUserListener onLoginUserListener);
    }
}
