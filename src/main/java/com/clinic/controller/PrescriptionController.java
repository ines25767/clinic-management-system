// src/main/java/com/clinic/controller/PrescriptionController.java
package com.clinic.controller;

import com.clinic.entity.Appointment;
import com.clinic.entity.Prescription;
import com.clinic.repository.AppointmentRepository;
import com.clinic.service.PrescriptionService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;
    private final AppointmentRepository appointmentRepo;

    public PrescriptionController(PrescriptionService service, AppointmentRepository appointmentRepo) {
        this.service = service;
        this.appointmentRepo = appointmentRepo;
    }

    // DTOs
    public record CreateReq(String medicine, String dosage, Long appointmentId) {}
    public record UpdateReq(String medicine, String dosage, Long appointmentId) {}

    // LIST (optionally filter by appointmentId)
    @GetMapping
    public List<Prescription> list(@RequestParam(required = false) Long appointmentId) {
        if (appointmentId != null) return service.findByAppointmentId(appointmentId);
        return service.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateReq req) {
        Appointment appt = appointmentRepo.findById(req.appointmentId())
                .orElse(null);
        if (appt == null) return ResponseEntity.badRequest().body("Invalid appointmentId");

        Prescription p = new Prescription();
        p.setMedicine(req.medicine());
        p.setDosage(req.dosage());
        p.setAppointment(appt);

        Prescription saved = service.save(p);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateReq req) {
        return service.findById(id).map(existing -> {
            if (req.medicine() != null) existing.setMedicine(req.medicine());
            if (req.dosage() != null)   existing.setDosage(req.dosage());
            if (req.appointmentId() != null) {
                Appointment appt = appointmentRepo.findById(req.appointmentId())
                        .orElseThrow(() -> new IllegalArgumentException("appointmentId invalid"));
                existing.setAppointment(appt);
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
