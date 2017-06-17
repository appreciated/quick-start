package com.github.appreciated.quickstart.base.splashscreen;

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
        Class<? extends UI> uiClass = environment.getUiClass();
        SplashScreen splashScreen = uiClass.getAnnotation(SplashScreen.class);
        if(splashScreen == null) {
            return null;
        } else {
            SplashScreenConfiguration splashScreenConfiguration = new SplashScreenConfiguration();
            String fileName = splashScreen.value();
            splashScreenConfiguration.setContents(getSplashContents(uiClass, fileName));
            splashScreenConfiguration.setWidth(splashScreen.width());
            splashScreenConfiguration.setHeight(splashScreen.height());
            splashScreenConfiguration.setAutohide(splashScreen.autohide());
            return splashScreenConfiguration;
        }
    }

    public static List<Node> getSplashContents(Class<? extends UI> uiClass, String fileName) {
        if(!fileName.endsWith(".html") && !fileName.endsWith(".htm")) {
            if(!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
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
