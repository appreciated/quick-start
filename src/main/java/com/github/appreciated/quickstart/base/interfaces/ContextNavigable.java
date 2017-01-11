package com.github.appreciated.quickstart.base.interfaces;
import com.github.appreciated.quickstart.base.navigation.Action;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by appreciated on 11.12.2016.
 */
public interface ContextNavigable {


    /**
     * Arrays.asList(new AbstractMap.SimpleEntry<>(new Color(), FontAwesome.PLANE));
     *
     * @return
     */
    public List<Action> getContextIcons();

    public void onContextButtonClicked(Resource resource);

    public void generatedButtons(List<HashMap.SimpleEntry<Resource, Component>> buttons);

}
