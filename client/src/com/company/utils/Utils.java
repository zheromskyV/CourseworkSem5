package com.company.utils;

import com.company.client.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Utils {
    public static void closeWindow(Control control) {
        control.getScene().getWindow().hide();
    }

    public static void setUpStage(Stage stage, Parent root) {
        String extraTitle = Client.self == null
                ? ""
                : "   [- " + Client.self.getName() + " " + Client.self.getSurname() + " -]";
        stage.setTitle(Constants.windowTitle + extraTitle);
        stage.setScene(new Scene(root, Constants.windowWidth, Constants.windowHeight));
        stage.setResizable(Constants.isWindowResizable);
        stage.show();
    }

    public static void loadWindow(String path, Class<?> c) {
        try {
            setUpStage(new Stage(), FXMLLoader.load(c.getClass().getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getEmployeeAccessRights(boolean isAdmin) {
        return isAdmin ? Constants.Admin : Constants.User;
    }

    public static int getEmployeeAccessRightsId(boolean isAdmin) {
        return isAdmin ? 1 : 2;
    }

    public static void resetTextField(TextField textField) {
        textField.textProperty().setValue("");
    }

    public static void resetCheckBox(CheckBox checkBox) {
        checkBox.setSelected(false);
    }

    public static void createReport(String content, String name) {
        try {
            Date now = new Date();
            String filename = name + " - " + now.toString().replace(':', '-');

            File file = new File(filename);
            file.createNewFile();

            FileWriter myWriter = new FileWriter(file);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
