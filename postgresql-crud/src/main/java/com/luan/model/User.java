package com.luan.model;

public class User {
    /* Long allows null before PostgreSQL generates the id */
    private Long id;
    private String name;
    private String email;

    /* this constructor is used before inserting a new user */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /* this constructor is used when reading an existing user from the database */
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /* toString provides a readable representation for examples and debugging */
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
