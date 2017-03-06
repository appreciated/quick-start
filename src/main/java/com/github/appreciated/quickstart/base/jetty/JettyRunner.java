package com.github.appreciated.quickstart.base.jetty;

import com.vaadin.server.VaadinServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Only for Development and Testing do not use for production!
 * Created by appreciated on 20.12.2016.
 */
public class JettyRunner {
    private Server server;
    private WebAppContext context;
    private int port = 8080;

    public JettyRunner(Class<? extends VaadinServlet> servlet) throws IOException {
        this(new File(".").getCanonicalPath(),  servlet);
    }

    private JettyRunner(String path, Class<? extends VaadinServlet> servlet) {
        Properties props = new Properties();
        server = new Server(port);
        context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceAlias("/VAADIN/", "/");
        ResourceCollection resources = new ResourceCollection(new String[]{
                //path + "/src/main/webapp/VAADIN", // Theme (mvn vaadin:update-theme)
                path + "/target/classes/VAADIN" // Widgetset (mvn vaadin:update-widgetset)
        });
        context.setBaseResource(resources);
        ServletHolder servletHolder = new ServletHolder(servlet);
        context.addServlet(servletHolder, "/*");
        server.setHandler(context);
    }

    public JettyRunner withPort(int port) {
        this.port = port;
        return this;
    }

    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebAppContext getContext() {
        return context;
    }
}


