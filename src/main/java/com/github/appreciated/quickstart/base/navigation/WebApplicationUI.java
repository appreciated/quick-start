package com.github.appreciated.quickstart.base.navigation;


import com.github.appreciated.quickstart.base.authentication.login.CurrentUser;
import com.github.appreciated.quickstart.base.error.ErrorPageDesign;
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
    private NavigationDesignInterface mobileView;
    private NavigationDesignInterface defaultView;
    private LoginPage loginPage;
    private WebAppDescription description;
    private WebsiteNavigator navigator;

    public static String getUsername() {
        return CurrentUser.get();
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            description = initWebAppDescription().init(isMobile());
            setLocale(vaadinRequest.getLocale());
            getPage().setTitle(description.getTitle());
            defaultView = description.getDefaultView();
            mobileView = description.getMobileView();
            loginPage = description.getLoginNavigable();
            if (loginPage != null && !getWebsiteDescription().getAccessControl().isUserSignedIn()) {
                loginPage.initWithLoginListener(() -> showMainView());
                setContent(loginPage);
            } else {
                showMainView();
            }
        } catch (Exception e) {
            e.printStackTrace();
            setContent(new ErrorPageDesign());
        }
    }

    protected void showMainView() {
        if (mobileView != null && isMobile()) {
            navigation = mobileView;
        } else {
            navigation = defaultView;
        }
        if (loginPage == null) {
            navigation.disableLogout();
        }
        navigator = new WebsiteNavigator(navigation);
        navigator.initNavigationElements(getWebsiteDescription().getSubpages());
        navigation.initNavigationElements(getWebsiteDescription().getSubpages());
        navigation.initUserFunctionality(getWebsiteDescription());
        navigation.initWithTitle(getWebsiteDescription().getTitle());
        navigation.initWithConfiguration(getWebsiteDescription().getConfiguration().stream());
        navigator.navigateToDefaultPage();
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

    public void setMobileView(NavigationDesignInterface mobileView) {
        this.mobileView = mobileView;
    }

    public void setDefaultView(NavigationDesignInterface defaultView) {
        this.defaultView = defaultView;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public abstract WebAppDescription initWebAppDescription() throws InvalidWebDescriptionException;

    public static WebAppDescription getWebsiteDescription() {
        return get().description;
    }

    public static boolean isUserSignedIn() {
        return getWebsiteDescription().getAccessControl().isUserSignedIn();
    }
}
