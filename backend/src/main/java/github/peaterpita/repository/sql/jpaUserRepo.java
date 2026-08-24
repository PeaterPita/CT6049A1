package github.peaterpita.repository.sql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaUserRepo extends JpaRepository<sqlUserEntity, String> {
    Optional<sqlUserEntity> findByUsername(String username);
}
