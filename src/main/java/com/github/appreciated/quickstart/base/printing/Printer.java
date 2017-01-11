package com.github.appreciated.quickstart.base.printing;

import com.vaadin.server.Page;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 * Created by appreciated on 20.12.2016.
 */
public class Printer {
    public static void printComponent(Component component) {

        if (component.getId() != null && component.getId().length() > 0) {
            printElementWithId(component.getId());
        } else {
            String id = "quick-start-printId";
            component.getParent().setId(id);
            printElementWithId(id);
        }
    }

    /**
     * This "hack" is about as dirty it can be. Some Components are printable some are not because they don't share their
     * state properly with the client part. Do not use without proper testing. Also the actual Component you want to print
     * needs to be wrapped in an extra Horizontal Layout
     * - Expect fine tuning
     * @param domId
     */
    public static void printElementWithId(String domId) {
        UI.getCurrent().access(() -> Page.getCurrent().getJavaScript().execute(
                "var content = document.getElementById('" + domId + "');\n" +
                        "var printer = window.open('', '','left=0,top=0,width=auto,height=auto,toolbar=0,scrollbars=0,status=0');\n" +
                        "printer.document.write('<html><head><title>Printer</title><link href=\"VAADIN/themes/" + UI.getCurrent().getTheme() + "/styles.css\" rel=\"stylesheet\">" +
                        "<style>@page{size:auto; margin:5mm;} .v-caption { line-height: inherit !important;}</style>" +
                        "<script>(function(){setTimeout(function(){print();},500);})();</script>" +
                        "</head><body>');\n" +
                        "printer.document.write('<div class=\"" + UI.getCurrent().getTheme() + "\">');\n" +
                        "printer.document.write(content.innerHTML);\n" +
                        "printer.document.write('</div>');\n" +
                        "printer.document.write('</body></html>');\n" +
                        "printer.document.close();"));
    }
}
