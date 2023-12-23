package com.example.testjavafx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Member extends User{
    int id;
    ArrayList<Review> reviews = new ArrayList<>();
    ArrayList<Report> reports = new ArrayList<>();
    private Map<String, List<Movie>> personalLists = new HashMap<>();
    ArrayList<Member> followedMembers = new ArrayList<>();
    ArrayList<Staff> followedStaff = new ArrayList<>();

    public Member(String username, String password, String firstName, String lastName, int age, int id, Gender gender, Address address) {
        super(username, password, firstName, lastName, age, id, gender, address);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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

    public Map<String, List<Movie>> getPersonalLists() {
        return personalLists;
    }

    public void setPersonalLists(Map<String, List<Movie>> personalLists) {
        this.personalLists = personalLists;
    }

    public ArrayList<Member> getFollowedMembers() {
        return followedMembers;
    }

    public void setFollowedMembers(Member followedMembers) {
        this.followedMembers.add(followedMembers);
    }

    public ArrayList<Staff> getFollowedStaff() {
        return followedStaff;
    }

    public void setFollowedStaff(Staff followedStaff) {
        this.followedStaff.add(followedStaff);
    }

    public void rateAndReviewMovie(Movie movie, int rating, String comment, boolean isSpoiler, boolean isHelpful) {
        Review review = new Review(this, movie, rating, comment, isSpoiler);
        reviews.add(review);

        if (isHelpful) {
            review.markHelpful();
        } else {
            review.markNotHelpful();
        }
    }

    public void addMovieToPersonalList(Movie movie, String listName) {
        switch (listName) {
            case "Watchlist":
            case "Favorites":
            case "ClassicsToSee":
                addToPersonalList(listName, movie);
                System.out.println("Movie added to your list successfully");
                break;
            default:
                System.out.println("Invalid list name");
        }
    }

    private void addToPersonalList(String listName, Movie movie) {
        personalLists.computeIfAbsent(listName, k -> new ArrayList<>()).add(movie);
    }

    public List<Movie> getPersonalList(String listName) {
        return personalLists.getOrDefault(listName, new ArrayList<>());
    }

    public void followMember(Member member){
        followedMembers.add(member);
    }
    public void followStaff(Staff staff){
        followedStaff.add(staff);
    }

    public void seeFollowedMemberLists(int memberID) {
        for (Member followedMember : followedMembers) {
            if (followedMember.getId() == memberID) {
                System.out.println("Member: " + followedMember.getUsername());

                // Watchlist
                List<Movie> watchlist = followedMember.getPersonalList("Watchlist");
                printMoviesWithRating("Watchlist", watchlist);

                // Favorites
                List<Movie> favorites = followedMember.getPersonalList("Favorites");
                printMoviesWithRating("Favorites", favorites);

                // ClassicsToSee
                List<Movie> classicsToSee = followedMember.getPersonalList("ClassicsToSee");
                printMoviesWithRating("ClassicsToSee", classicsToSee);
            }
        }
    }

    private void printMoviesWithRating(String listName, List<Movie> movies) {
        System.out.println(listName + ":");
        for (Movie movie : movies) {
            System.out.println("   " + movie.getTitle() + " - Rating: " + movie.getRate());
        }
    }

    public void reportContent(Review review, String reason) {
        Report report = new Report(this, review, reason);
        reports.add(report);
        System.out.println("Content reported successfully. Admins will review it.");
    }

}

