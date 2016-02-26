package com.philips.pins.espresso.demo.model;

import com.philips.pins.espresso.demo.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/2/26.
 */
public class UserModelImpl implements UserModel {

    private ArrayList<UserBean> arrayList;

    public UserModelImpl() {
        this.arrayList = new ArrayList<>();
    }


    @Override
    public void addUser(String username, String password) {
        UserBean userBean = new UserBean(username, password);
        arrayList.add(userBean);
    }

    @Override
    public boolean isReused(String username) {
        if (arrayList.size()!=0){
            for (UserBean userBean:arrayList) {
                String currentUsername = userBean.getUsername();
                if (currentUsername.equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getUserNumber(String username) {
        int i;
        if (isReused(username)) {
            for (i=0; i < arrayList.size(); i++) {
                String currentUsername = arrayList.get(i).getUsername();
                if (username.equals(currentUsername)) break;
            }
            return i;
        } else return -1;
    }

    @Override
    public UserBean getUser(int count) {
        return arrayList.get(count);
    }
}
