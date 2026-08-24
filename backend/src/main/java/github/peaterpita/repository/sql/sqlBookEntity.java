package github.peaterpita.repository.sql;

import java.util.UUID;

import javax.persistence.Column;

import github.peaterpita.model.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class sqlBookEntity {
    @Id
    private String id;

    @Column(unique = true)
    private String isbn;

    @Column
    private String title;

    @Column
    private String author;

    @Column(name = "copies_left")
    private Integer copiesLeft;

    public sqlBookEntity() {
    }

    public sqlBookEntity(Book book) {
        if (book.getId() != null) {
            this.id = book.getId();
        } else {
            this.id = UUID.randomUUID().toString();
        }
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.copiesLeft = book.getCopiesLeft();
    }

    public Book toBook() {
        Book book = new Book();

        book.setId(this.id);
        book.setIsbn(this.isbn);
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setCopiesLeft(this.copiesLeft);

        return book;
    }

    public String getId() {
        return id;
    }

}
