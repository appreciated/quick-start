package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationControl;
import com.github.appreciated.quickstart.base.navigation.exception.InvalidWebDescriptionException;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.QuickStartLoginView;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.QuickStartNavigationView;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by appreciated on 01.01.2017.
 */
public class WebAppDescription {
    private Class<? extends QuickStartNavigationView> defaultClass;
    private Class<? extends QuickStartNavigationView> mobileClass;
    private Class<? extends QuickStartLoginView> loginClass;

    private QuickStartDesignProvider provider;
    private QuickStartNavigationView defaultView;
    private QuickStartNavigationView mobileView;
    private QuickStartLoginView loginNavigable;
    private String title;
    private List<AbstractMap.SimpleEntry<String, Boolean>> configuration = new ArrayList<>();
    private List<Subpage> navigationElements = new ArrayList<>();
    private Class<? extends Subpage> defaultPage;
    private AccessControl accessControl;
    private RegistrationControl registrationControl;
    private Subpages navigationDescription;
    private Class<? extends QuickStartDesignProvider> designProvider;
    private boolean loginPage;

    public Class<? extends Subpage> getDefaultPage() {
        return defaultPage;
    }

    public QuickStartNavigationView getDefaultView() {
        return defaultView;
    }

    public QuickStartNavigationView getMobileView() {
        return mobileView;
    }

    public QuickStartLoginView getLoginNavigable() {
        return loginNavigable;
    }

    private Object createInstance(Class instanceClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (instanceClass != null) {
            return instanceClass.getConstructor().newInstance();
        }
        return null;
    }

    public String getTitle() {
        return title;
    }


    public List<AbstractMap.SimpleEntry<String, Boolean>> getConfiguration() {
        return configuration;
    }

    public Stream<Subpage> getSubpages() {
        return navigationElements.stream();
    }


    public AccessControl getAccessControl() {
        return accessControl;
    }

    public WebAppDescription init(boolean isMobile) throws InvalidWebDescriptionException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (designProvider == null) {
            throw new InvalidWebDescriptionException("No Design Provider defined!");
        }
        if (loginClass != null && accessControl == null) {
            throw new InvalidWebDescriptionException("No accessControl defined!");
        }
        if (title == null) {
            throw new InvalidWebDescriptionException("No title defined!");
        }
        if (navigationDescription.getSubpages().size() == 0) {
            throw new InvalidWebDescriptionException("No navigation elements defined defined!");
        }
        if (defaultPage == null) {
            defaultPage = navigationDescription.getSubpages().getFirst().getClass();
        }
        this.navigationElements = navigationDescription.getSubpages();
        for (Subpage navigationElement : navigationElements) {
            if (navigationElement.getNavigationName() == null) {
                throw new InvalidWebDescriptionException(navigationElement.getClass().getSimpleName() + " has no SubpageDescription!");
            }
        }
        provider = (QuickStartDesignProvider) createInstance(designProvider);
        if (!isMobile) {
            defaultView = (QuickStartNavigationView) createInstance(provider.getDesktopDesign());
        } else {
            mobileView = (QuickStartNavigationView) createInstance(provider.getMobileDesign());
        }
        if (loginPage) {
            loginNavigable = provider.getLoginView();
            loginNavigable.initTitle(title);
        }
        if (accessControl != null) {
            if (loginNavigable != null) {
                loginNavigable.initWithAccessControl(accessControl);
            } else {
                defaultView.initWithAccessControl(accessControl);
            }
            if (registrationControl == null) {
                throw new InvalidWebDescriptionException("No registrationControl defined!");
            } else {
                if (loginNavigable != null) {
                    loginNavigable.initRegistrationControl(registrationControl);
                } else {
                    defaultView.initRegistrationControl(registrationControl);
                }

            }
        }
        return this;
    }

    public RegistrationControl getRegistrationControl() {
        return registrationControl;
    }

    public void setDefaultClass(Class<? extends QuickStartNavigationView> defaultClass) {
        this.defaultClass = defaultClass;
    }

    public void setMobileClass(Class<? extends QuickStartNavigationView> mobileClass) {
        this.mobileClass = mobileClass;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDefaultPage(Class<? extends Subpage> defaultPage) {
        this.defaultPage = defaultPage;
    }

    public void setAccessControl(AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    public void setRegistrationControl(RegistrationControl registrationControl) {
        this.registrationControl = registrationControl;
    }

    public void setNavigationDescription(Subpages navigationDescription) {
        this.navigationDescription = navigationDescription;
    }

    public QuickStartDesignProvider getProvider() {
        return provider;
    }

    public void setProvider(Class<? extends QuickStartDesignProvider> designProvider) {
        this.designProvider = designProvider;
    }

    public void setLoginPage(boolean loginPage) {
        this.loginPage = loginPage;
    }
}
