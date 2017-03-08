package com.github.appreciated.quickstart.base.login;

/**
 * Example implementation of {@link AccessControl}. This implementation
 * accepts any User, but only one password, the user "admin" as the only
 * administrator.
 */
public class ExampleAccess implements AccessControl {
    @Override
    public boolean signIn(String username, String password) {
        if (username == null || username.isEmpty())
            return false;
        CurrentUser.set(username);
        return ("Test1234!".equals(password));
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
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
        return CurrentUser.get();
    }

}
