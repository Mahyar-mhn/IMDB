package com.example.testjavafx;

public class Edit {
    private static int nextId = 1; // for generating unique edit IDs
    private int id;
    private int movieId;
    private User editor;
    private String editedField;
    private String oldValue;
    private String newValue;
    private boolean isApproved; // Indicates whether the edit is approved by admin

    public Edit(User editor, int movieId, String editedField, String oldValue, String newValue) {
        this.id = nextId++;
        this.editor = editor;
        this.movieId = movieId;
        this.editedField = editedField;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.isApproved = false; // Initial state: not approved
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public User getEditor() {
        return editor;
    }

    public String getEditedField() {
        return editedField;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String MovieName(int id){
        for (Movie movie:DataBase.movies) {
            if (movie.id == id){
                return movie.title;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Edit{" +
                "id=" + id +
                ", editor=" + editor.firstName +
                ", movie=" + MovieName(movieId) +
                ", editedField='" + editedField + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }
}

