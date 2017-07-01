package com.github.appreciated.quickstart.base.pages.attributes;

import com.github.appreciated.quickstart.base.navigation.description.Subpages;
import com.github.appreciated.quickstart.base.pages.Page;

/**
 * Created by appreciated on 09.03.2017.
 */
public interface HasSubpages extends Page {
    Subpages getPagingElements();
}
