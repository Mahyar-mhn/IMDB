package com.example.testjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPage {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    // Initialize method for the FXML (if you choose to set the action here)
    @FXML
    public void initialize() {
        loginButton.setOnAction(actionEvent -> handleLogin());
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();



        for (User user : DataBase.users) {
            if (username.equals(user.username) && password.equals(user.password)) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
                    Parent adminPageParent = fxmlLoader.load();
                    AdminPage adminPageController = fxmlLoader.getController();
                    adminPageController.setAuthenticatedUser(user);  // Pass the authenticated user

                    Scene adminScene = new Scene(adminPageParent);

                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setScene(adminScene);
                    stage.setTitle("Admin Page");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
