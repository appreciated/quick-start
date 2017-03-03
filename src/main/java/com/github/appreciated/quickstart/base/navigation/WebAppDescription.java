package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.interfaces.LoginNavigable;
import com.github.appreciated.quickstart.base.interfaces.Navigable;
import com.github.appreciated.quickstart.base.interfaces.NavigationDesignInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

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

    public WebAppDescription withDefaultDesign(Class<? extends NavigationDesignInterface> desktopClass) {
        this.defaultClass = desktopClass;
        return this;
    }

    public WebAppDescription withMobileDesign(Class<? extends NavigationDesignInterface> mobileClass) {
        this.mobileClass = mobileClass;
        return this;
    }

    public WebAppDescription withLoginDesign(Class<? extends LoginNavigable> loginClass) {
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

    public void instanciateClasses() {
        defaultView = (NavigationDesignInterface) createInstance(defaultClass);
        mobileView = (NavigationDesignInterface) createInstance(mobileClass);
        loginNavigable = (LoginNavigable) createInstance(loginClass);
    }

    private Object createInstance(Class instanceClass) {
        if (instanceClass != null) {
            try {
                Constructor<?> constructor = instanceClass.getConstructor();
                return constructor.newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                e.getCause().printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                e.getCause().printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
                e.getCause().printStackTrace();
            } catch (InvocationTargetException e) {
                e.getCause().printStackTrace();
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.getCause().printStackTrace();
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

    public List<AbstractMap.SimpleEntry<String, Boolean>> getConfiguration() {
        return configuration;
        //return Arrays.asList(properties);
    }

    public List<Navigable> getNavigationElements() {
        return navigationElements;
    }
}
