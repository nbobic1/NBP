package ba.menit.nbp.services;


import ba.menit.nbp.dtos.PatientDto;
import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.entities.Patient;
import ba.menit.nbp.repositories.AppointmentRepository;
import ba.menit.nbp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient update(Long id, Patient updated) {
        Patient existing = getById(id);
        existing.setUser(updated.getUser());
        existing.setMedicalRecordNumber(updated.getMedicalRecordNumber());
        existing.setInsuranceId(updated.getInsuranceId());
        return patientRepository.save(existing);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
    public Patient getByUserId(Long userId) {
        return patientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Pacijent nije pronađen za userId: " + userId));
    }

    @Autowired // Inject AppointmentRepository
    private AppointmentRepository appointmentRepository;

    public List<PatientDto> getPatientsByDoctorId(Long doctorId) {
        // Use the new method from AppointmentRepository
        List<Patient> patients = appointmentRepository.findDistinctPatientsByDoctorId(doctorId);
        return patients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PatientDto convertToDto(Patient patient) {
        PatientDto dto = new PatientDto();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getUser().getFirstName());
        dto.setLastName(patient.getUser().getLastName());
        dto.setEmail(patient.getUser().getEmail());
        dto.setUserId(patient.getUser().getId());
        return dto;
    }
}
