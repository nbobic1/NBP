package ba.menit.nbp.repositories;


import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.entities.MedicalRecord;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends GenericRepository<Doctor, Long> {
    Optional<Doctor> findByUserId(Long userId);
}
