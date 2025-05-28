package ba.menit.nbp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DOCTOR_STATISTICS", schema = "NBP06")
@NamedStoredProcedureQuery(
        name = "FillDoctorStatistics",
        procedureName = "NBP06.FILL_DOCTOR_STATISTICS"
)
public class DoctorStatistics {
    @Id
    @Column(name = "DOCTOR_USER_ID")
    private Long doctorUserId;

    @Column(name = "DOCTOR_NAME")
    private String doctorName;

    @Column(name = "TOTAL_PATIENTS")
    private Integer totalPatients;

    @Column(name = "TOTAL_PAYMENTS")
    private Double totalPayments;

    // Getters and Setters
}
