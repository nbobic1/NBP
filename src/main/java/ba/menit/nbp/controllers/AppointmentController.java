package ba.menit.nbp.controllers;


import ba.menit.nbp.dtos.AppointmentDto;
import ba.menit.nbp.dtos.AppointmentSummaryDto;
import ba.menit.nbp.dtos.CreateAppointmentDto;
import ba.menit.nbp.entities.Appointment;
import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.entities.Patient;
import ba.menit.nbp.services.AppointmentService;
import ba.menit.nbp.services.DoctorService;
import ba.menit.nbp.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    AppointmentController(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping
//    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDto appointmentdto) {
//        Appointment appointment = new Appointment();
//
//        appointment.setStartTime(appointmentdto.getStartTime());
//        appointment.setDoctor(doctorService.getAll().get(0));
//        appointment.setPatient(patientService.getAll().get(0));
//        return ResponseEntity.ok(appointmentService.create(appointment));
//    }

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody CreateAppointmentDto createDto) {
        try {
            AppointmentDto newAppointment = appointmentService.createAppointment(createDto);
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED); // Return 201 Created
        } catch (Exception e) {
            // Handle cases where doctor or patient might not be found
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Or HttpStatus.NOT_FOUND if specific
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.update(id, appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/by-doctor/{doctorUserId}")
    public ResponseEntity<List<AppointmentSummaryDto>> getAppointmentsByDoctorUserId(@PathVariable Long doctorUserId) {
        Doctor doctor = doctorService.getByUserId(doctorUserId);
        List<Appointment> appointments = appointmentService.getByDoctorId(doctor.getId());

        List<AppointmentSummaryDto> result = appointments.stream()
                .map(app -> new AppointmentSummaryDto(
                        app.getId(),
                        app.getStartTime(),
                        app.getPatient().getUser().getFirstName(),
                        app.getPatient().getUser().getLastName(),
                        app.getService().getName(),
                        app.getService().getPrice()
                ))
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-doctor/doctor/{doctorUserId}")
    public ResponseEntity<List<AppointmentSummaryDto>> getAppointmentsByDoctorId(@PathVariable Long doctorUserId) {
        List<Appointment> appointments = appointmentService.getByDoctorId(doctorUserId);

        List<AppointmentSummaryDto> result = appointments.stream()
                .map(app -> new AppointmentSummaryDto(
                        app.getId(),
                        app.getStartTime(),
                        app.getPatient().getUser().getFirstName(),
                        app.getPatient().getUser().getLastName(),
                        app.getService().getName(),
                        app.getService().getPrice()
                ))
                .toList();

        return ResponseEntity.ok(result);
    }
    @GetMapping("/by-patient/{userId}")
    public ResponseEntity<List<AppointmentSummaryDto>> getAppointmentsByPatientUserId(@PathVariable Long userId) {
        Patient patient = patientService.getByUserId(userId);
        List<Appointment> appointments = appointmentService.getByPatientId(patient.getId());

        List<AppointmentSummaryDto> result = appointments.stream()
                .map(app -> new AppointmentSummaryDto(
                        app.getId(),
                        app.getStartTime(),
                        app.getDoctor().getUser().getFirstName(),
                        app.getDoctor().getUser().getLastName(),
                        app.getService().getName(),
                        app.getService().getPrice()
                ))
                .toList();

        return ResponseEntity.ok(result);
    }
}
