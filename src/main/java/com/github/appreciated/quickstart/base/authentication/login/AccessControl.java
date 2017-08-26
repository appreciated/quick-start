package com.github.appreciated.quickstart.base.authentication.login;

import java.io.Serializable;

/**
 * Simple interface for authentication and authorization checks.
 */
public interface AccessControl extends Serializable {

    public boolean checkCredentials(String username, String password);

    public void onInvalidCredentials();

    public boolean isUserSignedIn();

    public boolean isUserInRole(String role);

    public String getPrincipalName();
}
