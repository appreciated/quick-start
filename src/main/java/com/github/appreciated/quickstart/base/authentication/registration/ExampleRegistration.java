package com.github.appreciated.quickstart.base.authentication.registration;

import com.github.appreciated.quickstart.base.navigation.RegistrationControl;

/**
 * Will accept any registration (empty Input is previously catched)
 * Created by appreciated on 05.03.2017.
 */
public class ExampleRegistration implements RegistrationControl<ExampleUser> {
    @Override
    public RegistrationResult checkUserRegistrationValidity(ExampleUser user) {
        System.out.println("checkUserRegistrationValidity:" + user.toString());
        return new RegistrationResult(true);
    }

    public ExampleUser getUser() {
        return new ExampleUser();
    }
}
