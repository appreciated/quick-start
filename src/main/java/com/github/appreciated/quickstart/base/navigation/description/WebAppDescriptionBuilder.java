package com.github.appreciated.quickstart.base.navigation.description;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationControl;
import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.pages.Page;

import java.util.AbstractMap;

/**
 * Created by appreciated on 11.03.2017.
 */
public class WebAppDescriptionBuilder {
    WebAppDescription description = new WebAppDescription();

    public WebAppDescriptionBuilder withDesignProvider(Class<? extends QuickStartDesignProvider> desktopClass) {
        description.setProvider(desktopClass);
        return this;
    }

    public WebAppDescriptionBuilder withLoginPage() {
        description.setLoginPage(true);
        return this;
    }

    public WebAppDescriptionBuilder withSubpages(Subpages navigationDescription) {
        description.setNavigationDescription(navigationDescription);
        return this;
    }

    public WebAppDescriptionBuilder withSubpages(Page... navigationDescription) {
        description.setNavigationDescription(new Subpages(navigationDescription));
        return this;
    }

    public WebAppDescriptionBuilder withDefaultPage(Class<? extends Page> defaultPage) {
        description.setDefaultPage(defaultPage);
        return this;
    }

    public WebAppDescriptionBuilder withTitle(String title) {
        description.setTitle(title);
        return this;
    }

    public WebAppDescriptionBuilder withConfiguration(String key, boolean value) {
        description.getConfiguration().add(new AbstractMap.SimpleEntry<String, Boolean>(key, value));
        return this;
    }

    public WebAppDescriptionBuilder withRegistrationControl(RegistrationControl registrationControl) {
        description.setRegistrationControl(registrationControl);
        return this;
    }

    public WebAppDescriptionBuilder withAccessControl(AccessControl accessControl) {
        description.setAccessControl(accessControl);
        return this;
    }

    public WebAppDescription build() {
        return description;
    }
}