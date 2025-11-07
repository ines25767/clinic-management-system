package com.clinic.service;

import com.clinic.entity.Patient;
import com.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public List<Patient> findAll() {
        return repo.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return repo.findById(id);
    }

    public boolean emailExists(String email) {
        return repo.existsByEmail(email);
    }

    public boolean emailExistsForAnother(String email, Long id) {
        return repo.existsByEmailAndIdNot(email, id);
    }

    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    public Patient save(Patient p) {
        return repo.save(p);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
