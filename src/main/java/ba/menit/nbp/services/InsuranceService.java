package ba.menit.nbp.services;


import ba.menit.nbp.entities.Insurance;
import ba.menit.nbp.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    public Insurance create(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public Insurance getById(Long id) {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));
    }

    public List<Insurance> getAll() {
        return insuranceRepository.findAll();
    }

    public Insurance update(Long id, Insurance updated) {
        Insurance existing = getById(id);
        existing.setInsuranceName(updated.getInsuranceName());
        existing.setCoverageDetails(updated.getCoverageDetails());
        return insuranceRepository.save(existing);
    }

    public void delete(Long id) {
        insuranceRepository.deleteById(id);
    }
}
