package com.github.appreciated.quickstart.base.navigation.description;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationControl;
import com.github.appreciated.quickstart.base.navigation.exception.InvalidWebDescriptionException;
import com.github.appreciated.quickstart.base.navigation.theme.LoginView;
import com.github.appreciated.quickstart.base.navigation.theme.PageHolder;
import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.pages.Page;
import com.vaadin.ui.UI;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by appreciated on 01.01.2017.
 */
public class WebAppDescription {
    private Class<? extends PageHolder> defaultClass;
    private Class<? extends PageHolder> mobileClass;
    private Class<? extends LoginView> loginClass;

    private QuickStartDesignProvider provider;
    private PageHolder defaultView;
    private PageHolder mobileView;
    private LoginView loginNavigable;
    private String title;
    private List<AbstractMap.SimpleEntry<String, Boolean>> configuration = new ArrayList<>();
    private List<Page> navigationElements = new ArrayList<>();
    private Class<? extends Page> defaultPage;
    private AccessControl accessControl;
    private RegistrationControl registrationControl;
    private Subpages navigationDescription;
    private Class<? extends QuickStartDesignProvider> designProvider;
    private boolean loginPage;

    public Class<? extends Page> getDefaultPage() {
        return defaultPage;
    }

    public PageHolder getDefaultView() {
        return defaultView;
    }

    public PageHolder getMobileView() {
        return mobileView;
    }

    public LoginView getLoginNavigable() {
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

    public Stream<Page> getSubpages() {
        return navigationElements.stream();
    }


    public AccessControl getAccessControl() {
        return accessControl;
    }

    public WebAppDescription init(boolean isMobile) throws InvalidWebDescriptionException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (designProvider == null) {
            throw new InvalidWebDescriptionException("The WebDescription in "+ UI.getCurrent().getClass().getSimpleName()+".java has no DesignProvider defined add one by using WebAppDescriptionBuilder::withDesignProvider(...)!");
        }
        if (loginClass != null && accessControl == null) {
            throw new InvalidWebDescriptionException("The WebDescription in "+ UI.getCurrent().getClass().getSimpleName()+".java has no AccessControl but a LoginPage defined, add one by using WebAppDescriptionBuilder::withAccessControl(...)!");
        }
        if (title == null) {
            throw new InvalidWebDescriptionException("The WebDescription in "+ UI.getCurrent().getClass().getSimpleName()+".java has no title defined, add one by using WebAppDescriptionBuilder::withTitle(...)!");
        }
        if (navigationDescription.getPages().size() == 0) {
            throw new InvalidWebDescriptionException("The WebDescription in "+ UI.getCurrent().getClass().getSimpleName()+".java has no navigation elements defined, add them by using WebAppDescriptionBuilder::withSubpages(...)");
        }
        if (defaultPage == null) {
            defaultPage = navigationDescription.getPages().getFirst().getClass();
        }
        this.navigationElements = navigationDescription.getPages();
        for (Page navigationElement : navigationElements) {
            if (navigationElement.getNavigationName() == null) {
                throw new InvalidWebDescriptionException("The class " + navigationElement.getClass().getSimpleName() + ".java has no SubpageDescription Annotation, please add one!");
            }
        }
        provider = (QuickStartDesignProvider) createInstance(designProvider);
        if (!isMobile) {
            defaultView = (PageHolder) createInstance(provider.getDesktopDesign());
        } else {
            mobileView = (PageHolder) createInstance(provider.getMobileDesign());
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

    public void setDefaultClass(Class<? extends PageHolder> defaultClass) {
        this.defaultClass = defaultClass;
    }

    public void setMobileClass(Class<? extends PageHolder> mobileClass) {
        this.mobileClass = mobileClass;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDefaultPage(Class<? extends Page> defaultPage) {
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
