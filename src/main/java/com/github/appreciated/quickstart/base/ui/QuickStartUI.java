package com.github.appreciated.quickstart.base.ui;


import com.github.appreciated.quickstart.base.splashscreen.CustomSplashScreenConfigurator;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import org.vaadin.leif.splashscreen.SplashScreenConfigurator;

/**
 * Created by appreciated on 10.12.2016.
 */
@Push
@Viewport("user-scalable=no,initial-scale=1.0")
@SplashScreenConfigurator(value = CustomSplashScreenConfigurator.class)
public abstract class QuickStartUI extends QuickStartInitializerUI {

    private QuickStartInitializer initalizer;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        initalizer = new QuickStartInitializer(this);
    }

}
