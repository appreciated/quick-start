package com.github.appreciated.quickstart.base.authentication.registration;

import com.vaadin.server.ErrorMessage;

/**
 * Created by appreciated on 05.03.2017.
 */
public class RegistrationResult {
    private boolean valid;
    private ErrorMessage error = null;
    private Field field = null;

    public RegistrationResult(boolean valid){
        this.valid = valid;
    }

    public RegistrationResult(boolean valid, ErrorMessage error, Field field) {
        this(valid);
        this.error = error;
        this.field = field;
    }

    public boolean isValid() {
        return valid;
    }

    public ErrorMessage getErrorMessage() {
        return error;
    }

    public Field getField() {
        return field;
    }
}
