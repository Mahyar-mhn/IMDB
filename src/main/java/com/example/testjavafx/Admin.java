package com.example.testjavafx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Admin extends User{

    SiteConfiguration siteConfiguration = new SiteConfiguration();



    public Admin(String username, String password, String firstName,
                 String lastName, int age, int id, Gender gender, Address address) {
        super(username, password, firstName, lastName, age, id, gender, address);
    }

    public ArrayList<Movie> getMovies() {
        return DataBase.movies;
    }

    public ArrayList<Review> getReviews() {
        return DataBase.reviews;
    }

    public ArrayList<User> getUsers() {
        return DataBase.users;
    }

    public void addMovie(Movie movie){
        DataBase.movies.add(movie);
        System.out.println("Movie added successfully");
    }
    public void deleteMovie(int movieID) {
        Iterator<Movie> iterator = DataBase.movies.iterator();
        while (iterator.hasNext()) {
            Movie m = iterator.next();
            if (m.id == movieID) {
                iterator.remove();
                System.out.println("Movie removed successfully");
                break;
            }
        }
        System.out.println("Movie not found!!!");
    }
    public void editMovie(Movie updatedMovie) {
        for (int i = 0; i < DataBase.movies.size(); i++) {
            Movie movie = DataBase.movies.get(i);
            if (movie.getId() == updatedMovie.getId()) {
                DataBase.movies.set(i, updatedMovie);
                System.out.println("Movie edited successfully");
                return;
            }
        }
        System.out.println("Movie not found!!!");
    }
    public void addUser(User user){
        if(user instanceof Admin){
            Admin adminUser = (Admin) user;
            DataBase.users.add(adminUser);
        } else if (user instanceof Member) {
            Member memberUser = (Member) user;
            DataBase.users.add(memberUser);
        } else if (user instanceof Editor) {
            Editor editorUser = (Editor) user;
            DataBase.users.add(editorUser);
        }
    }
    public void deleteUser(int userID) {
        Iterator<User> iterator = DataBase.users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == userID) {
                iterator.remove();
                System.out.println("User removed successfully");
                return;
            }
        }
        System.out.println("User not found!!!");
    }
    public void editUser(User updatedUser) {
        for (int i = 0; i < DataBase.users.size(); i++) {
            User user = DataBase.users.get(i);
            if (user.getId() == updatedUser.getId()) {
                DataBase.users.set(i, updatedUser);
                System.out.println("User edited successfully");
                return;
            }
        }
        System.out.println("User not found!!!");
    }
    public void banUser(int userID) {
        for (User user : DataBase.users) {
            if (user.getId() == userID) {
                user.ban = true;
                System.out.println("User banned successfully");
                return;
            }
        }
        System.out.println("User not found!!!");
    }
    public void addReview(Review review) {
        DataBase.reviews.add(review);
        System.out.println("Review added successfully");
    }

    public void deleteReview(int reviewId) {
        Iterator<Review> iterator = DataBase.reviews.iterator();
        while (iterator.hasNext()) {
            Review review = iterator.next();
            if (review.getId() == reviewId) {
                iterator.remove();
                System.out.println("Review removed successfully");
                return;
            }
        }
        System.out.println("Review not found!!!");
    }

    public void editReview(int reviewId, String newComment, int newRating) {
        for (Review review : DataBase.reviews) {
            if (review.getId() == reviewId) {
                review.setComment(newComment);
                review.setRating(newRating);
                System.out.println("Review edited successfully");
                return;
            }
        }
        System.out.println("Review not found!!!");
    }
    public void reviewEditsFromEditor(List<Edit> editList) {
        for (Edit edit : editList) {
            if (edit.isApproved()) {
                applyApprovedEdit(edit);
                System.out.println("Edit approved successfully");
            } else {
                System.out.println("Edit rejected");
            }
        }
    }

    private void applyApprovedEdit(Edit edit) {
        // Apply the approved edit to your data (e.g., update movie details, etc.)
    }

    public void setConfigurationOption(String optionName, String optionValue) {
        siteConfiguration.setOption(optionName, optionValue);
        System.out.println("Configuration option set successfully");
    }

    public User findUser(String username, String password) {
        for (User user : DataBase.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("User found:");
                System.out.println(user);
                return user;
            }
        }
        System.out.println("User not found with the provided username and password.");
        return null;
    }

    public Movie findMovieById(int movieId) {
        for (Movie movie : DataBase.movies) {
            if (movie.getId() == movieId) {
                return movie;
            }
        }
        System.out.println("Movie not found with the provided ID: " + movieId);
        return null;
    }


    public Review findReviewById(int reviewId) {
        for (Review review : DataBase.reviews) {
            if (review.getId() == reviewId) {
                return review;
            }
        }
        System.out.println("Review not found with the provided ID.");
        return null;
    }


    public int showAdminMenu(){
        System.out.println("Admin Menu:");
        System.out.println("1. Add Movie");
        System.out.println("2. Delete Movie");
        System.out.println("3. Edit Movie");
        System.out.println("4. Add User");
        System.out.println("5. Delete User");
        System.out.println("6. Edit User");
        System.out.println("7. Ban User");
        System.out.println("8. Add Review");
        System.out.println("9. Delete Review");
        System.out.println("10. Edit Review");
        System.out.println("11. Review Edits from Editor");
        System.out.println("12. Set Configuration Option");
        System.out.println("13. Find User");
        System.out.println("14. All movies");
        System.out.println("15. See All reviews");
        System.out.println("16. See All users");
        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        return scanner.nextInt();

    }



}

