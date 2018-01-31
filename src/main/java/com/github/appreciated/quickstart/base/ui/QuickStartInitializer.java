package com.github.appreciated.quickstart.base.ui;

import com.github.appreciated.quickstart.base.authentication.login.User;
import com.github.appreciated.quickstart.base.error.ErrorPageDesign;
import com.github.appreciated.quickstart.base.navigation.description.WebAppDescription;
import com.github.appreciated.quickstart.base.navigation.theme.LoginView;
import com.github.appreciated.quickstart.base.navigation.theme.PageHolder;
import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.vaadin.leif.splashscreen.SplashScreenHandler;

import javax.servlet.ServletException;

public class QuickStartInitializer {

    private final QuickStartInitializerUI ui;
    QuickStartDesignProvider provider;
    private PageHolder navigation;
    private LoginView quickStartLogin;
    private WebAppDescription description;

    public static String getUsername() {
        return User.get();
    }


    QuickStartInitializer(QuickStartInitializerUI ui){
        this.ui = ui;
        try {
            description = ui.initWebAppDescription().init();
            ui.getPage().setTitle(description.getTitle());
            provider = description.getProvider();
            ui.getSession().setAttribute(QuickStartDesignProvider.class,provider);
            navigation = provider.getDesktopDesign().newInstance();
            quickStartLogin = description.getLoginNavigable();
            if (quickStartLogin != null && !description.getAccessControl().isUserSignedIn()) {
                quickStartLogin.initWithLoginListener(() -> showMainView());
                ui.setContent(quickStartLogin);
            } else {
                showMainView();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ui.setContent(new ErrorPageDesign());
        }
    }

    protected void showMainView() {
        if (quickStartLogin == null) {
            navigation.disableLogout();
        }
        navigation.initNavigationElements(description.getSubpages());
        navigation.initUserFunctionality(description);
        navigation.initWithTitle(description.getTitle());
        navigation.initWithConfiguration(description.getConfiguration().stream());
        ui.setContent(navigation);
    }


    public static boolean isUserSignedIn() {
        return UI.getCurrent().getSession().getAttribute("userSignedIn").equals("true");
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
