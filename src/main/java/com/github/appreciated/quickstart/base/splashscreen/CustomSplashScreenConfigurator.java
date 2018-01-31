package com.github.appreciated.quickstart.base.splashscreen;

import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.ui.UI;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.vaadin.leif.splashscreen.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Modified by appreciated on 07.04.2017.
 */
public class CustomSplashScreenConfigurator implements Configurator {
    public CustomSplashScreenConfigurator() {
    }

    public SplashScreenConfiguration getConfiguration(SplashScreenEnvironment environment) {

        SplashScreen splashScreen = QuickStartDesignProvider.get() != null ? QuickStartDesignProvider.get().getAnnotation() : null;
        if (splashScreen == null) {
            return null;
        } else {
            SplashScreenConfiguration config = new SplashScreenConfiguration();
            String fileName = splashScreen.value();
            config.setContents(getSplashContents(UI.getCurrent().getClass(), fileName));
            config.setWidth(splashScreen.width());
            config.setHeight(splashScreen.height());
            config.setAutohide(splashScreen.autohide());
            return config;
        }
    }

    public static List<Node> getSplashContents(Class<? extends UI> uiClass, String fileName) {
        if (!fileName.endsWith(".html") && !fileName.endsWith(".htm")) {
            if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
                throw new RuntimeException("Unsupported file extension for: " + fileName);
            } else {
                return getSplashContentsImage(fileName);
            }
        } else {
            return getSplashContentsHtml(uiClass, fileName);
        }
    }

    public static List<Node> getSplashContentsImage(String src) {
        return Collections.singletonList((new Element(Tag.valueOf("img"), "")).attr("src", src));
    }

    public static List<Node> getSplashContentsHtml(Class<? extends UI> uiClass, String fileName) {
        try {
            InputStream splashResource = uiClass.getResourceAsStream(fileName);
            if (splashResource == null) {
                splashResource = DefaultConfigurator.class.getClassLoader().getResourceAsStream(fileName);
            }
            if (splashResource == null) {
                throw new RuntimeException("Couldn't find splash screen file "
                        + fileName + " for " + uiClass.getName());
            }
            Document parse = Jsoup.parse(splashResource, "UTF-8", "");
            List<Node> contents = new ArrayList<>(parse.head().childNodes());
            contents.addAll(parse.body().childNodes());
            return contents;
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read splash screen file "
                    + fileName + " for " + uiClass.getName(), e);
        }
    }

}
