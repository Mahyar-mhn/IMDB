package com.example.testjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Optional;

public class AdminPage {

    @FXML
    private ListView<Movie> moviesListView;
    @FXML
    private ListView<Movie> moviesListView2;

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
    private Button Movies;
    @FXML
    private Button DashBoard;

    @FXML
    private Button ADDMovie;

    @FXML
    private Button DeleteMovie;

    @FXML
    private Button EditMovie;
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


    public void initialize() {
        DashBoardPane.setVisible(true);
        profile.setVisible(true);
        hideAllMoviePanes();
        populateLists();
    }

    private void hideAllMoviePanes() {
        moviesPane.setVisible(false);
        moviesPane1.setVisible(false);
        moviesPane2.setVisible(false);
    }

    private void populateLists() {
        moviesListView.getItems().addAll(DataBase.movies);
        moviesListView2.getItems().addAll(DataBase.movies);
        usersListView.getItems().addAll(DataBase.users);
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
    private void handleMoviesButtonClick(ActionEvent event) {
        DashBoardPane.setVisible(false);
        hideAllMoviePanes();
        moviesPane.setVisible(true);
        moviesPane1.setVisible(true);
        moviesPane2.setVisible(true);
    }

    @FXML
    private void handleDashboardButtonClick(ActionEvent event) {
        DashBoardPane.setVisible(true);
        hideAllMoviePanes();
    }

    @FXML
    private void addMovie() {
        String movieName = movieNameTextField.getText().trim();
        if (!movieName.isEmpty()) {
            Movie newMovie = new Movie();
            DataBase.movies.add(newMovie);
            refreshMovieLists();
        }
    }

    private void refreshMovieLists() {
        moviesListView2.getItems().setAll(DataBase.movies);
        moviesListView.getItems().setAll(DataBase.movies);
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
            DataBase.users.add(newUser);
            usersListView.getItems().add(newUser);
        }
    }

    @FXML
    private void deleteUser() {
        User selectedUser = usersListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null && showDeleteConfirmation("Delete User")) {
            DataBase.users.remove(selectedUser);
            usersListView.getItems().remove(selectedUser);
        }
    }
}
