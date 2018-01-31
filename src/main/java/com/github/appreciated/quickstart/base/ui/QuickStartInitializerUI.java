package com.github.appreciated.quickstart.base.ui;

import com.github.appreciated.quickstart.base.navigation.description.WebAppDescription;
import com.github.appreciated.quickstart.base.navigation.exception.InvalidWebDescriptionException;
import com.vaadin.ui.UI;

abstract class QuickStartInitializerUI extends UI {
    public abstract WebAppDescription initWebAppDescription() throws InvalidWebDescriptionException;
}
