package com.github.appreciated.quickstart.base.navigation.interfaces.attributes;

import com.github.appreciated.quickstart.base.navigation.Subpages;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;

/**
 * Created by appreciated on 09.03.2017.
 */
public interface HasSubpages extends Subpage {
    Subpages getPagingElements();
}
