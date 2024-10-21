package com.example.examen_frontend.login.presenter;

import com.example.examen_frontend.login.ContractLoginUser;
import com.example.examen_frontend.login.beans.User;
import com.example.examen_frontend.login.model.LoginUserModel;

public class LoginUserPresenter implements ContractLoginUser.Presenter, ContractLoginUser.Model.OnLoginUserListener {
    private ContractLoginUser.View view;
    private ContractLoginUser.Model model;

    public LoginUserPresenter(ContractLoginUser.View view) {
        this.view = view;
        model = new LoginUserModel(this);
    }

    @Override
    public void login(User user)
    {
        model.loginAPI(user, this);
    }

    @Override
    public void OnFinished(User user)
    {
        view.successLogin(user);
    }

    @Override
    public void OnFailure(String err)
    {
        view.failureLogin(err);
    }
}
