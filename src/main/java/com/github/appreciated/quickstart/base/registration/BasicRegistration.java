package com.github.appreciated.quickstart.base.registration;

import com.github.appreciated.quickstart.base.navigation.RegistrationControl;

/**
 * Created by Johannes on 05.03.2017.
 */
public class BasicRegistration implements RegistrationControl<BasicUser> {
    @Override
    public void onUserDataEntered(BasicUser user) {
        System.out.println("onUserDataEntered:" + user.toString());
    }

    public BasicUser getUser() {
        return new BasicUser();
    }

}
