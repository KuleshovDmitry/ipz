package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public Review() {
    }

    public Review(User user, Book book, String text) {
        this.user = user;
        this.book = book;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    @Override
    public String toString() {
        return "Review{" +
            "user=" + user +
            ", book=" + book +
            ", text='" + text + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(user, review.user) &&
            Objects.equals(book, review.book) &&
            Objects.equals(text, review.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book, text);
    }
}
