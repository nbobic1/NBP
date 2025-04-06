package ba.menit.nbp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MedicalRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Patient patient;

    private Long doctorId;

    private Long appointmentId;

    @Column(length = 255)
    private String diagnosis;

    @Column(length = 255)
    private String treatment;

    private LocalDateTime recordDate;
}
