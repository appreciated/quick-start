package com.github.appreciated.quickstart.base.navigation;


import com.github.appreciated.quickstart.base.interfaces.LoginNavigable;
import com.github.appreciated.quickstart.base.interfaces.Navigable;
import com.github.appreciated.quickstart.base.interfaces.NavigatorInterface;
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
public abstract class NavigationUI extends UI {

    private NavigatorInterface navigator;
    private NavigatorInterface mobileNavigationView;
    private NavigatorInterface desktopNavigationView;
    private LoginNavigable loginNavigable;

    @Override
    public void init(VaadinRequest vaadinRequest) {
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle(getTitle());
        if (loginNavigable != null && !loginNavigable.getAccessControl().isUserSignedIn()) {
            loginNavigable.setAuthenticationListener(() -> showMainView());
            setContent(loginNavigable);
        } else {
            showMainView();
        }
    }

    protected void showMainView() {
        if (mobileNavigationView != null && isMobile()) {
            navigator = mobileNavigationView;
        } else {
            navigator = desktopNavigationView;
        }
        if (loginNavigable == null) {
            navigator.disableLogout();
        }
        setContent(navigator.getNavigation().getNavigatorView());
    }

    public static NavigationUI get() {
        return (NavigationUI) UI.getCurrent();
    }

    public static Navigation getNavigation() {
        return get().navigator.getNavigation();
    }

    public static NavigatorInterface getNavigationView() {
        return get().navigator;
    }

    public static void navigateTo(Class<? extends Navigable> navigable) {
        get().navigator.getNavigation().navigateTo(navigable);
    }

    public static boolean isMobile() {
        WebBrowser browser = UI.getCurrent().getPage().getWebBrowser();
        return browser.isAndroid() || browser.isIOS() || browser.isWindowsPhone();
    }

    public abstract String getTitle();

    public void setMobileNavigationView(NavigatorInterface mobileNavigationView) {
        this.mobileNavigationView = mobileNavigationView;
    }

    public void setDesktopNavigationView(NavigatorInterface desktopNavigationView) {
        this.desktopNavigationView = desktopNavigationView;
    }

    public void setLoginNavigable(LoginNavigable loginNavigable) {
        this.loginNavigable = loginNavigable;
    }

}
