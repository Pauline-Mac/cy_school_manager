package models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Professor extends User implements HibernateEntity {



    @OneToMany(mappedBy = "professor")
    private List<Course> courses;


    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private LocalDate updatedAt;

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
}