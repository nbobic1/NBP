package ba.menit.nbp.entities;

import ba.menit.nbp.dtos.DoctorStatsDto;
import jakarta.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "Doctor.getStatistics",
        procedureName = "GET_DOCTOR_STATISTICS",
        resultClasses = DoctorStatsDto.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = void.class)
        }
)
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

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public Doctor() {
    }


    public Doctor(User user, Long hospitalId, String seniority) {
        this.user = user;
        this.hospitalId = hospitalId;
        this.seniority = seniority;
    }

    public Doctor(Long id, User user, Long hospitalId, String seniority) {
        this.id = id;
        this.user = user;
        this.hospitalId = hospitalId;
        this.seniority = seniority;
    }
}

