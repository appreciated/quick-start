package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.interfaces.LoginNavigable;
import com.github.appreciated.quickstart.base.interfaces.Navigable;
import com.github.appreciated.quickstart.base.interfaces.WebsiteNavigationInterface;
import com.vaadin.ui.Button;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

/**
 * Created by appreciated on 01.01.2017.
 */
public abstract class WebsiteDefinition {

    private Class<? extends WebsiteNavigationInterface> defaultClass;
    private Class<? extends WebsiteNavigationInterface> mobileClass;
    private Class<? extends LoginNavigable> loginClass;

    private WebsiteNavigationInterface defaultView;
    private WebsiteNavigationInterface mobileView;
    private LoginNavigable loginNavigable;

    public WebsiteDefinition() {
    }

    public WebsiteDefinition(Class<? extends WebsiteNavigationInterface> defaultClass) {
        this.defaultClass = defaultClass;
    }

    public WebsiteDefinition(Class<? extends WebsiteNavigationInterface> defaultClass, Class<? extends WebsiteNavigationInterface> mobileClass) {
        this.defaultClass = defaultClass;
        this.mobileClass = mobileClass;
    }

    public WebsiteDefinition(Class<? extends WebsiteNavigationInterface> defaultClass, Class<? extends WebsiteNavigationInterface> mobileClass, Class<? extends LoginNavigable> loginClass) {
        this.defaultClass = defaultClass;
        this.mobileClass = mobileClass;
        this.loginClass = loginClass;
    }

    public WebsiteDefinition withDesktop(Class<? extends WebsiteNavigationInterface> desktopClass) {
        this.defaultClass = desktopClass;
        return this;
    }

    public WebsiteDefinition withMobile(Class<? extends WebsiteNavigationInterface> mobileClass) {
        this.mobileClass = mobileClass;
        return this;
    }

    public WebsiteDefinition withLogin(Class<? extends LoginNavigable> loginClass) {
        this.loginClass = loginClass;
        return this;
    }

    public abstract String getTitle();

    public abstract List<AbstractMap.SimpleEntry<Button, Navigable>> getButtons();

    public abstract List<AbstractMap.SimpleEntry<String, Boolean>> getConfiguration();

    public AbstractMap.SimpleEntry<Button, Navigable> getButton(Button button, Navigable navigable) {
        return new AbstractMap.SimpleEntry<Button, Navigable>(button, navigable);
    }

    public List<AbstractMap.SimpleEntry<Button, Navigable>> getButtons(AbstractMap.SimpleEntry<Button, Navigable>... buttons) {
        return Arrays.asList(buttons);
    }

    public AbstractMap.SimpleEntry<String, Boolean> getConfiguration(String key, boolean value) {
        return new AbstractMap.SimpleEntry<String, Boolean>(key, new Boolean(value));
    }

    public List<AbstractMap.SimpleEntry<String, Boolean>> getConfigurations(AbstractMap.SimpleEntry<String, Boolean>... properties) {
        return Arrays.asList(properties);
    }

    public abstract Class<? extends Navigable> getStartNavigable();

    public WebsiteNavigationInterface getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(WebsiteNavigationInterface defaultView) {
        this.defaultView = defaultView;
    }

    public WebsiteNavigationInterface getMobileView() {
        return mobileView;
    }

    public void setMobileView(WebsiteNavigationInterface mobileView) {
        this.mobileView = mobileView;
    }

    public LoginNavigable getLoginNavigable() {
        return loginNavigable;
    }

    public void setLoginNavigable(LoginNavigable loginNavigable) {
        this.loginNavigable = loginNavigable;
    }

    public void instanciateClasses() {
        defaultView = (WebsiteNavigationInterface) createInstance(defaultClass);
        mobileView = (WebsiteNavigationInterface) createInstance(mobileClass);
        loginNavigable = (LoginNavigable) createInstance(loginClass);
    }

    private Object createInstance(Class instanceClass) {
        try {
            Constructor<?> constructor = instanceClass.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace();
            //e.printStackTrace();
        }
        return null;
    }
}
