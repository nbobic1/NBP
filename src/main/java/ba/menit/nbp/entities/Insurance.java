package ba.menit.nbp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String insuranceName;
    private String coverageDetails;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getCoverageDetails() {
        return coverageDetails;
    }

    public void setCoverageDetails(String coverageDetails) {
        this.coverageDetails = coverageDetails;
    }


    public Insurance() {
    }

    public Insurance(String insuranceName, String coverageDetails) {
        this.insuranceName = insuranceName;
        this.coverageDetails = coverageDetails;
    }

    public Insurance(Long id, String insuranceName, String coverageDetails) {
        this.id = id;
        this.insuranceName = insuranceName;
        this.coverageDetails = coverageDetails;
    }
}