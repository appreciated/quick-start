package com.github.appreciated.quickstart.base.navigation;


import com.github.appreciated.quickstart.base.navigation.exception.InvalidWebDescriptionException;
import com.github.appreciated.quickstart.base.navigation.interfaces.LoginPage;
import com.github.appreciated.quickstart.base.navigation.interfaces.NavigationDesignInterface;
import com.github.appreciated.quickstart.base.navigation.interfaces.Subpage;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.UI;

/**
 * Created by appreciated on 10.12.2016.
 */
@Push
@Viewport("user-scalable=no,initial-scale=1.0")
public abstract class WebApplicationUI extends UI {

    private NavigationDesignInterface navigation;
    private NavigationDesignInterface mobileNavigationView;
    private NavigationDesignInterface defaultNavigationView;
    private LoginPage loginNavigable;
    private WebAppDescription websiteDescription;
    private WebsiteNavigator navigator;

    @Override
    public final void init(VaadinRequest vaadinRequest) {
        try {
            websiteDescription = initWebAppDescription().init();
            setLocale(vaadinRequest.getLocale());
            getPage().setTitle(websiteDescription.getTitle());
            defaultNavigationView = websiteDescription.getDefaultView();
            mobileNavigationView = websiteDescription.getMobileView();
            loginNavigable = websiteDescription.getLoginNavigable();
            if (loginNavigable != null && !getWebsiteDescription().getAccessControl().isUserSignedIn()) {
                loginNavigable.setAuthenticationListener(() -> showMainView());
                setContent(loginNavigable);
            } else {
                showMainView();
            }
        } catch (InvalidWebDescriptionException e) {
            e.printStackTrace();
        }
    }

    protected void showMainView() {
        if (mobileNavigationView != null && isMobile()) {
            navigation = mobileNavigationView;
        } else {
            navigation = defaultNavigationView;
        }
        if (loginNavigable == null) {
            navigation.disableLogout();
        }
        navigation.initNavigationElements(getWebsiteDescription().getSubpages());
        navigation.initUserFunctionality(getWebsiteDescription());
        navigation.initWithTitle(getWebsiteDescription().getTitle());
        navigation.initWithConfiguration(getWebsiteDescription().getConfiguration().stream());
        navigator = new WebsiteNavigator(navigation);
        setContent(navigation);
    }

    public static WebApplicationUI get() {
        return (WebApplicationUI) UI.getCurrent();
    }

    public static WebsiteNavigator getNavigation() {
        return get().navigator;
    }

    public static NavigationDesignInterface getNavigationView() {
        return get().navigation;
    }

    public static void navigateTo(Class<? extends Subpage> page) {
        getNavigation().navigateTo(page);
    }

    public static boolean isMobile() {
        WebBrowser browser = UI.getCurrent().getPage().getWebBrowser();
        return browser.isAndroid() || browser.isIOS() || browser.isWindowsPhone();
    }

    public void setMobileNavigationView(NavigationDesignInterface mobileNavigationView) {
        this.mobileNavigationView = mobileNavigationView;
    }

    public void setDefaultNavigationView(NavigationDesignInterface defaultNavigationView) {
        this.defaultNavigationView = defaultNavigationView;
    }

    public void setLoginNavigable(LoginPage loginNavigable) {
        this.loginNavigable = loginNavigable;
    }

    public abstract WebAppDescription initWebAppDescription() throws InvalidWebDescriptionException;

    public static WebAppDescription getWebsiteDescription() {
        return get().websiteDescription;
    }
}
