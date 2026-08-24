package github.peaterpita.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import github.peaterpita.model.Book;
import github.peaterpita.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book findById(String id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Error finding book"));
    }

    public List<Book> searchByTitle(String title) {
        return bookRepo.findByTitle(title);
    }

    public List<Book> getAvailable() {
        return bookRepo.findByCopiesLeftGreaterThan(0);
    }

    public List<Book> searchBooks(String query, Boolean available) {
        List<Book> results = bookRepo.findAll();

        if (query != null && !query.isBlank()) {
            String lowerQuery = query.toLowerCase();
            results = results.stream()
                    .filter(b -> b.getTitle()
                            .toLowerCase()
                            .contains(lowerQuery) ||
                            b.getAuthor()
                                    .toLowerCase()
                                    .contains(lowerQuery))
                    .collect(Collectors.toList());
        }

        if (Boolean.TRUE.equals(available)) {
            results = results.stream()
                    .filter(b -> b.getCopiesLeft() != null
                            && b.getCopiesLeft() > 0)
                    .collect(Collectors.toList());
        }

        return results;
    }

}
