package com.example.testjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

public class LoginPage {
    @FXML
    private AnchorPane LoginPane;
    @FXML
    private AnchorPane signUpPane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    @FXML
    private Button signUp;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField age;
    @FXML
    private ChoiceBox<Gender> gender = new ChoiceBox<>();

    private User loggedInUser;

    // Initialize method for the FXML (if you choose to set the action here)
    @FXML
    public void initialize() {
        LoginPane.setVisible(true);
        signUpPane.setVisible(false);
        gender.getItems().addAll(Gender.values());
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
                } else {
                    navigateToMemberPage();
                }
            }

        }
    }
    @FXML
    private void handleSignUp(){
        if(LoginPane.isVisible()){
            LoginPane.setVisible(false);
            signUpPane.setVisible(true);
            signUp.setText("Login");

        }
        else{
            LoginPane.setVisible(true);
            signUpPane.setVisible(false);
            signUp.setText("SignUp");
        }
        User user = new User(username.getText(), password.getText(), firstname.getText(), lastname.getText(), Integer.parseInt(age.getText()), 7, gender.getValue(),null);
        DataBase.users.add(user);

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
            editorPageController.setWelcomeLabel();
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
