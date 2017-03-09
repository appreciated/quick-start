package com.github.appreciated.quickstart.base.authentication.login;

import java.io.Serializable;

/**
 * Created by appreciated on 15.12.2016.
 */
public interface LoginListener extends Serializable {
    void loginSuccessful();
}
