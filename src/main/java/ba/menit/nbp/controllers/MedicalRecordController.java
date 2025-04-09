package ba.menit.nbp.controllers;


import ba.menit.nbp.entities.MedicalRecord;
import ba.menit.nbp.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

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
        return ResponseEntity.ok(medicalRecordService.getByPatientId(patientId));
    }
}
