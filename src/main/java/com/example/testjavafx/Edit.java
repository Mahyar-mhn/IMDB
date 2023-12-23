package com.example.testjavafx;

public class Edit {
    private static int nextId = 1; // for generating unique edit IDs
    private int id;
    private User editor;
    private String editedField;
    private String oldValue;
    private String newValue;
    private boolean isApproved; // Indicates whether the edit is approved by admin

    public Edit(User editor, String editedField, String oldValue, String newValue) {
        this.id = nextId++;
        this.editor = editor;
        this.editedField = editedField;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.isApproved = false; // Initial state: not approved
    }

    public int getId() {
        return id;
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
}

