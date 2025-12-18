# 🐜 Pest Control Management System

## 📌 Project Overview

Pest Control Management System is a simple Java desktop application built using **Java Swing** in **NetBeans**. The application is designed to manage pest control services, including **client data**, **service packages**, and **payment transactions**.

This project was developed as a **final exam requirement for the 3rd semester** in college.

---

## 🛠 Tech Stack

* ☕ Java (JDK 21)
* 🖥 Java Swing (GUI)
* 🗄 Hibernate ORM
* 🐘 PostgreSQL
* 🧰 NetBeans IDE

---

## 📂 Project Structure

The project follows a simple **MVC-style structure** as reflected in the GitHub repository. Please note that **NetBeans may display packages differently** under *Source Packages*, but the structure below represents the actual directory layout.

```
PestControlApp/
├── src/
│   ├── images/                 # Image assets used in the UI
│   ├── pestcontrol/
│   │   ├── app/                # Application entry point & controllers
│   │   │   └── MainApp.java    # Main class to run the application
│   │   ├── entity/             # Hibernate entity (model) classes
│   │   └── util/               # Utility classes (Hibernate session, helpers)
│   ├── view/                   # Java Swing UI (views / forms)
│   └── hibernate.cfg.xml       # Hibernate configuration file
├── Libraries/
└── README.md
```

📌 *Note:* This structure may look different in NetBeans due to how the IDE groups **Source Packages**, but the actual folder structure in GitHub reflects the real package layout.

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Codenames-Ren/PestControlApp.git
```

---

### 2️⃣ Open Project in NetBeans

1. Open **NetBeans IDE**
2. Click **File → Open Project**
3. Select the cloned project folder

---

### 3️⃣ Configure Database

1. Create a PostgreSQL database (example: `pest_control_db`)
2. Update the database configuration inside `hibernate.cfg.xml`
3. Run `MainApp.java` located at:
   `src/pestcontrol/app/MainApp.java`
4. Hibernate ORM will automatically generate the required tables

📌 **Note:**

* Login accounts must be created manually using PostgreSQL queries
* You can use tools like **psql** or **DBeaver** for this purpose

```xml
<property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/pest_control_db</property>
<property name="hibernate.connection.username">postgres</property>
<property name="hibernate.connection.password">your_password</property>
```

---

### 4️⃣ Add Required Libraries

Make sure these libraries are added to the project:

* Hibernate Core
* MySQL Connector / JDBC Driver
* JPA / Persistence API

📌 *If missing, right-click project → Properties → Libraries → Add JAR/Library*

---

## ▶️ Running the Project

1. Open the project in **NetBeans**
2. Navigate to `pestcontrol.app.MainApp`
3. Right-click `MainApp.java`
4. Select **Run File**

If everything is configured correctly, the application window should appear.

---

## ✨ Features

* 📋 Manage client data
* 📦 Manage pest control service packages
* 💳 Record payment transactions
* 🗃 Database integration using Hibernate
* 🖥 Simple and user-friendly desktop interface

---

## 📝 Notes

* This project is for **educational purposes**
* UI and features are kept simple according to semester-level requirements
* Feel free to modify or improve the project

---

## 👤 Author

Developed by **Bayu Sukma**
3rd Semester Student

---

## ⭐ Acknowledgement

Thanks to lecturers and references that supported the development of this project.
