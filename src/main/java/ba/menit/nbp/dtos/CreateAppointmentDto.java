package ba.menit.nbp.dtos;

import java.time.LocalDateTime;

public class CreateAppointmentDto {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime startTime;
    private Long serviceId;


    public CreateAppointmentDto() {}

    public CreateAppointmentDto(Long patientId, Long doctorId, LocalDateTime startTime, Long serviceId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.serviceId = serviceId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}