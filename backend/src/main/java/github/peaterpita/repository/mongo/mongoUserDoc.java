package github.peaterpita.repository.mongo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import github.peaterpita.model.User;

@Document(collection = "users")
public class mongoUserDoc {
    @Id
    private String id;
    private String username;
    private String password;
    private LocalDateTime createdAt;

    public mongoUserDoc() {
    }

    public mongoUserDoc(User user) {
        this.id = user.getId();
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
}
