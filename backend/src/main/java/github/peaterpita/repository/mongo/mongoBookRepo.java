package github.peaterpita.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface mongoBookRepo extends MongoRepository<mongoBookDoc, String> {
    List<mongoBookDoc> findByTitle(String title);

    List<mongoBookDoc> findByTitleContainingIgnoreCase(String title);

    List<mongoBookDoc> findByAuthorContainingIgnoreCase(String author);

    List<mongoBookDoc> findByCopiesLeftGreaterThan(int copies);
}
