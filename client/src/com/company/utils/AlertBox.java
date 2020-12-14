package com.company.utils;

import javafx.scene.control.Alert;

public class AlertBox {
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";

    private String type;
    private String textContent;

    public AlertBox(String type, String textContent) {
        this.type = type;
        this.textContent = textContent;
    }

    public void show() {
        switch (type) {
            case ERROR: {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setContentText(textContent);
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            break;
            case SUCCESS: {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setContentText(textContent);
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            break;
        }
    }

}
