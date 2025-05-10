# 🏥 Doctor Appointment Booking System

A full-featured Doctor Appointment Booking System where patients can book appointments with doctors, and the system manages all data using relational tables.

---

## 📅 Daily Progress - 2025-05-10

### ✅ Today’s Completed Tasks:

1. ✅ **Spring Security Configuration Completed**
   - Full JWT-based authentication and authorization setup
   - Role-based access for Admin, Doctor, Patient, and Lab Panels
   - Custom login filters and authentication manager configured

2. ✅ **Admin Panel API Developed**
   - Manage users (doctor/patient)
   - Manage roles and permissions
   - View all appointments, tests, and system status

3. ✅ **Doctor Panel API Developed**
   - View doctor profile and appointments
   - Manage appointment status (approve/reject)
   - View patient details

4. ✅ **Lab Test Panel API Developed**
   - List and manage lab tests
   - Manage test bookings
   - Assign reports to patients

5. ✅ **Appointment Booking Panel API Developed**
   - Patients can book appointments
   - View available doctors by specialization
   - Manage appointment history

---

## 🚀 Features

- 🧑‍⚕️ Doctor Registration & Profile Management
- 🧑‍💼 Patient Registration & Login
- 📅 Appointment Booking & Scheduling
- 🧪 Medical Test Booking
- 📌 Role-Based Access (Admin, Doctor, Patient, Lab)
- 🔐 Secure Authentication with JWT
- 🧩 Full Relational Database Design

---

## 🛠 Tech Stack

- **Backend:** Spring Boot 3.4.5
- **Security:** Spring Security, JWT
- **Frontend:** Thymeleaf (Server-side rendering)
- **Database:** MySQL
- **Build Tool:** Gradle
- **JDK:** 21

---

## 🗃️ Database Design (Tables & Relationships)

Here are some core entities and their relationships:

- `Patient` ⟶ One-to-Many ⟶ `Appointment`
- `Doctor` ⟶ One-to-Many ⟶ `Appointment`
- `Appointment` ⟶ Many-to-One ⟶ `Doctor`, `Patient`
- `TestBooking` ⟶ Many-to-One ⟶ `Patient`
- `Role` ⟶ One-to-Many ⟶ `User`
- `User` ⟶ Many-to-One ⟶ `Role`

---

## 📌 Upcoming Tasks

- [ ] Frontend Integration for each panel
- [ ] Unit & Integration Tests

---

**Made with ❤️ by [MD Nasir Uddin]**
