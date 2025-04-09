package ba.menit.nbp.services;


import ba.menit.nbp.entities.Appointment;
import ba.menit.nbp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment update(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = getById(id);
        existingAppointment.setStartTime(updatedAppointment.getStartTime());
        existingAppointment.setDoctor(updatedAppointment.getDoctor());
        return appointmentRepository.save(existingAppointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
    public List<Appointment> getByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}
