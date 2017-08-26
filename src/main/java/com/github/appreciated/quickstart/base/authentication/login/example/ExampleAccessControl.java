package com.github.appreciated.quickstart.base.authentication.login.example;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.login.User;
import com.github.appreciated.quickstart.base.notification.QuickNotification;

/**
 * Example implementation of {@link AccessControl}. This implementation
 * accepts any User, but only one password, the user "admin" as the only
 * administrator.
 */
public class ExampleAccessControl implements AccessControl {
    @Override
    public boolean checkCredentials(String username, String password) {
        if (username == null || username.isEmpty())
            return false;
        User.set(username);
        return ("Test1234!".equals(password));
    }

    @Override
    public void onInvalidCredentials() {
        QuickNotification.showMessage("Login failed", "Please check your username and password and try again.");
    }

    @Override
    public boolean isUserSignedIn() {
        return !User.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            return getPrincipalName().equals("admin");
        }
        return true;
    }

    @Override
    public String getPrincipalName() {
        return User.get();
    }

}
