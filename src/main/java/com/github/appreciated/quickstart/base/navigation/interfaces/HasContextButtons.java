package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.github.appreciated.quickstart.base.navigation.actions.Action;

import java.util.List;

/**
 * Created by appreciated on 11.12.2016.
 */
public interface HasContextButtons {


    /**
     * Arrays.asList(new AbstractMap.SimpleEntry<>(new Color(), FontAwesome.PLANE));
     *
     * @return
     */
    public List<Action> getContextActions();


}
