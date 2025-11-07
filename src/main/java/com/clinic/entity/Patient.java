package com.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;          // <-- add
import java.util.List;               // <-- add
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "patients", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String firstName;
    @NotBlank private String lastName;

    @Email @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{8,15}", message = "Phone must contain 8-15 digits")
    private String phone;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference("patient-appointments")
    private List<Appointment> appointments = new ArrayList<>();


    // getters & setters (generate or write by hand)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}
