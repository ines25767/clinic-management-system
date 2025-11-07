// src/main/java/com/clinic/service/AppointmentService.java
package com.clinic.service;

import com.clinic.entity.Appointment;
import com.clinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;

    public AppointmentService(AppointmentRepository repo) { this.repo = repo; }

    public Appointment save(Appointment a) { return repo.save(a); }
    public Optional<Appointment> findById(Long id) { return repo.findById(id); }
    public List<Appointment> findAll() { return repo.findAll(); }
    public List<Appointment> findByPatientId(Long patientId) { return repo.findByPatient_Id(patientId); }
    public List<Appointment> findByDoctorId(Long doctorId) { return repo.findByDoctor_Id(doctorId); }
    public void deleteById(Long id) { repo.deleteById(id); }
}
