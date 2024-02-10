package com.example.testjavafx;

import java.util.ArrayList;
import java.util.Date;

public class DataBase {
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Movie> movies = new ArrayList<>();
    static ArrayList<Review> reviews = new ArrayList<>();
    static ArrayList<User> banUsers = new ArrayList<>();
    static ArrayList<Edit> edits = new ArrayList<>();
    int id=1;

    public void initializeAPP() {
        // Create and add an admin user
        Admin admin = new Admin("admin", "admin", "mahyar", "mohammadian", 19, 1, Gender.MALE, null);
        users.add(admin);

        Editor editor = new Editor("editor", "editor", "Mahyar", "MHN", 19, 1, Gender.MALE, null);
        users.add(editor);

        Member member = new Member("member", "member", "Mahyar", "Mohammadian", 19, 1, Gender.MALE, null);
        users.add(member);
        Member member2 = new Member("member2", "member2", "Mahyar2", "Mohammadian2", 192, 12, Gender.MALE, null);
        users.add(member2);

        // Create movies
        createAndAddMovie( id++,"First Movie", Genre.ACTION, 5, "sampleTrailer1", "Summary 1", "posterURL1", "English");
        createAndAddMovie(id++,"Second Movie", Genre.COMEDY, 4, "sampleTrailer2", "Summary 2", "posterURL2", "English");
        createAndAddMovie( id++,"Third Movie", Genre.DRAMA, 4, "sampleTrailer3", "Summary 3", "posterURL3", "Spanish");
        createAndAddMovie(id++,"Fourth Movie", Genre.HORROR, 3, "sampleTrailer4", "Summary 4", "posterURL4", "English");
        createAndAddMovie(id++,"Fifth Movie", Genre.SCIENCE_FICTION, 4, "sampleTrailer5", "Summary 5", "posterURL5", "English");
        createAndAddMovie(id++,"Sixth Movie", Genre.ACTION, 4, "sampleTrailer6", "Summary 6", "posterURL6", "English");

    }

    private void createAndAddMovie(int id, String title, Genre genre, int rate, String trailer, String summary, String poster, String language) {
        // Create a Date object for the release date
        Date releaseDate = new Date(); // This will set the current date and time. You can adjust as needed.

        // Create an ArrayList for reviews, reports, and staff (if needed)
        ArrayList<Review> reviewsList = new ArrayList<>();
        ArrayList<Report> reportsList = new ArrayList<>();
        ArrayList<Staff> staffList = new ArrayList<>();


        // Create a new instance of the Movie class and add it to the movies list
        Movie movie = new Movie(id, title, genre, rate, 0.0, trailer, summary, poster, language, releaseDate, reviewsList, reportsList, staffList);
        movies.add(movie);
    }
}
