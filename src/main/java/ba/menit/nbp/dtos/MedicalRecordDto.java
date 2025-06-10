package ba.menit.nbp.dtos;

import java.time.LocalDateTime;

public class MedicalRecordDto {
    private Long id;
    private String patientFirstName;
    private String patientLastName;
    private String diagnosis;
    private String treatment;
    private LocalDateTime recordDate;

    public MedicalRecordDto(Long id, String patientFirstName, String patientLastName, String diagnosis, String treatment, LocalDateTime recordDate) {
        this.id = id;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.recordDate = recordDate;
    }


    public Long getId() {
        return id;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }
}