package ba.menit.nbp.repositories;

import ba.menit.nbp.entities.Appointment;
import java.util.List;

public interface AppointmentRepository extends GenericRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
}