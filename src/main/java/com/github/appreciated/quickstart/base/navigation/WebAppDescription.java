package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.exception.InvalidWebDescriptionException;
import com.github.appreciated.quickstart.base.login.AccessControl;
import com.github.appreciated.quickstart.base.navigation.interfaces.LoginPage;
import com.github.appreciated.quickstart.base.navigation.interfaces.NavigationDesignInterface;
import com.github.appreciated.quickstart.base.navigation.interfaces.Page;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by appreciated on 01.01.2017.
 */
public class WebAppDescription {
    private Class<? extends NavigationDesignInterface> defaultClass;
    private Class<? extends NavigationDesignInterface> mobileClass;
    private Class<? extends LoginPage> loginClass;
    private NavigationDesignInterface defaultView;
    private NavigationDesignInterface mobileView;
    private LoginPage loginNavigable;
    private String title;
    private List<AbstractMap.SimpleEntry<String, Boolean>> configuration = new ArrayList<>();
    private List<Page> navigationElements = new ArrayList<>();
    private Class<? extends Page> defaultPage;
    private AccessControl accessControl;
    private RegistrationControl registrationControl;
    private Pages navigationDescription;

    public WebAppDescription() {
    }

    public WebAppDescription(Class<? extends NavigationDesignInterface> defaultClass) {
        this.defaultClass = defaultClass;
    }

    public WebAppDescription(Class<? extends NavigationDesignInterface> defaultClass, Class<? extends NavigationDesignInterface> mobileClass) {
        this.defaultClass = defaultClass;
        this.mobileClass = mobileClass;
    }

    public WebAppDescription(Class<? extends NavigationDesignInterface> defaultClass, Class<? extends NavigationDesignInterface> mobileClass, Class<? extends LoginPage> loginClass) {
        this.defaultClass = defaultClass;
        this.mobileClass = mobileClass;
        this.loginClass = loginClass;
    }

    public WebAppDescription withDesign(Class<? extends NavigationDesignInterface> desktopClass) {
        this.defaultClass = desktopClass;
        return this;
    }

    public WebAppDescription withMobileDesign(Class<? extends NavigationDesignInterface> mobileClass) {
        this.mobileClass = mobileClass;
        return this;
    }

    public WebAppDescription withLoginPage(Class<? extends LoginPage> loginClass) {
        this.loginClass = loginClass;
        return this;
    }

    public WebAppDescription withSubpages(Pages navigationDescription) {
        this.navigationDescription = navigationDescription;
        return this;
    }

    public Class<? extends Page> getDefaultPage() {
        return defaultPage;
    }

    public NavigationDesignInterface getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(NavigationDesignInterface defaultView) {
        this.defaultView = defaultView;
    }

    public NavigationDesignInterface getMobileView() {
        return mobileView;
    }

    public void setMobileView(NavigationDesignInterface mobileView) {
        this.mobileView = mobileView;
    }

    public LoginPage getLoginNavigable() {
        return loginNavigable;
    }

    public void setLoginNavigable(LoginPage loginNavigable) {
        this.loginNavigable = loginNavigable;
    }

    private Object createInstance(Class instanceClass) {
        if (instanceClass != null) {
            try {
                return instanceClass.getConstructor().newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                e.getCause().printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                e.getCause().printStackTrace();
            } catch (InvocationTargetException e) {
                e.getCause().printStackTrace();
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public WebAppDescription withDefaultPage(Class<? extends Page> defaultPage) {
        this.defaultPage = defaultPage;
        return this;
    }

    public WebAppDescription withTitle(String title) {
        this.title = title;
        return this;
    }

    public WebAppDescription withConfiguration(String key, boolean value) {
        configuration.add(new AbstractMap.SimpleEntry<String, Boolean>(key, value));
        return this;
    }

    public Stream<AbstractMap.SimpleEntry<String, Boolean>> getConfiguration() {
        return configuration.stream();
    }

    public Stream<Page> getNavigationElements() {
        return navigationElements.stream();
    }

    public WebAppDescription withLogin(AccessControl accessControl) {
        this.accessControl = accessControl;
        return this;
    }

    public AccessControl getAccessControl() {
        return accessControl;
    }

    public WebAppDescription init() throws InvalidWebDescriptionException {
        if (defaultClass == null) {
            throw new InvalidWebDescriptionException("No defaultNavigationView defined!");
        }
        if (loginClass != null && accessControl == null) {
            throw new InvalidWebDescriptionException("No accessControl defined!");
        }
        if (title == null) {
            throw new InvalidWebDescriptionException("No title defined!");
        }
        for (Page navigationElement : navigationElements) {
            if (navigationElement.getNavigationName() == null) {
                throw new InvalidWebDescriptionException("No navigationName defined!");
            }
        }
        if (navigationDescription.getPages().size() == 0) {
            throw new InvalidWebDescriptionException("No navigation elements defined defined!");
        }
        if (defaultPage == null) {
            defaultPage = navigationDescription.getPages().get(0).getClass();
        }

        this.navigationElements = navigationDescription.getPages();
        defaultView = (NavigationDesignInterface) createInstance(defaultClass);
        mobileView = (NavigationDesignInterface) createInstance(mobileClass);
        loginNavigable = (LoginPage) createInstance(loginClass);
        loginNavigable.initTitle(title);
        if (accessControl != null) {
            loginNavigable.initWithAccessControl(accessControl);
            if (registrationControl == null) {
                throw new InvalidWebDescriptionException("No registrationControl defined!");
            } else {
                loginNavigable.initRegistrationControl(registrationControl);
            }
        }
        return this;
    }

    public WebAppDescription withRegistration(RegistrationControl registrationControl) {
        this.registrationControl = registrationControl;
        return this;
    }

    public RegistrationControl getRegistrationControl() {
        return registrationControl;
    }

}
