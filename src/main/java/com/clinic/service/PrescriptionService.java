package com.clinic.service;

import com.clinic.entity.Prescription;
import com.clinic.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    private final PrescriptionRepository repo;

    public PrescriptionService(PrescriptionRepository repo) { this.repo = repo; }

    public Prescription save(Prescription p) { return repo.save(p); }
    public Optional<Prescription> findById(Long id) { return repo.findById(id); }
    public List<Prescription> findAll() { return repo.findAll(); }
    public List<Prescription> findByAppointmentId(Long apptId) { return repo.findByAppointment_Id(apptId); }
    public void deleteById(Long id) { repo.deleteById(id); }
}
