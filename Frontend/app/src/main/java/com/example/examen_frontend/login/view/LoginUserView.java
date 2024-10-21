package com.example.examen_frontend.login.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examen_frontend.R;
import com.example.examen_frontend.login.ContractLoginUser;
import com.example.examen_frontend.login.beans.User;
import com.example.examen_frontend.login.presenter.LoginUserPresenter;

public class LoginUserView extends AppCompatActivity implements ContractLoginUser.View {
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;

    private LoginUserPresenter presenter = new LoginUserPresenter(this);

    private static LoginUserView mainActivity = null;
    public static LoginUserView getInstance()
    {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        mainActivity = this;
        initComponents();
    }

    private void initComponents() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(txtEmail.getText().toString(), txtPassword.getText().toString());
                presenter.login(user);
            }
        });
    }

    @Override
    public void successLogin(User user) {
        Toast.makeText(this, "Login successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureLogin(String err) {
        Toast.makeText(this, "Login failed: " + err, Toast.LENGTH_LONG).show();
    }
}
