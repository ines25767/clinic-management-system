package com.clinic.repository;

import com.clinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // traverse the association: appointment.doctor.id and appointment.patient.id
    List<Appointment> findByDoctor_Id(Long doctorId);

    List<Appointment> findByPatient_Id(Long patientId);
}
