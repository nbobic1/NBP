package ba.menit.nbp.controllers;


import ba.menit.nbp.dtos.DoctorDto;
import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.entities.DoctorStatistics;
import ba.menit.nbp.entities.Patient;
import ba.menit.nbp.services.DoctorService;
import ba.menit.nbp.services.DoctorStatisticsService;
import ba.menit.nbp.services.PdfExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private PdfExportService pdfExportService;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorStatisticsService statisticsService;

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.create(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> get(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/stats")
    public List<DoctorStatistics> getDoctorStats() {
        return statisticsService.getStatistics();
    }
    @GetMapping("/stats/pdf")
    public ResponseEntity<byte[]> exportStatsPdf() {
        List<Map<String, Object>> stats = doctorService.getStats();
        ByteArrayInputStream pdfStream = pdfExportService.exportDoctorStats(stats);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=doctor-stats.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.update(id, doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Doctor> getPatientsByUserId(@PathVariable Long userId) {
        Doctor doctor = doctorService.getByUserId(userId);

        return ResponseEntity.ok(doctor);
    }
}
