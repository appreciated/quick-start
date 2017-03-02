package com.github.appreciated.quickstart.base.interfaces;

import com.github.appreciated.quickstart.base.navigation.WebAppDescription;
import com.github.appreciated.quickstart.base.navigation.WebsiteNavigator;
import com.github.appreciated.quickstart.base.navigation.WebApplicationUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 * Created by appreciated on 10.12.2016.
 */
public interface NavigationDesignInterface extends Component {

    public WebsiteNavigator getNavigation();

    public default WebAppDescription getDefinition(){
       return  ((WebApplicationUI)UI.getCurrent()).getWebsiteDescriptor();
    };

    public void disableLogout();

    public void setCurrentContainerLabel(String label);

    public void setCurrentActions(ContextNavigable actions);

    public void setCurrentSearchNavigable(SearchNavigable navigable);
}
