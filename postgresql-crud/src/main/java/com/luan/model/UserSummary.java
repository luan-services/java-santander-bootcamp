package com.luan.model;

/* represents the columns returned by the user_summary view */
public class UserSummary {
    private final Long id;
    private final String name;
    private final String email;
    private final String displayName;

    public UserSummary(Long id, String name, String email, String displayName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "UserSummary{id=" + id
                + ", name='" + name
                + "', email='" + email
                + "', displayName='" + displayName + "'}";
    }
}
