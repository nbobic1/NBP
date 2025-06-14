package ba.menit.nbp.controllers;


import ba.menit.nbp.dtos.PatientDto;
import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.entities.Patient;
import ba.menit.nbp.services.DoctorService;
import ba.menit.nbp.services.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.create(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> get(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secure")
    public ResponseEntity<String> secureData() {
        return ResponseEntity.ok("This is secured.");
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.update(id, patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-doctor/{userId}")
    public ResponseEntity<List<PatientDto>> getPatientsByDoctorId(@PathVariable Long userId) {
        Doctor doctor = doctorService.getByUserId(userId);
        List<PatientDto> patients = patientService.getPatientsByDoctorId(doctor.getId());
        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patients);
    }
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Patient> getPatientsByUserId(@PathVariable Long userId) {
        Patient patient = patientService.getByUserId(userId);

        return ResponseEntity.ok(patient);
    }
}