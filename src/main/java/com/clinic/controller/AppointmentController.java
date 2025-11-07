// src/main/java/com/clinic/controller/AppointmentController.java
package com.clinic.controller;

import com.clinic.entity.Appointment;
import com.clinic.entity.Doctor;
import com.clinic.entity.Patient;
import com.clinic.service.AppointmentService;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.PatientRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public AppointmentController(AppointmentService service,
                                 DoctorRepository doctorRepo,
                                 PatientRepository patientRepo) {
        this.service = service;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    // DTOs
    public record CreateAppointmentReq(String dateTime, String reason, Long doctorId, Long patientId) {}
    public record UpdateAppointmentReq(String dateTime, String reason, Appointment.Status status,
                                       Long doctorId, Long patientId) {}

    // LIST (optionally filter by doctorId/patientId)
    @GetMapping
    public List<Appointment> list(@RequestParam(required = false) Long doctorId,
                                  @RequestParam(required = false) Long patientId) {
        if (doctorId != null) return service.findByDoctorId(doctorId);
        if (patientId != null) return service.findByPatientId(patientId);
        return service.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateAppointmentReq req) {
        Optional<Doctor> doc = doctorRepo.findById(req.doctorId());
        Optional<Patient> pat = patientRepo.findById(req.patientId());
        if (doc.isEmpty() || pat.isEmpty())
            return ResponseEntity.badRequest().body("Invalid doctorId or patientId");

        Appointment a = new Appointment();
        a.setDateTime(LocalDateTime.parse(req.dateTime()));   // "YYYY-MM-DDTHH:mm:ss"
        a.setReason(req.reason());
        a.setDoctor(doc.get());
        a.setPatient(pat.get());

        Appointment saved = service.save(a);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // UPDATE (full)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateAppointmentReq req) {
        return service.findById(id).map(existing -> {
            if (req.dateTime() != null) existing.setDateTime(LocalDateTime.parse(req.dateTime()));
            if (req.reason() != null)   existing.setReason(req.reason());
            if (req.status() != null)   existing.setStatus(req.status());

            if (req.doctorId() != null) {
                Doctor d = doctorRepo.findById(req.doctorId())
                        .orElseThrow(() -> new IllegalArgumentException("doctorId invalid"));
                existing.setDoctor(d);
            }
            if (req.patientId() != null) {
                Patient p = patientRepo.findById(req.patientId())
                        .orElseThrow(() -> new IllegalArgumentException("patientId invalid"));
                existing.setPatient(p);
            }
            return ResponseEntity.ok(service.save(existing));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
