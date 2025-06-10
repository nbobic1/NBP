package ba.menit.nbp.services;


import ba.menit.nbp.dtos.DoctorDto;
import ba.menit.nbp.dtos.DoctorStatsDto;
import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.repositories.DoctorRepository;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;




    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor getById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DoctorDto convertToDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getUser() != null ? doctor.getUser().getFirstName() : "N/A",
                doctor.getUser() != null ? doctor.getUser().getLastName() : "N/A",
                doctor.getUser() != null ? doctor.getUser().getEmail() : "N/A"
        );
    }

    public Doctor getByUserId(Long userId) {
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Doktor nije pronađen za userId: " + userId));
    }
    public Doctor update(Long id, Doctor updated) {
        Doctor existing = getById(id);
        existing.setUser(updated.getUser());
//        existing.setHospital(updated.getHospital());
        existing.setSeniority(updated.getSeniority());
        return doctorRepository.save(existing);
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getStats() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_DOCTOR_STATISTICS")
                .declareParameters(
                        new SqlOutParameter("p_cursor", OracleTypes.CURSOR, new ColumnMapRowMapper())
                )
                .withoutProcedureColumnMetaDataAccess();

        Map<String, Object> result = jdbcCall.execute(new HashMap<>());

        return (List<Map<String, Object>>) result.get("p_cursor");
    }
}
