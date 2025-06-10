package ba.menit.nbp.repositories;

import ba.menit.nbp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends GenericRepository<Patient, Long>{
    Optional<Patient> findByUserId(Long userId);

}


