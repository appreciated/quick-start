package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.navigation.exception.InvalidWebDescriptionException;
import com.github.appreciated.quickstart.base.navigation.interfaces.LoginPage;
import com.github.appreciated.quickstart.base.navigation.interfaces.NavigationDesignInterface;
import com.github.appreciated.quickstart.base.navigation.interfaces.Subpage;

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
    private List<Subpage> navigationElements = new ArrayList<>();
    private Class<? extends Subpage> defaultPage;
    private AccessControl accessControl;
    private RegistrationControl registrationControl;
    private Subpages navigationDescription;

    public Class<? extends Subpage> getDefaultPage() {
        return defaultPage;
    }

    public NavigationDesignInterface getDefaultView() {
        return defaultView;
    }

    public NavigationDesignInterface getMobileView() {
        return mobileView;
    }

    public LoginPage getLoginNavigable() {
        return loginNavigable;
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


    public List<AbstractMap.SimpleEntry<String, Boolean>> getConfiguration() {
        return configuration;
    }

    public Stream<Subpage> getSubpages() {
        return navigationElements.stream();
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
        if (navigationDescription.getSubpages().size() == 0) {
            throw new InvalidWebDescriptionException("No navigation elements defined defined!");
        }
        if (defaultPage == null) {
            defaultPage = navigationDescription.getSubpages().get(0).getClass();
        }
        this.navigationElements = navigationDescription.getSubpages();
        for (Subpage navigationElement : navigationElements) {
            if (navigationElement.getNavigationName() == null) {
                throw new InvalidWebDescriptionException("No navigationName defined!");
            }
        }

        defaultView = (NavigationDesignInterface) createInstance(defaultClass);
        mobileView = (NavigationDesignInterface) createInstance(mobileClass);
        loginNavigable = (LoginPage) createInstance(loginClass);
        if (loginNavigable != null) {
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

    public void setDefaultClass(Class<? extends NavigationDesignInterface> defaultClass) {
        this.defaultClass = defaultClass;
    }

    public void setMobileClass(Class<? extends NavigationDesignInterface> mobileClass) {
        this.mobileClass = mobileClass;
    }

    public void setLoginClass(Class<? extends LoginPage> loginClass) {
        this.loginClass = loginClass;
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
}
