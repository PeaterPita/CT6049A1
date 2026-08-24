package github.peaterpita.repository.mongo;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import github.peaterpita.model.User;
import github.peaterpita.repository.UserRepository;

@Repository
@Profile("mongo")
public class mongoUserAdapter implements UserRepository {

    private final mongoUserRepo userRepo;

    public mongoUserAdapter(mongoUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(User user) {
        mongoUserDoc document = new mongoUserDoc(user);
        return userRepo.save(document).toUser();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username).map(mongoUserDoc::toUser);
    }
}
