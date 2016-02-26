package com.philips.pins.espresso.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.philips.pins.espresso.demo.bean.UserBean;
import com.philips.pins.espresso.demo.presenter.LoginPresenter;
import com.philips.pins.espresso.demo.view.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginView {
    private EditText usernameEd;
    private EditText passwordEd;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loginPresenter = new LoginPresenter(this, getApplication());
        //Display the last account logged successfully
        loginPresenter.displayLastLoginUser();
    }

    //Initialize the controls
    private void initView() {
        usernameEd = (EditText) findViewById(R.id.username);
        passwordEd = (EditText) findViewById(R.id.password);
    }

    @Override
    public String getUsername() {
        return usernameEd.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEd.getText().toString();
    }

    @Override
    public void setUsername(String username) {
        usernameEd.setText(username);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        String username = getUsername();
        String password = getPassword();

        if (TextUtils.isEmpty(username)) {
            showMessage(getString(R.string.empty_user));
        } else if (TextUtils.isEmpty(password)) {
            showMessage(getString(R.string.empty_pass));
        } else {
            switch (v.getId()) {
                case R.id.login:
                    loginPresenter.login();
                    break;
                case R.id.regist:
                    loginPresenter.regist();
                    break;
            }
        }
    }
}
