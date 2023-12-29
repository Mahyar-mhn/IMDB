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
import java.lang.reflect.Type;

public class LoginPage {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private User loggedInUser;

    // Initialize method for the FXML (if you choose to set the action here)
    @FXML
    public void initialize() {
        loginButton.setOnAction(actionEvent -> handleLogin());
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        loggedInUser = authenticate(username, password);
        if (loggedInUser != null) {
            if (!loggedInUser.ban){
                if(loggedInUser instanceof Admin){
                    navigateToAdminPage();
                }
                else if(loggedInUser instanceof Editor){
                    navigateToEditorPage();
                } else if (loggedInUser instanceof Member) {
                    navigateToMemberPage();
                }
            }

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

            AdminPage adminPageController = fxmlLoader.getController(); // Assuming AdminPage has a reference to itself as its controller.
            adminPageController.setLoggedInUser(loggedInUser); // Set the logged-in user in the AdminPage
            adminPageController.setWelcomeLabel();
            Scene adminScene = new Scene(adminPageParent);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(adminScene);
            stage.setTitle("Admin Page");
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading the admin page: " + e.getMessage());
        }
    }
    private void navigateToEditorPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditorPage.fxml"));
            Parent editorPageParent = fxmlLoader.load();

            EditorPage editorPageController = fxmlLoader.getController();;
            editorPageController.setLoggedInUser(loggedInUser);
            Scene editorScene = new Scene(editorPageParent);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(editorScene);
            stage.setTitle("Editor Page");
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading the admin page: " + e.getMessage());
        }
    }

    private void navigateToMemberPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MemberPage.fxml"));
            Parent memberPageParent = fxmlLoader.load();

            MemberPage memberPageController = fxmlLoader.getController();
            memberPageController.setLoggedInUser(loggedInUser);
            memberPageController.setWelcomeLabel();
            Scene memberScene = new Scene(memberPageParent);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(memberScene);
            stage.setTitle("MemberPage");
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading the admin page: " + e.getMessage());
        }
    }
}
