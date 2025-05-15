package ba.menit.nbp.entities;

import jakarta.persistence.*;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;
    private String dosage;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Medication() {
    }

    public Medication(String name, String description, String dosage) {
        this.name = name;
        this.description = description;
        this.dosage = dosage;
    }

    public Medication(Long id, String name, String description, String dosage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dosage = dosage;
    }
}