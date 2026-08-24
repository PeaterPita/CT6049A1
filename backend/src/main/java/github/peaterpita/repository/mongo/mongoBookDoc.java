package github.peaterpita.repository.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import github.peaterpita.model.Book;

@Document(collection = "books")
public class mongoBookDoc {
    @Id
    private String id;

    private String isbn;
    private String title;
    private String author;

    @Field("copies_left")
    private Integer copiesLeft;

    public mongoBookDoc() {
    }

    public mongoBookDoc(Book book) {
        this.id = book.getId();
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
}
