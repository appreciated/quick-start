package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.interfaces.LoginNavigable;
import com.github.appreciated.quickstart.base.interfaces.Navigable;
import com.github.appreciated.quickstart.base.interfaces.WebsiteNavigationInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by appreciated on 01.01.2017.
 */
public class WebsiteDefinition {

    private Class<? extends WebsiteNavigationInterface> defaultClass;
    private Class<? extends WebsiteNavigationInterface> mobileClass;
    private Class<? extends LoginNavigable> loginClass;

    private WebsiteNavigationInterface defaultView;
    private WebsiteNavigationInterface mobileView;
    private LoginNavigable loginNavigable;
    private String title;
    private List<AbstractMap.SimpleEntry<String, Boolean>> configuration = new ArrayList<>();
    private List<Navigable> navigationElements = new ArrayList<>();
    private Class<? extends Navigable> defaultPage;

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

    public WebsiteDefinition withDefaultDesign(Class<? extends WebsiteNavigationInterface> desktopClass) {
        this.defaultClass = desktopClass;
        return this;
    }

    public WebsiteDefinition withMobileDesign(Class<? extends WebsiteNavigationInterface> mobileClass) {
        this.mobileClass = mobileClass;
        return this;
    }

    public WebsiteDefinition withLoginDesign(Class<? extends LoginNavigable> loginClass) {
        this.loginClass = loginClass;
        return this;
    }

    public WebsiteDefinition withNavigation(Navigable navigable) {
        navigationElements.add(navigable);
        return this;
    }

    public Class<? extends Navigable> getDefaultPage() {
        return defaultPage;
    }

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
            e.printStackTrace();
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public WebsiteDefinition withDefaultPage(Class<? extends Navigable> defaultPage) {
        this.defaultPage = defaultPage;
        return this;
    }

    public WebsiteDefinition withTitle(String title) {
        this.title = title;
        return this;
    }

    public WebsiteDefinition withConfiguration(String key, boolean value) {
        configuration.add(new AbstractMap.SimpleEntry<String, Boolean>(key, new Boolean(value)));
        return this;
    }

    public List<AbstractMap.SimpleEntry<String, Boolean>> getConfiguration() {
        return configuration;
        //return Arrays.asList(properties);
    }

    public List<Navigable> getNavigationElements() {
        return navigationElements;
    }
}
