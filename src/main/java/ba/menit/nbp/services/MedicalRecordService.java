package ba.menit.nbp.services;


import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.entities.MedicalRecord;
import ba.menit.nbp.repositories.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord create(MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }

    public MedicalRecord getById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found"));
    }

    public List<MedicalRecord> getAll() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord update(Long id, MedicalRecord updated) {
        MedicalRecord existing = getById(id);
        existing.setPatient(updated.getPatient());
        existing.setDoctorId(updated.getDoctorId());
        existing.setAppointmentId(updated.getAppointmentId());
        existing.setDiagnosis(updated.getDiagnosis());
        existing.setTreatment(updated.getTreatment());
        existing.setRecordDate(updated.getRecordDate());
        return medicalRecordRepository.save(existing);
    }

    public void delete(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    public List<MedicalRecord> getByUserId(Long userId) {
        return medicalRecordRepository.findByPatientUserId(userId);
    }
    public List<MedicalRecord> getByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    public List<MedicalRecord> getByDoctorId(Long userId) {
        return medicalRecordRepository.findByDoctorId(userId);
    }
}
