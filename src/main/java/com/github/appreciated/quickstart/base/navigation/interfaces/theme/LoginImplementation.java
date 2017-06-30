package com.github.appreciated.quickstart.base.navigation.interfaces.theme;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.login.LoginListener;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationControl;

/**
 * Created by appreciated on 21.03.2017.
 */
public interface LoginImplementation {
    LoginImplementation initWithLoginListener(LoginListener loginListener);
    LoginImplementation initWithAccessControl(AccessControl accessControl);
    LoginImplementation initRegistrationControl(RegistrationControl registrationControl);
}
