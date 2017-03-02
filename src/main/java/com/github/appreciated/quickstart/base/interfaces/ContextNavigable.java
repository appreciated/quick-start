package com.github.appreciated.quickstart.base.interfaces;
import com.github.appreciated.quickstart.base.navigation.actions.Action;

import java.util.List;

/**
 * Created by appreciated on 11.12.2016.
 */
public interface ContextNavigable extends Navigable {


    /**
     * Arrays.asList(new AbstractMap.SimpleEntry<>(new Color(), FontAwesome.PLANE));
     *
     * @return
     */
    public List<Action> getContextActions();


}
