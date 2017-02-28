package com.github.appreciated.quickstart.base.interfaces;

import com.github.appreciated.quickstart.base.navigation.WebsiteDefinition;
import com.github.appreciated.quickstart.base.navigation.WebsiteNavigator;
import com.github.appreciated.quickstart.base.navigation.WebsiteUI;
import com.vaadin.ui.UI;

/**
 * Created by appreciated on 10.12.2016.
 */
public interface WebsiteNavigationInterface {

    public WebsiteNavigator getNavigation();

    public default WebsiteDefinition getDefinition(){
       return  ((WebsiteUI)UI.getCurrent()).getWebsiteDefinition();
    };

    public void disableLogout();

}
