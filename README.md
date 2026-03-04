# Smart Resource Allocation System

## 📌 Project Description
A backend system built using Spring Boot to allocate employees to tasks based on skills, workload, and availability.

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- REST APIs

## ⚙️ Features
- Create employees
- Allocate work based on skill match
- Automatically increase workload
- Mark employees unavailable when workload exceeds threshold
- RESTful API design

## 🔁 Project Flow
1. Client sends request to allocate work
2. System finds employee with matching skill
3. Workload is updated
4. Availability is recalculated
5. Updated employee data is returned as JSON

## 📡 API Endpoints
- POST `/employees` → Add employee
- GET `/employees` → Get all employees
- POST `/employees/allocate/{skill}` → Allocate work by skill

## 🗄 Database
- MySQL
- Employee table stores workload and availability

## ▶ How to Run
1. Clone repository
2. Update DB config in `application.properties`
3. Run Spring Boot application
4. Test APIs using Postman

## 📌 Output
JSON responses showing updated workload and allocation status.

## 👨‍💻 Author
Varshith Naga
