package ba.menit.nbp.entities;

import jakarta.persistence.*;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String streetName;

    public Hospital() {
    }

    public Hospital(String name, String streetName) {
        this.name = name;
        this.streetName = streetName;
    }

    public Hospital(Long id, String name, String streetName) {
        this.id = id;
        this.name = name;
        this.streetName = streetName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
