package com.example.testjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminPage {

    @FXML
    private ListView<Movie> moviesListView;
    @FXML
    private ListView<Movie> moviesListView2;

    @FXML
    private ListView<User> usersListView;
    @FXML
    private ListView<User> usersListView2;


    @FXML
    private TextField movieNameTextField;

    @FXML
    private TextField movieIdTextField; // To enter the ID of the movie to be edited

    @FXML
    private TextField updatedMovieNameTextField; // To enter the updated movie name
    @FXML
    private TextField userIdTextField; // To enter the ID of the movie to be edited

    @FXML
    private TextField updatedUserNameTextField; // To enter the updated movie name

    @FXML
    private TextField userNameTextField;
    @FXML
    private AnchorPane profile;
    @FXML
    private AnchorPane DashBoardPane;
    @FXML
    private AnchorPane moviesPane;
    @FXML
    private AnchorPane moviesPane1;
    @FXML
    private AnchorPane moviesPane2;
    @FXML
    private AnchorPane UserPane;
    @FXML
    private AnchorPane UserPane1;
    @FXML
    private AnchorPane UserPane2;


    public void initialize() {
        DashBoardPane.setVisible(true);
        profile.setVisible(true);
        hideAllMoviePanes();
        hideAllUserPanes();
        populateLists();
    }

    private void hideAllMoviePanes() {
        moviesPane.setVisible(false);
        moviesPane1.setVisible(false);
        moviesPane2.setVisible(false);
    }
    private void hideAllUserPanes() {
        UserPane.setVisible(false);
        UserPane1.setVisible(false);
        UserPane2.setVisible(false);
    }

    private void populateLists() {
        moviesListView.getItems().addAll(DataBase.movies);
        moviesListView2.getItems().addAll(DataBase.movies);
        usersListView.getItems().addAll(DataBase.users);
        usersListView2.getItems().addAll(DataBase.users);
        moviesListView.setCellFactory(list -> new ColoredListCell<>());
        usersListView.setCellFactory(list -> new ColoredListCell<>());
    }

    private static class ColoredListCell<T> extends ListCell<T> {
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setStyle("-fx-background-color:  #1e2022;");
            } else {
                setText(item.toString());
                setTextFill(Color.valueOf("#f0f5f9"));
                setStyle("-fx-background-color:  #1e2022;");
            }
        }
    }

    @FXML
    private void handleMoviesButtonClick() {
        DashBoardPane.setVisible(false);
        hideAllMoviePanes();
        hideAllUserPanes();
        moviesPane.setVisible(true);
        moviesPane1.setVisible(true);
        moviesPane2.setVisible(true);
    }
    @FXML
    private void handleUsersButtonClick() {
        DashBoardPane.setVisible(false);
        hideAllMoviePanes();
        moviesPane.setVisible(false);
        moviesPane1.setVisible(false);
        moviesPane2.setVisible(false);
        UserPane.setVisible(true);
        UserPane1.setVisible(true);
        UserPane2.setVisible(true);
    }

    @FXML
    private void handleDashboardButtonClick() {
        DashBoardPane.setVisible(true);
        hideAllMoviePanes();
        hideAllUserPanes();
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


    @FXML
    private void addMovie() {
        String movieName = movieNameTextField.getText().trim();
        if (!movieName.isEmpty()) {
            Movie newMovie = new Movie();
            newMovie.setTitle(movieName);
            DataBase.movies.add(newMovie);
            refreshMovieLists();
        }
    }

    private void refreshMovieLists() {
        moviesListView2.getItems().setAll(DataBase.movies);
        moviesListView.getItems().setAll(DataBase.movies);
    }
    private void refreshUserLists() {
        usersListView.getItems().setAll(DataBase.users);
        usersListView2.getItems().setAll(DataBase.users);
    }

    @FXML
    private void editMovie() {
        try {
            int movieId = Integer.parseInt(movieIdTextField.getText().trim());
            String updatedMovieName = updatedMovieNameTextField.getText().trim();
            Optional<Movie> optionalMovieToEdit = DataBase.movies.stream()
                    .filter(m -> m.getId() == movieId)
                    .findFirst();

            if (optionalMovieToEdit.isPresent()) {
                optionalMovieToEdit.get().setTitle(updatedMovieName);
                refreshMovieLists();
                System.out.println("Movie edited successfully");
            } else {
                System.out.println("Movie not found!!!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid movie ID format!");
        }
    }

    @FXML
    private void deleteMovie() {
        Movie selectedMovie = moviesListView2.getSelectionModel().getSelectedItem();
        if (selectedMovie != null && showDeleteConfirmation("Delete Movie")) {
            DataBase.movies.remove(selectedMovie);
            refreshMovieLists();
        }
    }

    private boolean showDeleteConfirmation(String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(header);
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
    private void addUser() {
        String userName = userNameTextField.getText().trim();
        if (!userName.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(userName);
            DataBase.users.add(newUser);
        }
        refreshUserLists();
    }

    @FXML
    private void deleteUser() {
        User selectedUser = usersListView2.getSelectionModel().getSelectedItem();
        if (selectedUser != null && showDeleteConfirmation("Delete User")) {
            DataBase.users.remove(selectedUser);
            refreshUserLists();
        }
    }
    @FXML
    private void editUser(){
        try {
            int userid = Integer.parseInt(userIdTextField.getText().trim());
            String updatedUserName = updatedUserNameTextField.getText().trim();
            Optional<User> optionalUserToEdit = DataBase.users.stream()
                    .filter(m -> m.getId() == userid)
                    .findFirst();

            if (optionalUserToEdit.isPresent()) {
                optionalUserToEdit.get().setUsername(updatedUserName);
                refreshUserLists();
                System.out.println("User edited successfully");
            } else {
                System.out.println("User not found!!!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid user ID format!");
        }
    }

    @FXML
    private void banUser() {
        User selectedUser = usersListView2.getSelectionModel().getSelectedItem();
        if (selectedUser != null && showDeleteConfirmation("Ban User")) {
            DataBase.users.remove(selectedUser);
            refreshUserLists();
            System.out.println("User " + selectedUser.getUsername() + " has been banned.");
        }
    }



}
