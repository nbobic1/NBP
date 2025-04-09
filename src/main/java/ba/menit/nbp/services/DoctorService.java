package ba.menit.nbp.services;


import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
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
}
