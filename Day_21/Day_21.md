# 🏥 Doctor Appointment Booking System

A full-featured Doctor Appointment Booking System where patients can book appointments with doctors, and the system manages all data using relational tables.

## 🚀 Features

- 🧑‍⚕️ Doctor Registration & Profile Management
- 🧑‍💼 Patient Registration & Login
- 📅 Appointment Booking & Scheduling
- 📋 Medical Test Booking
- 📌 Role-Based Access (Admin, Doctor, Patient)
- 🔐 Secure Authentication with JWT
- 🧩 Full Relational Database Design

## 🛠 Tech Stack

- **Backend:** Spring Boot 3.4.5
- **Security:** Spring Security, JWT
- **Frontend:** Thymeleaf (Server-side rendering)
- **Database:** MySQL
- **Build Tool:** Gradle
- **JDK:** 21

## 🗃️ Database Design (Tables & Relationships)

Here are some core entities and their relationships:

- `Patient` ⟶ One-to-Many ⟶ `Appointment`
- `Doctor` ⟶ One-to-Many ⟶ `Appointment`
- `Appointment` ⟶ Many-to-One ⟶ `Doctor`, `Patient`
- `TestBooking` ⟶ Many-to-One ⟶ `Patient`
- `Role` ⟶ One-to-Many ⟶ `User`
- `User` ⟶ Many-to-One ⟶ `Role`

> All foreign keys are properly maintained with referential integrity.

## ⚙️ How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/doctor-booking-system.git
   cd doctor-booking-system
