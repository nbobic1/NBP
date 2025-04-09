package ba.menit.nbp.services;


import ba.menit.nbp.entities.Patient;
import ba.menit.nbp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
