package models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Professor extends User implements HibernateEntity {

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;


    public Professor() {
        super();
    }


    public Professor(String email, String password, String lastName, String firstName, LocalDate birthDate, String phone) {
        super(email, password, lastName, firstName, birthDate, phone, "PROFESSOR");
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.courses = new ArrayList<>();
    }

    // Getters et setters


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Course> getCourses() {
        return courses;
    }

}