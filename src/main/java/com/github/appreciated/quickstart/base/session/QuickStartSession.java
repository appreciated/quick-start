package com.github.appreciated.quickstart.base.session;

import com.github.appreciated.quickstart.base.authentication.login.User;

public class QuickStartSession {

    public static boolean isUserSignedIn() {
        return User.isSignedIn();
    }

    public static String getUsername() {
        return User.get();
    }
}
