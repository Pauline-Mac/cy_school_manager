package org.openjfx.schoolmanager;

import jakarta.persistence.*;



@Entity
@Table(name = "students") // Nom de la table dans la base de données
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment pour l'ID
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_naissance")
    private String dateNaissance; // Ou utiliser LocalDate

    @Column(name = "contact")
    private String contact;

    // Constructeurs
    public Student() {}

    public Student(String nom, String prenom, String dateNaissance, String contact) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getContact() {
        return contact;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
