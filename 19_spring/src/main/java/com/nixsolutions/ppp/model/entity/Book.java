package com.nixsolutions.ppp.model.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "year")
    private int year;
    @Column(name = "isbn", nullable = false)
    private int isbn;
    @Column(name = "available")
    private boolean available;
    @ManyToOne(optional=false, cascade= CascadeType.DETACH)
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(optional=false, cascade= CascadeType.DETACH)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Book() {
        available = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book(String name, int year, int isbn, Author author, Genre genre) {
        this.name = name;
        this.year = year;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        available = true;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", year=" + year +
            ", isbn=" + isbn +
            ", author=" + author +
            ", genre=" + genre +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
            year == book.year &&
            isbn == book.isbn &&
            Objects.equals(name, book.name) &&
            Objects.equals(author, book.author) &&
            Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, isbn, author, genre);
    }
}
