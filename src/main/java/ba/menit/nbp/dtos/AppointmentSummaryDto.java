package ba.menit.nbp.dtos;

import java.time.LocalDateTime;

public class AppointmentSummaryDto {
    private Long id;
    private LocalDateTime startTime;
    private String patientFirstName;
    private String patientLastName;
    private String serviceName;
    private Double servicePrice;

    public AppointmentSummaryDto(Long id, LocalDateTime startTime, String patientFirstName, String patientLastName, String serviceName, Double servicePrice) {
        this.id = id;
        this.startTime = startTime;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public Long getId() { return id; }
    public LocalDateTime getStartTime() { return startTime; }
    public String getPatientFirstName() { return patientFirstName; }
    public String getPatientLastName() { return patientLastName; }

    public String getServiceName() {
        return serviceName;
    }

    public Double getServicePrice() {
        return servicePrice;
    }
}