package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.sql.Date;

@Entity
public class RentedBook implements Serializable {
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
    @Column (name = "is_returned")
    private boolean isReturned;
    @Column (name = "date_taken")
    private Date dateTaken;
    @Column (name = "must_be_returned_date")
    private Date mustBeReturnedDate;

    public RentedBook() {
    }

    public RentedBook(User user, Book book, boolean isReturned, LocalDate dateTaken, LocalDate mustBeReturnedDate) {
        this.user = user;
        this.book = book;
        this.isReturned = isReturned;
        this.dateTaken = Date.valueOf(dateTaken);
        this.mustBeReturnedDate = Date.valueOf(mustBeReturnedDate);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
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


    public void setDateTaken(LocalDate dateTaken) {
        this.dateTaken = Date.valueOf(dateTaken);
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public Date getMustBeReturnedDate() {
        return mustBeReturnedDate;
    }

    public void setMustBeReturnedDate(Date mustBeReturnedDate) {
        this.mustBeReturnedDate = mustBeReturnedDate;
    }

    public void setMustBeReturnedDate(LocalDate mustBeReturnedDate) {
        this.mustBeReturnedDate = Date.valueOf(mustBeReturnedDate);
    }

    @Override
    public String toString() {
        return "RentedBook{" +
            "user=" + user +
            ", book=" + book +
            ", isReturned=" + isReturned +
            ", dateTaken=" + dateTaken +
            ", mustBeReturnedDate=" + mustBeReturnedDate +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentedBook that = (RentedBook) o;
        return isReturned == that.isReturned &&
            Objects.equals(user, that.user) &&
            Objects.equals(book, that.book) &&
            Objects.equals(dateTaken, that.dateTaken) &&
            Objects.equals(mustBeReturnedDate, that.mustBeReturnedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book, isReturned, dateTaken, mustBeReturnedDate);
    }
}
