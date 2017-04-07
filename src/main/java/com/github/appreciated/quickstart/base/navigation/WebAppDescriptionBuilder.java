package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.navigation.interfaces.LoginPage;
import com.github.appreciated.quickstart.base.navigation.interfaces.NavigationDesignInterface;
import com.github.appreciated.quickstart.base.navigation.interfaces.Subpage;

import java.util.AbstractMap;

/**
 * Created by appreciated on 11.03.2017.
 */
public class WebAppDescriptionBuilder {
    WebAppDescription description = new WebAppDescription();

    public WebAppDescriptionBuilder withDesign(Class<? extends NavigationDesignInterface> desktopClass) {
        description.setDefaultClass(desktopClass);
        return this;
    }

    public WebAppDescriptionBuilder withMobileDesign(Class<? extends NavigationDesignInterface> mobileClass) {
        description.setMobileClass(mobileClass);
        return this;
    }

    public WebAppDescriptionBuilder withLoginPage(Class<? extends LoginPage> loginClass) {
        description.setLoginClass(loginClass);
        return this;
    }

    public WebAppDescriptionBuilder withSubpages(Subpages navigationDescription) {
        description.setNavigationDescription(navigationDescription);
        return this;
    }

    public WebAppDescriptionBuilder withSubpages(Subpage... navigationDescription) {
        description.setNavigationDescription(new Subpages(navigationDescription));
        return this;
    }

    public WebAppDescriptionBuilder withDefaultPage(Class<? extends Subpage> defaultPage) {
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

    public WebAppDescriptionBuilder withRegistration(RegistrationControl registrationControl) {
        description.setRegistrationControl(registrationControl);
        return this;
    }

    public WebAppDescriptionBuilder withLogin(AccessControl accessControl) {
        description.setAccessControl(accessControl);
        return this;
    }

    public WebAppDescription build() {
        return description;
    }
}