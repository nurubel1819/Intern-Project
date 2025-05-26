# ✅ Daily Work Log - 26/05/2025

**Name:** MD Nasir Uddin  
**Date:** 26/05/2025  
**Total Hours Worked:** 8 Hours  
**GitHub Repository:** [Intern Project](https://github.com/nurubel1819/Intern-Project)

---

## 🧠 Task Overview

### ✅ Task 1: Notification Feature Integration using SOLID Principles — `5 Hours`

- Integrated a **Notification module** into the project following **SOLID design principles**:
  - **S**ingle Responsibility: Separated notification logic from controller/service layers.
  - **O**pen/Closed: New notification types (email, UI) can be added without modifying existing code.
  - **L**iskov Substitution: Abstracted base notification class/interface used.
  - **I**nterface Segregation: Split interfaces for senders and receivers.
  - **D**ependency Inversion: Notification logic depends on abstractions, not concrete classes.

- Created interfaces like:
  - `INotificationSender`
  - `UINotificationSender`
  - `EmailNotificationSender`

- Applied Spring Boot dependency injection to manage these services.

---

### 🎨 Task 2: Notification UI Design — `3 Hours`

- Designed the **frontend UI** to show and send notifications:
  - Created notification list view (for users)
  - Added alert box with status colors (info, success, error)
  - Designed notification sending form (admin/user)
  - Responsive layout integrated using CSS Flex/Grid