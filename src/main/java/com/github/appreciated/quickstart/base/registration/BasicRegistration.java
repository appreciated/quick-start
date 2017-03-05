package com.github.appreciated.quickstart.base.registration;

import com.github.appreciated.quickstart.base.navigation.RegistrationControl;

/**
 * Created by Johannes on 05.03.2017.
 */
public class BasicRegistration implements RegistrationControl<BasicUser> {
    @Override
    public RegistrationResult checkUserRegistrationValidity(BasicUser user) {
        System.out.println("checkUserRegistrationValidity:" + user.toString());
        return new RegistrationResult(true);
    }

    public BasicUser getUser() {
        return new BasicUser();
    }

}
