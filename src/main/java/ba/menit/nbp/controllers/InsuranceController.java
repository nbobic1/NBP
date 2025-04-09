package ba.menit.nbp.controllers;


import ba.menit.nbp.entities.Insurance;
import ba.menit.nbp.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping
    public ResponseEntity<Insurance> create(@RequestBody Insurance insurance) {
        return ResponseEntity.ok(insuranceService.create(insurance));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> get(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Insurance>> getAll() {
        return ResponseEntity.ok(insuranceService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insurance> update(@PathVariable Long id, @RequestBody Insurance insurance) {
        return ResponseEntity.ok(insuranceService.update(id, insurance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        insuranceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
