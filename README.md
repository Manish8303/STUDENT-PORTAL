# 🎓 Student Management System
### 🚀 Advanced Internet Programming Project – Web Application

Welcome to the **AIP WebApp**, an interactive Java EE-based system built using:

- **JavaBeans**
- **Servlets**
- **JSP**
- **MySQL + JDBC**
- **Apache Tomcat**

> A complete demonstration of MVC architecture with dynamic UI and database operations.  
Ideal for students, tech enthusiasts, and learners eager to explore real-world Java EE applications.

---

## 🔧 Tech Stack

| **Layer**    | **Technology**              |
|--------------|-----------------------------|
| Frontend     | HTML5, CSS3, JSP            |
| Backend      | Java Servlets, JavaBeans    |
| Server       | Apache Tomcat 9+            |
| Database     | MySQL (via phpMyAdmin)      |
| Connector    | JDBC                        |

---

## ✨ Features

- 🔐 User Signup & Login (JavaBeans + Servlets)
- 📑 Form Handling with server-side logic
- 🧠 Full CRUD Integration with MySQL
- 📊 User Grades Display
- 📦 MVC Design Pattern
- 🖥️ Dynamic JSP-Powered UI

---

## 🛠️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/aip-webapp.git
cd aip-webapp
```

---

### 2️⃣ Import into IDE

- Use **Eclipse**, **IntelliJ**, or **NetBeans**
- Go to: `File > Import > Existing Project`
- Select the cloned `aip-webapp` directory
- Ensure **Apache Tomcat 9+** is configured in your IDE

---

### 3️⃣ Set Up MySQL Database

1. Open: [http://localhost/phpmyadmin](http://localhost/phpmyadmin)
2. Create a database:
   ```sql
   CREATE DATABASE aip_project_db;
   ```
3. Import the provided `aip_project_db.sql` file:
   - Go to phpMyAdmin > `Import` tab
   - Select the SQL file from `database/` folder
   - Click **Go**

---

### 4️⃣ Configure JDBC Connection

In `DBConnection.java` (usually under `src/java/utils/` or `dao/`):

```java
String url = "jdbc:mysql://localhost:3306/aip_project_db";
String username = "root";
String password = ""; // <-- Your MySQL password
```

---

### 5️⃣ Deploy on Tomcat

You can:

- Right-click project > `Run on Server` > Select Tomcat  
**OR**
- Deploy the `.war` file to `Tomcat/webapps/` manually

Then visit:

```bash
http://localhost:8080/aip-webapp/
```

---

## 🗂️ Project Structure

```bash
📦 StudentManagementSystem/
├── 🛠️ build/                        # Compiled classes
├── 📦 dist/
│   └── StudentPortal.war           # WAR file
├── ⚙️ nbproject/                    # NetBeans config
├── 📁 src/
│   ├── conf/
│   │   └── MANIFEST.MF             # Manifest
│   ├── java/
│   │   ├── AddUserServlet.java
│   │   ├── DBConnection.java
│   │   ├── DashboardServlet.java
│   │   ├── EditUserServlet.java
│   │   ├── RegisterServlet.java
│   │   ├── ResetPasswordServlet.java
│   │   ├── Student_Test_Grades.java
│   │   ├── forgot.java
│   │   ├── login.java
│   │   ├── logout.java
│   │   └── userProfile.java
│   └── test/                       # Test files (if any)
├── 🌐 web/
│   ├── META-INF/
│   ├── WEB-INF/
│   │   └── web.xml                 # Deployment descriptor
│   ├── index.html
│   ├── forgot.html
│   ├── registration.html
│   └── reset_password.html
└── 🧱 build.xml                     # Apache Ant build script
```

---

## 📸 Screenshots

```
[screenshots/1.png]
[screenshots/2.png]
[screenshots/3.png]
[screenshots/4.png]
[screenshots/5.png]
[screenshots/7.png]
[screenshots/8.png]
```

---

## 🙋‍♂️ Author

**👨‍💻 Abhinash**  
📧 `abhit7575@gmail.com`

📘 *Submitted as part of the Advanced Internet Programming course.*
