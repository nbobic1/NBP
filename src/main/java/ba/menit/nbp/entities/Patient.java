package ba.menit.nbp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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
    @JsonIgnore // Crucial to prevent infinite recursion in JSON serialization
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true) // 'patient' is the field in Appointment entity
    private Set<Appointment> appointments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Patient() {
    }
    public Patient(User user) {
    this.user   =user;
    }


    public Patient(User user, String medicalRecordNumber, Long insuranceId) {
        this.user = user;
        this.medicalRecordNumber = medicalRecordNumber;
        this.insuranceId = insuranceId;
    }

    public Patient(Long id, User user, String medicalRecordNumber, Long insuranceId) {
        this.id = id;
        this.user = user;
        this.medicalRecordNumber = medicalRecordNumber;
        this.insuranceId = insuranceId;
    }
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}