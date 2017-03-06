package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.exception.InvalidWebDescriptionException;
import com.github.appreciated.quickstart.base.interfaces.LoginNavigable;
import com.github.appreciated.quickstart.base.interfaces.Navigable;
import com.github.appreciated.quickstart.base.interfaces.NavigationDesignInterface;
import com.github.appreciated.quickstart.base.login.AccessControl;

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
    private Class<? extends LoginNavigable> loginClass;

    private NavigationDesignInterface defaultView;
    private NavigationDesignInterface mobileView;
    private LoginNavigable loginNavigable;
    private String title;
    private List<AbstractMap.SimpleEntry<String, Boolean>> configuration = new ArrayList<>();
    private List<Navigable> navigationElements = new ArrayList<>();
    private Class<? extends Navigable> defaultPage;
    private AccessControl accessControl;
    private RegistrationControl registrationControl;

    public WebAppDescription() {
    }

    public WebAppDescription(Class<? extends NavigationDesignInterface> defaultClass) {
        this.defaultClass = defaultClass;
    }

    public WebAppDescription(Class<? extends NavigationDesignInterface> defaultClass, Class<? extends NavigationDesignInterface> mobileClass) {
        this.defaultClass = defaultClass;
        this.mobileClass = mobileClass;
    }

    public WebAppDescription(Class<? extends NavigationDesignInterface> defaultClass, Class<? extends NavigationDesignInterface> mobileClass, Class<? extends LoginNavigable> loginClass) {
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

    public WebAppDescription withLoginPage(Class<? extends LoginNavigable> loginClass) {
        this.loginClass = loginClass;
        return this;
    }

    public WebAppDescription withNavigation(Navigable navigable) {
        navigationElements.add(navigable);
        return this;
    }

    public Class<? extends Navigable> getDefaultPage() {
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

    public LoginNavigable getLoginNavigable() {
        return loginNavigable;
    }

    public void setLoginNavigable(LoginNavigable loginNavigable) {
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

    public WebAppDescription withDefaultPage(Class<? extends Navigable> defaultPage) {
        this.defaultPage = defaultPage;
        return this;
    }

    public WebAppDescription withTitle(String title) {
        this.title = title;
        return this;
    }

    public WebAppDescription withConfiguration(String key, boolean value) {
        configuration.add(new AbstractMap.SimpleEntry<String, Boolean>(key, new Boolean(value)));
        return this;
    }

    public Stream<AbstractMap.SimpleEntry<String, Boolean>> getConfiguration() {
        return configuration.stream();
    }

    public Stream<Navigable> getNavigationElements() {
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
        if (defaultPage == null) {
            throw new InvalidWebDescriptionException("No defaultPage defined!");
        }
        if (loginClass != null && accessControl == null) {
            throw new InvalidWebDescriptionException("No accessControl defined!");
        }
        if (title == null) {
            throw new InvalidWebDescriptionException("No title defined!");
        }
        defaultView = (NavigationDesignInterface) createInstance(defaultClass);
        mobileView = (NavigationDesignInterface) createInstance(mobileClass);
        loginNavigable = (LoginNavigable) createInstance(loginClass);
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
