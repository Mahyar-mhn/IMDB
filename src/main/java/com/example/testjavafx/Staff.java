package com.example.testjavafx;

import java.util.ArrayList;

public class Staff extends User {
    int id;
    StaffRole staffRole;
    ArrayList<Movie> movies;
    ArrayList<Member> members;

    public Staff(String username, String password, String firstName, String lastName, int age, int id, Gender gender, Address address) {
        super(username, password, firstName, lastName, age, id, gender, address);
    }
}
