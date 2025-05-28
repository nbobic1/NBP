package ba.menit.nbp.repositories;

import ba.menit.nbp.entities.DoctorStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface DoctorStatisticsRepository extends JpaRepository<DoctorStatistics, Long> {
    List<DoctorStatistics> findAll();
    @Procedure(name = "FillDoctorStatistics")
    void callFillDoctorStatistics();
}
