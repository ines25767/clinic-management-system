 Clinic Management System – Spring Boot Project

 Project Overview
This project is a **Clinic Management System** developed as part of the **DS1 Web Development – Spring Boot** module.  
It aims to manage and organize essential clinic operations such as **patients, doctors, appointments, and invoices** using a RESTful backend architecture.

The project demonstrates complete CRUD functionality, entity relationships, and API testing with Postman.

---

 Objective
To design and implement a **Spring Boot REST API** that allows efficient management of clinic data, showcasing:
- Entity modeling and relationships
- CRUD operations
- Data validation and error handling
- Testing APIs using Postman

---

 Technologies Used
- **Spring Boot** (Backend framework)
- **Spring Data JPA** (ORM for database operations)
- **MySQL** (Relational database)
- **Lombok** (for cleaner entity code)
- **Postman** (for API testing)
- **Maven** (for dependency management)
- **Java 17+** (programming language)

---

 Project Structure
```
src/
 ├─ main/java/com/example/clinic/
 │   ├─ model/           # Entity classes (Patient, Doctor, Appointment, Invoice)
 │   ├─ repository/      # JpaRepository interfaces
 │   ├─ service/         # Business logic and validation
 │   ├─ controller/      # REST API endpoints
 │   └─ ClinicApplication.java  # Main Spring Boot application
 ├─ resources/
 │   ├─ application.properties  # Database configuration
 └─ test/
```

---

 Entities and Relationships

 1. **Patient**
- `id`, `name`, `age`, `gender`, `phoneNumber`
- A patient can have **multiple appointments**

 2. **Doctor**
- `id`, `name`, `specialization`, `email`
- A doctor can handle **multiple appointments**

 3. **Appointment**
- `id`, `date`, `time`, `status`
- Linked to one **Patient** and one **Doctor**

 4. **Invoice**
- `id`, `amount`, `dateIssued`
- Each invoice is linked to **one appointment**

**Relationships:**
- OneToMany → Doctor → Appointment  
- OneToMany → Patient → Appointment  
- OneToOne → Appointment → Invoice

---

 Features / Functionalities
✅ CRUD operations for **Patients**, **Doctors**, and **Appointments**  
✅ Automatic generation of **Invoices** for appointments  
✅ Validation of input data and error handling  
✅ RESTful APIs tested via **Postman**  

---

 How to Run the Project

 .Prerequisites
- Java 17 or higher
- Maven
- MySQL Database
- IDE (IntelliJ, STS, or VS Code)

. Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/clinic-management-system.git
   ```
2. Open the project in your IDE.
3. Configure your MySQL connection in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/clinicdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
4. Run the project with:
   ```bash
   mvn spring-boot:run
   ```
5. Test the endpoints in Postman.

---

. Postman Endpoints Examples

| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/patients` | Get all patients |
| POST | `/api/patients` | Add new patient |
| GET | `/api/patients/{id}` | Get patient by ID |
| PUT | `/api/patients/{id}` | Update patient |
| DELETE | `/api/patients/{id}` | Delete patient |
| GET | `/api/doctors` | Get all doctors |
| POST | `/api/appointments` | Create appointment |
| GET | `/api/invoices` | List invoices |

*(Include screenshots of successful Postman tests in your slides or GitHub repo.)*

---

. Collaborator
**Validator (Teacher):** [awadi.hatem@gmail.com](mailto:awadi.hatem@gmail.com)

---

. Conclusion
This project showcases the use of **Spring Boot** and **REST APIs** to develop a modular, testable, and scalable backend for healthcare operations management.

---

. License
This project is for academic purposes as part of the DS1 module at [Your Institution Name].
