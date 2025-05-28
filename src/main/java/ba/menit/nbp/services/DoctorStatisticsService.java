package ba.menit.nbp.services;

import ba.menit.nbp.entities.DoctorStatistics;
import ba.menit.nbp.repositories.DoctorStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorStatisticsService {

    @Autowired
    private DoctorStatisticsRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DoctorStatistics> getStatistics() {
        jdbcTemplate.execute("BEGIN NBP06.FILL_DOCTOR_STATISTICS; END;");

        return repository.findAll();
    }
}
