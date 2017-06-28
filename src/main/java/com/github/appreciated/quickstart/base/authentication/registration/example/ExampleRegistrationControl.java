package com.github.appreciated.quickstart.base.authentication.registration.example;

import com.github.appreciated.quickstart.base.authentication.registration.ExampleUser;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationControl;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationResult;

/**
 * Will accept any registration (empty Input is previously catched)
 * Created by appreciated on 05.03.2017.
 */
public class ExampleRegistrationControl implements RegistrationControl<ExampleUser> {
    @Override
    public RegistrationResult checkUserRegistrationValidity(ExampleUser user) {
        return new RegistrationResult(true);
    }

    public ExampleUser getUser() {
        return new ExampleUser();
    }
}
