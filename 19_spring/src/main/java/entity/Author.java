package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "bio")
    private String bio;

    public Author() {
    }

    public Author(String firstName, String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Author{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", bio='" + bio + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstName, author.firstName) &&
            Objects.equals(lastName, author.lastName) &&
            Objects.equals(bio, author.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, bio);
    }
}
