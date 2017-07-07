package com.github.appreciated.quickstart.base.navigation.theme;

import com.github.appreciated.quickstart.base.pages.ComponentPage;
import com.github.appreciated.quickstart.base.pages.Page;
import com.github.appreciated.quickstart.base.pages.attributes.PageManager;

/**
 * Created by Johannes on 01.07.2017.
 */
public interface ContainerPageView extends ComponentPage, PageManager {
    Page getContainedPage();
}
