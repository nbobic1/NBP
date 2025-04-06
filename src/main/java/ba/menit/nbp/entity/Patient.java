package ba.menit.nbp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(length = 20, unique = true)
    private String medicalRecordNumber;

    private Long insuranceId;
}