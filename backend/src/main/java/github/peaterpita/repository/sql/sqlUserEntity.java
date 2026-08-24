package github.peaterpita.repository.sql;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;

import github.peaterpita.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class sqlUserEntity {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private LocalDateTime createdAt;

    public sqlUserEntity() {
    };

    public sqlUserEntity(User user) {
        if (user.getId() != null) {
            this.id = user.getId();
        } else {
            this.id = UUID.randomUUID().toString();
        }

        this.username = user.getUsername();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
    }

    public User toUser() {
        User user = new User();

        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);

        user.setCreatedAt(this.createdAt);

        return user;
    }

    public String getId() {
        return id;
    }

}
