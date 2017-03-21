package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.login.LoginListener;
import com.github.appreciated.quickstart.base.navigation.RegistrationControl;

/**
 * Created by Johannes on 21.03.2017.
 */
public interface Login {
    Login initWithLoginListener(LoginListener loginListener);
    Login initWithAccessControl(AccessControl accessControl);
    Login initRegistrationControl(RegistrationControl registrationControl);
}
