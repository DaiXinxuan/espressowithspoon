package com.philips.pins.espresso.demo.presenter;

import android.content.Context;

import com.philips.pins.espresso.demo.R;
import com.philips.pins.espresso.demo.bean.UserBean;
import com.philips.pins.espresso.demo.model.UserModel;
import com.philips.pins.espresso.demo.model.UserModelImpl;
import com.philips.pins.espresso.demo.view.LoginView;

/**
 * Created by 310231492 on 2016/2/26.
 */
public class LoginPresenter {
    private LoginView loginView;
    private UserModel userModel;
    private Context c;
    private int lastLoginUserCount = -1;

    public LoginPresenter(LoginView loginView, Context c) {
        this.loginView = loginView;
        userModel = new UserModelImpl();
        this.c = c;
    }

    public void regist() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        if (userModel.isReused(username)) {
            loginView.showMessage(c.getString(R.string.user_resued));
        } else {
            userModel.addUser(username, password);
            loginView.showMessage(c.getString(R.string.regist_success));
        }
    }

    public void login() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        int count = userModel.getUserNumber(username);
        if (count == -1) {
            loginView.showMessage(c.getString(R.string.nonexisten_user));
        } else {
            String correctPassword = userModel.getUser(count).getPassword();
            if (password.equals(correctPassword)) {
                loginView.showMessage(c.getString(R.string.login_success));
                lastLoginUserCount = count;
            } else {
                loginView.showMessage(c.getString(R.string.login_fail));
            }
        }
    }

    public void displayLastLoginUser() {
        if (lastLoginUserCount != -1) {
            UserBean userBean = userModel.getUser(lastLoginUserCount);
            loginView.setUsername(userBean.getUsername());
        }
    }
}
