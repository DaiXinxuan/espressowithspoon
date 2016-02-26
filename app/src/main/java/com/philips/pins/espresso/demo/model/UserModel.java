package com.philips.pins.espresso.demo.model;

import com.philips.pins.espresso.demo.bean.UserBean;

/**
 * Created by 310231492 on 2016/2/26.
 */
public interface UserModel {
    void addUser(String username, String password);
    boolean isReused(String username);
    int getUserNumber(String username);
    UserBean getUser(int count);
}
