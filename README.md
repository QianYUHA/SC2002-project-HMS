# 🏥 Hospital Management System (HMS)

Welcome to the **Hospital Management System** project! 🎉 This robust, Java-based application is built with meticulous attention to detail and features comprehensive JavaDoc documentation for every class and method. It addresses the needs of healthcare institutions by offering a structured, modular, and scalable solution to manage hospital operations.

---

## 🌟 Key Features

### 1. **Appointment Scheduling and Management** 🗓️
- **Patient-centric Scheduling**: Patients can select doctors and view available slots in real-time (`appointments.txt` and `unavailable_slots.txt`).
- **Status Updates**: Appointment statuses (`pending` or `completed`) are managed seamlessly for efficient tracking.
- **Doctor Availability**: Integrated with `TimeSlot` and `AppointmentManager` classes to manage availability.

---

### 2. **Inventory Control** 📦
- **Live Inventory Management**: Uses `inventory.txt` for real-time tracking of stock levels.
- **Automated Replenishments**: Low stock automatically generates requests stored in `replenishment_requests.txt`.
- **Role-based Access**: Pharmacists oversee inventory and manage replenishments.

---

### 3. **Medical Records** 📋
- **Secure and Categorized Data**: Patient medical histories are securely stored in `medicalrecord.txt`.
- **Doctor Utilities**: Doctors can add or retrieve records using the `MedicalRecord` and `MedicalRecord$RecordEntry` classes.
- **Optimized Retrieval**: Structured for quick access and efficient storage.

---

### 4. **Role-Based Operations** 👥
Each role comes with specific functionalities to ensure clarity and responsibility:
- **Administrator**: Oversees user management, inventory, and scheduling.
- **Doctor**: Manages appointments and updates medical records.
- **Patient**: Schedules, reschedules, or cancels appointments via a user-friendly interface.
- **Pharmacist**: Accesses completed appointments and manages inventory.

---

### 5. **Advanced Utilities and Validation** 🔧
- **Data Integrity**: All inputs are validated using `ValidationUtils` to ensure consistent and error-free operations.
- **Exception Handling**: Custom error handling enhances system stability.

---

## 🛠️ How It Works

1. **Appointment Process**:
   - Patients view doctor availability (`appointments.txt`).
   - They can schedule or reschedule appointments, with updates logged automatically.
   
2. **Inventory Monitoring**:
   - Real-time tracking using `InventoryManager` ensures no shortages.
   - Alerts are generated for low-stock items.

3. **Role-Specific Access**:
   - Only relevant data and functionalities are exposed to each role, ensuring security and usability.

4. **Medical Record Updates**:
   - Doctors securely update records for each patient.
   - Records are retrieved efficiently using categorized entries (`MedicalRecord$MedicalRecordEntry`).

---

## 📂 Directory Structure

```plaintext
📂 OODP - FINAL
├── 📁 AppointmentManagement
│   ├── Appointment.java
│   ├── AppointmentManager.java
├── 📁 InventoryManagement
│   ├── Inventory.java
│   ├── InventoryManager.java
├── 📁 MedicalRecords
│   ├── MedicalRecord.java
│   ├── MedicalRecord$RecordEntry.java
├── 📁 UserManagement
│   ├── Administrator.java
│   ├── Doctor.java
│   ├── Patient.java
│   ├── Pharmacist.java
├── 📄 ValidationUtils.java
├── 📂 Data Files (.txt)
│   ├── appointments.txt
│   ├── medicalrecord.txt
│   ├── inventory.txt
│   ├── replenishment_requests.txt
