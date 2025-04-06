package ba.menit.nbp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Long hospitalId;

    @Column(length = 255)
    private String seniority;
}

