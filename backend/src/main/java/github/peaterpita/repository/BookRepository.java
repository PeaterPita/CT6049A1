
package github.peaterpita.repository;

import java.util.List;
import java.util.Optional;

import github.peaterpita.model.Book;

public interface BookRepository {

    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(String id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByCopiesLeftGreaterThan(int copies);

}
