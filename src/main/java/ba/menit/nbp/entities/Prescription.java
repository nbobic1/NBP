package ba.menit.nbp.entities;

import jakarta.persistence.*;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medical_record_id", nullable = false)
    private MedicalRecord medicalRecord;

    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    private Integer quantity;
    private String instructions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Prescription() {
    }

    public Prescription(MedicalRecord medicalRecord, Medication medication, Integer quantity, String instructions) {
        this.medicalRecord = medicalRecord;
        this.medication = medication;
        this.quantity = quantity;
        this.instructions = instructions;
    }

    public Prescription(Long id, MedicalRecord medicalRecord, Medication medication, Integer quantity, String instructions) {
        this.id = id;
        this.medicalRecord = medicalRecord;
        this.medication = medication;
        this.quantity = quantity;
        this.instructions = instructions;
    }
}