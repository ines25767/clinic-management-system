Clinic Management API (Spring Boot + REST + MySQL)

A complete Clinic Management API built using Spring Boot, implementing CRUD operations for:

✔ Patients
✔ Doctors
✔ Appointments
✔ Prescriptions

With relationships between entities, REST endpoints, and JSON responses.

📌 1. Project Overview

This RESTful API allows a clinic to manage appointments, patients, doctors and prescriptions.

 Features:

Add, update, delete, list Patients & Doctors

Schedule and manage Appointments

Add/Update/Delete Prescriptions for an Appointment

Handles relationships:

One Patient → Many Appointments

One Doctor → Many Appointments

One Appointment → Many Prescriptions

Uses Spring JPA, Hibernate, MySQL for persistence

API tested using Postman

 2. Technologies Used
Technology	Description
Java 17	Programming Language
Spring Boot 3	Backend Framework
Spring Data JPA	ORM & Database interaction
MySQL	Database
Hibernate	JPA Implementation
Lombok (optional)	Reduce boilerplate code
Postman	API Testing
Maven	Dependency Management
🗂️ 3. ER Diagram (Conceptual)
Patient (1) --------- (∞) Appointment (∞) --------- (1) Doctor
                              |
                            (∞)
                       Prescription

📦 4. Entities & Relationships
Entity	Main Fields	Relationship
Patient	id, firstName, lastName, email, phone	One-to-Many with Appointments
Doctor	id, firstName, lastName, email, specialty	One-to-Many with Appointments
Appointment	id, dateTime, reason, status(SCHEDULED...)	Many-to-One (Patient & Doctor), One-to-Many Prescriptions
Prescription	id, medicine, dosage	Many-to-One with Appointment
 5. API Endpoints
 Patients API
Method	Endpoint	Description
GET	/api/patients	Get all patients
GET	/api/patients/{id}	Get patient by ID
POST	/api/patients	Add new patient
PUT	/api/patients/{id}	Update patient
DELETE	/api/patients/{id}	Delete patient
 Doctors API
Method	Endpoint	Description
GET	/api/doctors	Get all doctors
POST	/api/doctors	Add new doctor
 Appointments API
Method	Endpoint	Description
GET	/api/appointments	List all appointments
POST	/api/appointments	Create appointment
GET	/api/appointments/{id}	Get appointment details
PUT	/api/appointments/{id}	Update appointment
DELETE	/api/appointments/{id}	Cancel/Delete appointment
 Prescriptions API
Method	Endpoint	Description
GET	/api/prescriptions?appointmentId=1	List prescriptions for appointment
POST	/api/prescriptions?appointmentId=1	Add prescription to appointment
PUT	/api/prescriptions/{id}	Update prescription
DELETE	/api/prescriptions/{id}	Delete prescription
 6. How to Run the Project
 Step 1: Clone the Repository
git clone https://github.com/inesjemour/clinic-management.git
cd clinic-management

 Step 2: Update application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinic_db
spring.datasource.username=root
spring.datasource.password=pwd
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

 Step 3: Run MySQL Server
 Step 4: Run the Application
mvn spring-boot:run


or in Eclipse → Right-click ClinicApplication.java → Run As → Spring Boot App

 7. Testing with Postman

Example POST request to create an appointment:

POST http://localhost:8081/api/appointments
{
  "dateTime": "2025-11-10T10:30:00",
  "reason": "General Checkup",
  "doctorId": 1,
  "patientId": 1
}

 8. GitHub Submission Instructions

Initialize the Git repository in your project:

git init
git add .
git commit -m "Initial commit - Clinic API"

Add GitHub remote:

git remote add origin https://github.com/inesjemour/clinic-management.git
git push -u origin main


Add your teacher as collaborator:

Go to GitHub → Repository → Settings → Collaborators

Add: awadi.hatem@gmail.com

 9. Author

ines jemour
Student | Full-Stack Developer
