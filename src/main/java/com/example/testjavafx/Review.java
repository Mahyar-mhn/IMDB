package com.example.testjavafx;

public class Review {
    private static int nextId = 1;
    private int id;
    private User reviewer;
    private Movie movie;
    private int rating; // Out of 5 or 10 stars
    private String comment;
    private boolean isSpoiler;
    private boolean isHelpful;
    private boolean isNotHelpful;

    public Review(User reviewer, Movie movie, int rating, String comment, boolean isSpoiler) {
        this.id = nextId++;
        this.reviewer = reviewer;
        this.movie = movie;
        this.rating = rating;
        this.comment = comment;
        this.isSpoiler = isSpoiler;
        this.isHelpful = false;
        this.isNotHelpful = false;
    }

    public int getId() {
        return id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public boolean isSpoiler() {
        return isSpoiler;
    }

    public boolean isHelpful() {
        return isHelpful;
    }

    public static void setNextId(int nextId) {
        Review.nextId = nextId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReviewer(Member reviewer) {
        this.reviewer = reviewer;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSpoiler(boolean spoiler) {
        isSpoiler = spoiler;
    }

    public void setHelpful(boolean helpful) {
        isHelpful = helpful;
    }

    public void setNotHelpful(boolean notHelpful) {
        isNotHelpful = notHelpful;
    }

    public boolean isNotHelpful() {
        return isNotHelpful;
    }

    public void markHelpful() {
        isHelpful = true;
        isNotHelpful = false;
    }

    public void markNotHelpful() {
        isHelpful = false;
        isNotHelpful = true;
    }
}

