package com.philips.pins.espresso.demo.view;

/**
 * Created by 310231492 on 2016/2/26.
 */
public interface LoginView {
    String getUsername();
    String getPassword();

    void setUsername(String username);
    void showMessage(String message);
}
