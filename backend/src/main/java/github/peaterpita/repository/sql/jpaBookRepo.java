package github.peaterpita.repository.sql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaBookRepo extends JpaRepository<sqlBookEntity, String> {

    List<sqlBookEntity> findByTitleContainingIgnoreCase(String title);

    List<sqlBookEntity> findByAuthorContainingIgnoreCase(String author);

    List<sqlBookEntity> findByCopiesLeftGreaterThan(int copies);

    List<sqlBookEntity> findByTitle(String title);
}
