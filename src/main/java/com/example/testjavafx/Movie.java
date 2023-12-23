package com.example.testjavafx;

import java.util.ArrayList;
import java.util.Date;

public class Movie {
    int id;
    String title;
    Genre genre;
    int rate;
    double averageRating;
    String trailer;
    String summary;
    String Poster;
    String language;
    Date realeaseDate;
    ArrayList<Review> reviews = new ArrayList<>();
    ArrayList<Report> reports = new ArrayList<>();
    ArrayList<Staff> staff = new ArrayList<>();

    public Movie(int id, String title, Genre genre, int rate, double averageRating, String trailer, String summary, String poster, String language, Date realeaseDate, ArrayList<Review> reviews, ArrayList<Report> reports, ArrayList<Staff> staff) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rate = rate;
        this.averageRating = averageRating;
        this.trailer = trailer;
        this.summary = summary;
        Poster = poster;
        this.language = language;
        this.realeaseDate = realeaseDate;
        this.reviews = reviews;
        this.reports = reports;
        this.staff = staff;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public Date getRealeaseDate() {
        return realeaseDate;
    }

    public void setRealeaseDate(Date realeaseDate) {
        this.realeaseDate = realeaseDate;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    private void calculateAverageRating() {
        if (!reviews.isEmpty()) {
            double totalRating = 0;
            for (Review review : reviews) {
                totalRating += review.getRating();
            }
            averageRating = totalRating / reviews.size();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Movie ID: ").append(id).append(" ");
        stringBuilder.append("Title: ").append(title).append(" ");
        stringBuilder.append("Genre: ").append(genre).append(" ");
        stringBuilder.append("Rate: ").append(rate).append(" ");
        stringBuilder.append("Average Rating: ").append(averageRating).append(" ");
        stringBuilder.append("Trailer: ").append(trailer).append(" ");
        stringBuilder.append("Summary: ").append(summary).append(" ");
        stringBuilder.append("Poster: ").append(Poster).append(" ");
        stringBuilder.append("Language: ").append(language).append(" ");
        stringBuilder.append("Release Date: ").append(realeaseDate).append(" ");
        stringBuilder.append("Reviews: ").append(reviews).append(" ");
        stringBuilder.append("Reports: ").append(reports).append(" ");
        stringBuilder.append("Staff: ").append(staff).append("\n");

        return stringBuilder.toString();
    }

}

