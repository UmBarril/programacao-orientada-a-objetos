package org.barril;

import javafx.application.Platform;

public class Main {
    public static void main(String[] args) {
        Platform.startup(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}
