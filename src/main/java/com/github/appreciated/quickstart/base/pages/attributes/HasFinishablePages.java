package com.github.appreciated.quickstart.base.pages.attributes;

import com.github.appreciated.quickstart.base.pages.FinishablePage;
import com.github.appreciated.quickstart.base.pages.Page;

import java.util.List;

/**
 * Created by appreciated on 19.03.2017.
 */
public interface HasFinishablePages extends Page {
    List<FinishablePage> getPagingElements();
}
