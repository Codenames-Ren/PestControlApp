# 🐜 Pest Control Management System

## 📌 Project Overview

Pest Control Management System is a simple Java desktop application built using **Java Swing** in **NetBeans**. This application helps manage pest control services, including **client data**, **service packages**, and **payment transactions**.

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

```
PestControlApp/
├── Source Packages/
│   ├── <default package>/
│   │   └── hibernate.cfg.xml  # Hibernate configuration file
│   ├── images/                # Image assets
│   ├── pestcontrol.app/       # Application entry & main logic
│   ├── pestcontrol.entity/    # Hibernate entity classes
│   ├── pestcontrol.util/      # Utilities (Hibernate config, helpers)
│   └── view/                  # Java Swing UI (Frames & Forms)
├── Libraries/
└── README.md
```

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

1. Create a database (example: `pest_control_db`)
2. Update database configuration in `hibernate.cfg.xml`
3. Run MainApp.Java at /PestControlApp/SourcePackages/pestcontrol.app/MainApp.Java <- Here
4. Hibernate ORM will automigrate the databases
PS. For Login, you must create account with manual queries in your postgree terminal or GUI like Dbeaver.


```xml
<property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/pest_control_db</property>
<property name="hibernate.connection.username">postgres</property>
<property name="hibernate.connection.password">your_password</property>
```

---

### 4️⃣ Add Required Libraries

Make sure these libraries are added to the project:

* Hibernate Core
* PostgreeSQL Connector / JDBC Driver
* JPA / Persistence API

📌 *If missing, right-click project → Properties → Libraries → Add JAR/Library*

---

## ▶️ Running the Project

1. Locate the **main frame** or **main class** (e.g. `Main.java` or `FrameLogin.java`)
2. Right-click the file
3. Click **Run File**

If configured correctly, the application window should appear.

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
