package com.clinic.service;

import com.clinic.entity.Doctor;
import com.clinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public List<Doctor> findAll() {
        return repo.findAll();
    }

    public Optional<Doctor> findById(Long id) {
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

    public Doctor save(Doctor d) {
        return repo.save(d);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
