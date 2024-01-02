package com.example.testjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MemberPage {
    @FXML
    private ListView<Movie> AllMovies;
    @FXML
    private ListView<User> Followers;
    @FXML
    private ListView<Movie> RecommendMovies;
    @FXML
    private ListView<User> SearchList;
    @FXML
    private ListView<Movie> MovieSearchList;
    @FXML
    private ListView<Movie> FavoritesMovies;
    @FXML
    private TextField SearchBar;
    @FXML
    private TextField MovieSearchBar;
    @FXML
    private TextField Rate;
    @FXML
    private Label welcomeLabel;
    @FXML
    private AnchorPane Profile;
    @FXML
    private AnchorPane Dashboard;
    @FXML
    private AnchorPane Search;
    @FXML
    private AnchorPane Lists;


    private User loggedInUser;
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void setWelcomeLabel() {
        welcomeLabel.setText(loggedInUser.firstName);
    }

    public void initialize() {
        Dashboard.setVisible(true);
        Profile.setVisible(true);
        hideAllSearchPanes();
        hideAllListsPanes();
        populateLists();
    }
    private void hideAllSearchPanes() {
        Search.setVisible(false);
    }
    private void hideAllListsPanes() {
        Lists.setVisible(false);
    }
    private void populateLists() {
        AllMovies.getItems().addAll(DataBase.movies);

        if (loggedInUser instanceof Member) {
            Followers.getItems().addAll(((Member) loggedInUser).followedMembers);
            FavoritesMovies.getItems().addAll(((Member) loggedInUser).favoriteMovies);
            RecommendMovies.getItems().addAll(((Member) loggedInUser).recommendMovies());
        }


        AllMovies.setCellFactory(list -> new MemberPage.ColoredListCell<>());
        Followers.setCellFactory(list -> new MemberPage.ColoredListCell<>());
        FavoritesMovies.setCellFactory(list -> new MemberPage.ColoredListCell<>());
        SearchList.setCellFactory(list -> new MemberPage.ColoredListCell<>());
        MovieSearchList.setCellFactory(list -> new MemberPage.ColoredListCell<>());
        RecommendMovies.setCellFactory(list -> new MemberPage.ColoredListCell<>());

    }

    private static class ColoredListCell<T> extends ListCell<T> {
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setStyle("-fx-background-color:  #7f1a0f;");
            } else {
                setText(item.toString());
                setTextFill(Color.valueOf("#fff4ed"));
                setStyle("-fx-background-color:  #7f1a0f;");

            }
        }
    }

    @FXML
    private void handleDashboardButtonClick() {
        Dashboard.setVisible(true);
        hideAllSearchPanes();
        hideAllListsPanes();
        if(((Member)loggedInUser).recommendMovies != null){
            RecommendMovies.getItems().addAll(((Member) loggedInUser).recommendMovies());
        }

    }
    @FXML
    private void handleSearchButtonClick() {
        Dashboard.setVisible(false);
        Search.setVisible(true);
        hideAllListsPanes();

    }

    @FXML
    private void handleListsButtonClick() {
        Dashboard.setVisible(false);
        Lists.setVisible(true);
        hideAllSearchPanes();
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

    public void SearchPerson(){
        SearchList.getItems().clear();
        String name = SearchBar.getText();
        for (User user:DataBase.users) {
            if (Objects.equals(user.firstName, name)){
                SearchList.getItems().add(user);
            }
        }
        SearchList.refresh();
    }

    public void SearchMovie(){
        MovieSearchList.getItems().clear();
        String movieName = MovieSearchBar.getText();
        for (Movie movie:DataBase.movies) {
            if(Objects.equals(movie.title, movieName)){
                MovieSearchList.getItems().add(movie);
            }
        }
        MovieSearchList.refresh();
    }

    @FXML
    private void handleFollowButtonClick() {
        User selectedUser = SearchList.getSelectionModel().getSelectedItem();
        if (selectedUser != null && !(Followers.getItems().contains(selectedUser))){
            Followers.getItems().add(selectedUser);
        }
        Followers.refresh();

    }
    @FXML
    private void handleAddToFavoriteListButtonClick() {
        FavoritesMovies.getItems().clear();
        Movie selectedMovie = MovieSearchList.getSelectionModel().getSelectedItem();
        if (selectedMovie != null && !(((Member)loggedInUser).favoriteMovies.contains(selectedMovie))){
            ((Member)loggedInUser).favoriteMovies.add(selectedMovie);
        }
        FavoritesMovies.getItems().addAll(((Member)loggedInUser).favoriteMovies);
        FavoritesMovies.refresh();
    }

    @FXML
    private void handleRateButtonClick() {
        Movie selectedMovie = MovieSearchList.getSelectionModel().getSelectedItem();
        if (selectedMovie != null){
            int rate = Integer.parseInt(Rate.getText().trim());
            Review review = new Review(loggedInUser, selectedMovie, rate,null,false);
            selectedMovie.reviews.add(review);
            AllMovies.refresh();
            RecommendMovies.refresh();
            MovieSearchList.refresh();
            FavoritesMovies.refresh();
        }

    }

}
