package com.github.appreciated.quickstart.base.navigation.interfaces.attributes;

import com.github.appreciated.quickstart.base.navigation.interfaces.Finishable;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;

import java.util.List;

/**
 * Created by appreciated on 19.03.2017.
 */
public interface HasFinishableSubpages extends Subpage {
    List<Finishable> getPagingElements();
}
