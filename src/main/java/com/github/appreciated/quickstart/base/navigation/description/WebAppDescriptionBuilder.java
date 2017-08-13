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

    public WebAppDescriptionBuilder withPages(Pages navigationDescription) {
        description.setNavigationDescription(navigationDescription);
        return this;
    }

    public WebAppDescriptionBuilder withPages(Page... navigationDescription) {
        description.setNavigationDescription(new Pages(navigationDescription));
        return this;
    }

    /**
     * The counter part to withPages(...) one will override the other, when using this you will have to take care of navigation by your self.
     *
     * @param page
     * @return
     */
    public WebAppDescriptionBuilder withPage(Page page) {
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