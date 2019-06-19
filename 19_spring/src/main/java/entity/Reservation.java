package entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "date")
    private Date date;
    @OneToOne(optional=false, cascade= CascadeType.DETACH)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;//TODO принятие взятой книги

    public Reservation() {
    }

    public Reservation(LocalDate date, Book book, User user) {
        this.date = Date.valueOf(date);
        this.book = book;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = Date.valueOf(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date=" + date +
                ", book=" + book +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id &&
                date.equals(that.date) &&
                book.equals(that.book) &&
                user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, book, user);
    }
}
