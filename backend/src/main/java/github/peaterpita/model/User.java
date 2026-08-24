package github.peaterpita.model;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String username;
    private String password;
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // ###########
    // # Setters #
    // ###########

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime date) {
        this.createdAt = date;
    }

    public void setId(String id) {
        this.id = id;
    }

}
