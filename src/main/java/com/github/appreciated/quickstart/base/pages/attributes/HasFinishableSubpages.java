package com.github.appreciated.quickstart.base.pages.attributes;

import com.github.appreciated.quickstart.base.pages.Finishable;
import com.github.appreciated.quickstart.base.pages.Subpage;

import java.util.List;

/**
 * Created by appreciated on 19.03.2017.
 */
public interface HasFinishableSubpages extends Subpage {
    List<Finishable> getPagingElements();
}
