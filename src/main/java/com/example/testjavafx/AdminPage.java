package com.example.testjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class AdminPage {

    @FXML
    private Label welcomeLabel;

    @FXML
    private ListView<Movie> moviesListView;

    @FXML
    private ListView<User> usersListView;

    @FXML
    private TextField movieNameTextField;

    @FXML
    private TextField movieIdTextField; // To enter the ID of the movie to be edited

    @FXML
    private TextField updatedMovieNameTextField; // To enter the updated movie name

    @FXML
    private TextField updatedMovieDetailsTextField; // You can add more fields as needed
    @FXML
    private TextField userNameTextField;

    @FXML
    private VBox movieControlsVBox;

    @FXML
    private VBox userControlsVBox;

    private User authenticatedUser;

    public void setAuthenticatedUser(User user) {
        this.authenticatedUser = user;
    }

    // This method will be called after the FXML file is loaded.
    public void initialize() {
        if (authenticatedUser != null) {
            welcomeLabel.setText("Welcome, " + authenticatedUser.getFirstName());
        } else {
            welcomeLabel.setText("Welcome!!");
        }

        // Populate moviesListView with existing movies
        moviesListView.getItems().addAll(DataBase.movies);

        // Populate usersListView with existing users
        usersListView.getItems().addAll(DataBase.users);
    }

    @FXML
    private void addMovie() {
        String movieName = movieNameTextField.getText().trim();
        if (!movieName.isEmpty()) {
            Movie newMovie = new Movie(); // Assuming a default constructor or another way to create a movie
            newMovie.setTitle(movieName);
            // You can add more properties to the newMovie as required
            DataBase.movies.add(newMovie);
            moviesListView.getItems().add(newMovie);
            movieNameTextField.clear();
        }
    }

    @FXML
    private void editMovie() {
        try {
            int movieId = Integer.parseInt(movieIdTextField.getText().trim()); // Assuming you have a movieIdTextField for input
            String updatedMovieName = updatedMovieNameTextField.getText().trim(); // Assuming you have a updatedMovieNameTextField for input

            Optional<Movie> optionalMovieToEdit = DataBase.movies.stream()  // Assuming DataBase.movies is a list of Movie objects
                    .filter(m -> m.getId() == movieId)  // Assuming Movie objects have an id field
                    .findFirst();

            if (optionalMovieToEdit.isPresent()) {
                Movie movieToEdit = optionalMovieToEdit.get();
                movieToEdit.setTitle(updatedMovieName);
                // Update other properties of the movie as needed
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
        Movie selectedMovie = moviesListView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Movie");
            alert.setContentText("Are you sure you want to delete this movie?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DataBase.movies.remove(selectedMovie);
                moviesListView.getItems().remove(selectedMovie);
            }
        }
    }

    @FXML
    private void addUser() {
        String userName = userNameTextField.getText().trim();
        if (!userName.isEmpty()) {
            User newUser = new User(); // Assuming a constructor with just username
            DataBase.users.add(newUser);
            usersListView.getItems().add(newUser);
            userNameTextField.clear();
        }
    }

    @FXML
    private void deleteUser() {
        User selectedUser = usersListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete User");
            alert.setContentText("Are you sure you want to delete this user?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DataBase.users.remove(selectedUser);
                usersListView.getItems().remove(selectedUser);
            }
        }
    }

    @FXML
    private void editUser() {
        // Placeholder for editing a user
        // This might involve opening a new dialog or updating in-place depending on your requirements
    }

    // Other admin-specific methods or event handlers can be added here.
}
