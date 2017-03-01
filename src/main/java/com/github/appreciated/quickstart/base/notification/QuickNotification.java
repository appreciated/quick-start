package com.github.appreciated.quickstart.base.notification;

import com.vaadin.ui.Notification;

import static com.vaadin.ui.Notification.show;

/**
 * Created by appreciated on 21.12.2016.
 */
public class QuickNotification {
    public static void showMessageTray(String message) {
        show(message, message, com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showMessage(String title, String message) {
        show(title, message, Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageError(String message) {
        show(message, com.vaadin.ui.Notification.Type.ERROR_MESSAGE);
    }

}
