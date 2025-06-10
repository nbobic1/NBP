package ba.menit.nbp.controllers;


import ba.menit.nbp.dtos.MedicalRecordDto;
import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.entities.MedicalRecord;
import ba.menit.nbp.entities.Patient;
import ba.menit.nbp.services.DoctorService;
import ba.menit.nbp.services.MedicalRecordService;
import ba.menit.nbp.services.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<MedicalRecord> create(@RequestBody MedicalRecord record) {
        return ResponseEntity.ok(medicalRecordService.create(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAll() {
        return ResponseEntity.ok(medicalRecordService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> update(@PathVariable Long id, @RequestBody MedicalRecord record) {
        return ResponseEntity.ok(medicalRecordService.update(id, record));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicalRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<MedicalRecord>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(medicalRecordService.getByUserId(userId));
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getByPatientId(@PathVariable Long patientId) {
        Patient patient = patientService.getByUserId(patientId);
        return ResponseEntity.ok(medicalRecordService.getByPatientId(patient.getId()));
    }

    @Transactional
    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecordDto>> getRecordsByDoctor(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.getByUserId(doctorId);
        List<MedicalRecord> records = medicalRecordService.getByDoctorId(doctor.getId());
        List<MedicalRecordDto> result = records.stream().map(r ->
                new MedicalRecordDto(
                        r.getId(),
                        r.getPatient().getUser().getFirstName(),
                        r.getPatient().getUser().getLastName(),
                        r.getDiagnosis(),
                        r.getTreatment(),
                        r.getRecordDate()
                )
        ).toList();
        return ResponseEntity.ok(result);
    }
}
