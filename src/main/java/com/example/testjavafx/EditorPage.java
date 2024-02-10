package com.example.testjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EditorPage {

    @FXML
    private ListView<Movie> moviesListView;

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField partToEdit;

    @FXML
    private TextField oldValue;

    @FXML
    private TextField newValue;

    private User loggedInUser;

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }
    public void setWelcomeLabel(){
        welcomeLabel.setText(loggedInUser.firstName);
    }
    @FXML
    private void initialize() {
        refreshLists();
    }


    private static class ColoredListCell<T> extends ListCell<T> {
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setStyle("-fx-background-color:  #B6C4B6;");
            } else {
                setText(item.toString());
                setTextFill(Color.valueOf("#163020"));
                setStyle("-fx-background-color:  #B6C4B6;");
            }
        }
    }
    @FXML
    private void refreshLists() {
        moviesListView.getItems().clear();
        moviesListView.getItems().addAll(DataBase.movies);

        moviesListView.setCellFactory(list -> new EditorPage.ColoredListCell<>());
    }

    @FXML
    private void editMovieRequest() {
        Movie selectedMovie = moviesListView.getSelectionModel().getSelectedItem();
        String editorUsername = loggedInUser.username;
        String partOfMovie = partToEdit.getText().trim();
        String oldVal = oldValue.getText().trim();
        String newVal = newValue.getText().trim();

        User editor = findEditor(editorUsername);

        if (editor == null) {
            // Handle error, e.g., show a message to the user.
            return;
        }

        Edit edit = new Edit(editor, selectedMovie.id, partOfMovie, oldVal, newVal);
        DataBase.edits.add(edit);
        refreshLists();
    }

    private User findEditor(String editorUsername) {
        for (User user : DataBase.users) {
            if (user instanceof Editor && Objects.equals(user.username, editorUsername)) {
                return user;
            }
        }
        return null;
    }

    @FXML
    private void handleSignOutButtonClick(ActionEvent event) {
        // Access the Stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            // Load LoginPage.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent loginPageParent = fxmlLoader.load();

            // Create a new Scene with the login page and set it to the Stage
            Scene loginScene = new Scene(loginPageParent);
            currentStage.setScene(loginScene);
            currentStage.setTitle("Login Page");
            currentStage.show();

        } catch (IOException e) {
            System.err.println("Error loading the login page: " + e.getMessage());
        }
    }
}
