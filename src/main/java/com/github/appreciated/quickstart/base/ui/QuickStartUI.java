package com.github.appreciated.quickstart.base.ui;


import com.github.appreciated.quickstart.base.authentication.login.CurrentUser;
import com.github.appreciated.quickstart.base.error.ErrorPageDesign;
import com.github.appreciated.quickstart.base.navigation.WebAppDescription;
import com.github.appreciated.quickstart.base.navigation.WebsiteNavigator;
import com.github.appreciated.quickstart.base.navigation.exception.InvalidWebDescriptionException;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.LoginImplementationView;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.NavigationView;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.splashscreen.CustomSplashScreenConfigurator;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.UI;
import org.vaadin.leif.splashscreen.SplashScreenConfigurator;
import org.vaadin.leif.splashscreen.SplashScreenHandler;

import javax.servlet.ServletException;

/**
 * Created by appreciated on 10.12.2016.
 */
@Push
@Viewport("user-scalable=no,initial-scale=1.0")
@SplashScreenConfigurator(value = CustomSplashScreenConfigurator.class)
public abstract class QuickStartUI extends UI {

    QuickStartDesignProvider provider;
    private NavigationView navigation;
    private NavigationView mobileView;
    private NavigationView defaultView;
    private LoginImplementationView quickStartLogin;
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
            provider = description.getProvider();
            defaultView = description.getDefaultView();
            mobileView = description.getMobileView();
            quickStartLogin = description.getLoginNavigable();
            if (quickStartLogin != null && !getWebsiteDescription().getAccessControl().isUserSignedIn()) {
                quickStartLogin.initWithLoginListener(() -> showMainView());
                setContent(quickStartLogin);
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
        if (quickStartLogin == null) {
            navigation.disableLogout();
        }
        navigator = new WebsiteNavigator(navigation,getProvider());
        navigator.initNavigationElements(getWebsiteDescription().getSubpages());
        navigation.initNavigationElements(getWebsiteDescription().getSubpages());
        navigation.initUserFunctionality(getWebsiteDescription());
        navigation.initWithTitle(getWebsiteDescription().getTitle());
        navigation.initWithConfiguration(getWebsiteDescription().getConfiguration().stream());
        navigator.navigateToDefaultPage();
        setContent(navigation);
    }

    public static QuickStartUI get() {
        return (QuickStartUI) UI.getCurrent();
    }

    @Override
    public WebsiteNavigator getNavigator() {
        return navigator;
    }

    public static WebsiteNavigator getNavigation() {
        return get().navigator;
    }

    public static NavigationView getNavigationView() {
        return get().navigation;
    }

    public static void navigateTo(Class<? extends Subpage> page) {
        getNavigation().navigateTo(page);
    }

    public static boolean isMobile() {
        WebBrowser browser = UI.getCurrent().getPage().getWebBrowser();
        return browser.isAndroid() || browser.isIOS() || browser.isWindowsPhone();
    }

    public abstract WebAppDescription initWebAppDescription() throws InvalidWebDescriptionException;

    public static WebAppDescription getWebsiteDescription() {
        return get() != null ? get().description : null;
    }

    public static boolean isUserSignedIn() {
        return getWebsiteDescription().getAccessControl().isUserSignedIn();
    }

    public static QuickStartDesignProvider getProvider() {
        return getWebsiteDescription() != null ? getWebsiteDescription().getProvider() : null;
    }

    public static class QuickStartServlet extends VaadinServlet {
        @Override
        public void init() throws ServletException {
            if (getService() != null) {
                SplashScreenHandler.init(getService());
            }
        }
    }

}
