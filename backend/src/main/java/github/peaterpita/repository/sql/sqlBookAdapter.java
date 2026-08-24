package github.peaterpita.repository.sql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import github.peaterpita.model.Book;
import github.peaterpita.repository.BookRepository;

@Repository
@Profile("sql")
public class sqlBookAdapter implements BookRepository {

    private final jpaBookRepo jpaRepo;

    public sqlBookAdapter(jpaBookRepo jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Book save(Book book) {
        sqlBookEntity entity = new sqlBookEntity(book);
        return jpaRepo.save(entity).toBook();
    }

    @Override
    public List<Book> findAll() {
        return jpaRepo.findAll().stream().map(sqlBookEntity::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(String id) {
        return jpaRepo.findById(id).map(sqlBookEntity::toBook);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return jpaRepo.findByTitleContainingIgnoreCase(title)
                .stream().map(sqlBookEntity::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return jpaRepo.findByAuthorContainingIgnoreCase(author)
                .stream().map(sqlBookEntity::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByCopiesLeftGreaterThan(int copies) {
        return jpaRepo.findByCopiesLeftGreaterThan(copies)
                .stream().map(sqlBookEntity::toBook)
                .collect(Collectors.toList());
    }

}
