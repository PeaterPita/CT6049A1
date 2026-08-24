package github.peaterpita.model;

public class Book {
    private String id;
    private String isbn;
    private String title;
    private String author;
    private Integer copiesLeft = 1;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getCopiesLeft() {
        return copiesLeft;
    }

    // ###########
    // # Setters #
    // ###########

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCopiesLeft(Integer copiesLeft) {
        this.copiesLeft = copiesLeft;
    }

}
