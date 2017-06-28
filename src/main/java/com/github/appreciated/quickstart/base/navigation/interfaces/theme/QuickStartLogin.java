package com.github.appreciated.quickstart.base.navigation.interfaces.theme;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.login.LoginListener;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationControl;

/**
 * Created by appreciated on 21.03.2017.
 */
public interface QuickStartLogin {
    QuickStartLogin initWithLoginListener(LoginListener loginListener);
    QuickStartLogin initWithAccessControl(AccessControl accessControl);
    QuickStartLogin initRegistrationControl(RegistrationControl registrationControl);
}
