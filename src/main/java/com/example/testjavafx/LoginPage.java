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

        User authenticatedUser = authenticate(username, password);
        if (authenticatedUser != null) {
            navigateToAdminPage();
        }
    }

    private User authenticate(String username, String password) {
        for (User user : DataBase.users) {
            if (username.equals(user.username) && password.equals(user.password)) {
                return user;
            }
        }
        return null;
    }

    private void navigateToAdminPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
            Parent adminPageParent = fxmlLoader.load();

            Scene adminScene = new Scene(adminPageParent);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(adminScene);
            stage.setTitle("Admin Page");
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading the admin page: " + e.getMessage());
        }
    }
}
