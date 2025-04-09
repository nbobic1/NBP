package ba.menit.nbp.repositories;


import ba.menit.nbp.entities.MedicalRecord;

import java.util.List;

public interface MedicalRecordRepository extends GenericRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientUserId(Long userId);
    List<MedicalRecord> findByPatientId(Long patientId);
}