package github.peaterpita.repository.mongo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import github.peaterpita.model.Book;
import github.peaterpita.repository.BookRepository;

@Repository
@Profile("mongo")
public class mongoBookAdapter implements BookRepository {
    private final mongoBookRepo mongoRepo;

    public mongoBookAdapter(mongoBookRepo mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public Book save(Book book) {
        mongoBookDoc document = new mongoBookDoc(book);
        return mongoRepo.save(document).toBook();
    }

    @Override
    public List<Book> findAll() {
        return mongoRepo.findAll().stream()
                .map(mongoBookDoc::toBook).collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(String id) {
        return mongoRepo.findById(id).map(mongoBookDoc::toBook);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return mongoRepo.findByTitleContainingIgnoreCase(title).stream()
                .map(mongoBookDoc::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return mongoRepo.findByAuthorContainingIgnoreCase(author).stream()
                .map(mongoBookDoc::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByCopiesLeftGreaterThan(int copies) {
        return mongoRepo.findByCopiesLeftGreaterThan(copies).stream()
                .map(mongoBookDoc::toBook)
                .collect(Collectors.toList());
    }

}
