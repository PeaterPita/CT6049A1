package github.peaterpita.repository.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface mongoUserRepo extends MongoRepository<mongoUserDoc, String> {
    Optional<mongoUserDoc> findByUsername(String username);
}
