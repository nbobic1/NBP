package ba.menit.nbp.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment() {

    }

    public Appointment(LocalDateTime startTime, Doctor doctor) {
        this.startTime = startTime;
        this.doctor = doctor;
    }

    public Appointment(Long id, LocalDateTime startTime, Doctor doctor) {
        this.id = id;
        this.startTime = startTime;
        this.doctor = doctor;
    }
}