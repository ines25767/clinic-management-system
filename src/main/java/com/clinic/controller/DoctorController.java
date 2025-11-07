package com.clinic.controller;

import com.clinic.entity.Doctor;
import com.clinic.repository.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository repo;

    public DoctorController(DoctorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Doctor> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return repo.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Doctor d) {
        if (repo.existsByEmail(d.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Doctor d) {
        if (repo.existsByEmailAndIdNot(d.getEmail(), id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        return repo.findById(id).map(existing -> {
            existing.setFirstName(d.getFirstName());
            existing.setLastName(d.getLastName());
            existing.setSpecialty(d.getSpecialty());
            existing.setEmail(d.getEmail());
            return ResponseEntity.ok(repo.save(existing));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
