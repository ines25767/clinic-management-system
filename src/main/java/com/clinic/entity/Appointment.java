// src/main/java/com/clinic/entity/Appointment.java
package com.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dateTime;

    @NotBlank
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Status status = Status.SCHEDULED;

    public enum Status { SCHEDULED, COMPLETED, CANCELLED }

    @ManyToOne(optional = false) @JoinColumn(name = "patient_id")
    @com.fasterxml.jackson.annotation.JsonBackReference("patient-appointments")
    private Patient patient;

    @ManyToOne(optional = false) @JoinColumn(name = "doctor_id")
    @com.fasterxml.jackson.annotation.JsonBackReference("doctor-appointments")
    private Doctor doctor;


    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("appointment-prescriptions")
    private List<Prescription> prescriptions = new ArrayList<>();

    public void addPrescription(Prescription p) {
        prescriptions.add(p);
        p.setAppointment(this);
    }

    public void removePrescription(Prescription p) {
        prescriptions.remove(p);
        p.setAppointment(null);
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(List<Prescription> prescriptions) { this.prescriptions = prescriptions; }
}
