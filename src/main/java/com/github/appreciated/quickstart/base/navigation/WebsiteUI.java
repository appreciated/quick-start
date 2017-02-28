package com.github.appreciated.quickstart.base.navigation;


import com.github.appreciated.quickstart.base.exception.InvalidWebsiteDefinitionException;
import com.github.appreciated.quickstart.base.interfaces.LoginNavigable;
import com.github.appreciated.quickstart.base.interfaces.Navigable;
import com.github.appreciated.quickstart.base.interfaces.WebsiteNavigationInterface;
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
public abstract class WebsiteUI extends UI {

    private WebsiteNavigationInterface navigation;
    private WebsiteNavigationInterface mobileNavigationView;
    private WebsiteNavigationInterface defaultNavigationView;
    private LoginNavigable loginNavigable;
    private WebsiteDefinition websiteDefinition;

    @Override
    public void init(VaadinRequest vaadinRequest) {
        websiteDefinition = initWebsiteDefinition();
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle(websiteDefinition.getTitle());
        websiteDefinition.instanciateClasses();
        defaultNavigationView = websiteDefinition.getDefaultView();
        mobileNavigationView = websiteDefinition.getMobileView();
        loginNavigable = websiteDefinition.getLoginNavigable();
        try {
            if (loginNavigable != null && !loginNavigable.getAccessControl().isUserSignedIn()) {
                loginNavigable.setAuthenticationListener(() -> {
                    try {
                        showMainView();
                    } catch (InvalidWebsiteDefinitionException e) {
                        e.printStackTrace();
                    }
                });
                setContent(loginNavigable);
            } else {
                showMainView();
            }
        } catch (InvalidWebsiteDefinitionException e) {
            e.printStackTrace();
        }
    }

    protected void showMainView() throws InvalidWebsiteDefinitionException {
        if (defaultNavigationView == null) {
            throw new InvalidWebsiteDefinitionException("No defaultNavigationView defined");
        }
        if (mobileNavigationView != null && isMobile()) {
            navigation = mobileNavigationView;
        } else {
            navigation = defaultNavigationView;
        }
        if (loginNavigable == null) {
            navigation.disableLogout();
        }
        setContent(navigation.getNavigation().getNavigatorView());
    }

    public static WebsiteUI get() {
        return (WebsiteUI) UI.getCurrent();
    }

    public static WebsiteNavigator getNavigation() {
        return get().navigation.getNavigation();
    }

    public static WebsiteNavigationInterface getNavigationView() {
        return get().navigation;
    }

    public static void navigateTo(Class<? extends Navigable> navigable) {
        get().navigation.getNavigation().navigateTo(navigable);
    }

    public static boolean isMobile() {
        WebBrowser browser = UI.getCurrent().getPage().getWebBrowser();
        return browser.isAndroid() || browser.isIOS() || browser.isWindowsPhone();
    }

    public void setMobileNavigationView(WebsiteNavigationInterface mobileNavigationView) {
        this.mobileNavigationView = mobileNavigationView;
    }

    public void setDefaultNavigationView(WebsiteNavigationInterface defaultNavigationView) {
        this.defaultNavigationView = defaultNavigationView;
    }

    public void setLoginNavigable(LoginNavigable loginNavigable) {
        this.loginNavigable = loginNavigable;
    }

    public abstract WebsiteDefinition initWebsiteDefinition();

    public WebsiteDefinition getWebsiteDefinition() {
        return websiteDefinition;
    }
}