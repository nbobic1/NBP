package ba.menit.nbp.dtos;

import java.time.LocalDateTime;

public class AppointmentDto{

    private Long id;
    private LocalDateTime startTime;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String serviceName;
    private Double servicePrice;
    public AppointmentDto() {

    }

    public AppointmentDto(Long id, LocalDateTime startTime, String patientFirstName, String patientLastName, String patientEmail, String serviceName, Double servicePrice) {
        this.id = id;
        this.startTime = startTime;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public void setStartTime(LocalDateTime localDateTime){
        this.startTime = localDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }
}