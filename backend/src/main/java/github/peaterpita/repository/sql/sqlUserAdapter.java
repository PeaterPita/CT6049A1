package github.peaterpita.repository.sql;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import github.peaterpita.model.User;
import github.peaterpita.repository.UserRepository;

@Repository
@Profile("sql")
public class sqlUserAdapter implements UserRepository {
    private final jpaUserRepo userRepo;

    public sqlUserAdapter(jpaUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(User user) {
        sqlUserEntity entity = new sqlUserEntity(user);
        return userRepo.save(entity).toUser();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username).map(sqlUserEntity::toUser);
    }

}
