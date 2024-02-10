package com.example.testjavafx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Editor extends User {
    private List<Edit> edits = new ArrayList<>();

    public Editor(String username, String password, String firstName, String lastName, int age, int id, Gender gender, Address address) {
        super(username, password, firstName, lastName, age, id, gender, address);
    }

    public List<Edit> getEditSuggestions() {
        return edits;
    }

//    public void suggestEdit(String editedField, String oldValue, String newValue) {
//        Edit edit = new Edit(this, editedField, oldValue, newValue);
//        edits.add(edit);
//        System.out.println("Edit suggestion submitted successfully");
//    }

    public void addReleaseDate(Movie movie, Date releaseDate) {
        movie.setRealeaseDate(releaseDate);
        System.out.println("Release date added successfully");
    }

    public void addFullStaff(Movie movie, ArrayList<Staff> staff){
        movie.setStaff(staff);
        System.out.println("Staff added successfully");
    }
}

