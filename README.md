🎓 Student Portal Web Application
Welcome to the Student Portal, a comprehensive web-based platform developed as part of the Advanced Internet Programming course. This project leverages core Java EE technologies to manage students, profiles, and academic data in a structured and secure way.

🚧 Project Overview
This system demonstrates real-world implementation of the MVC architecture using:

Java Servlets for backend logic

JavaBeans for data encapsulation

JSP for rendering dynamic content

MySQL for persistent storage

It’s built to be deployed on an Apache Tomcat Server and is connected to a MySQL database for managing user and academic records.

💡 Key Highlights
✅ User Registration and Login

👤 View and Edit User Profiles

📝 Grade Management

🧱 MVC-based Modular Codebase

💾 Secure Database Integration

📬 Password Reset Functionality

🧰 Tech Stack
Layer	Technologies Used
Frontend	HTML, CSS, JSP, [Your Input]
Backend	Java Servlets, JavaBeans
Database	MySQL
Server	Apache Tomcat [Your Version]
Connectivity	JDBC
Tools Used	[Eclipse / IntelliJ / etc.]

🛠️ Setup Instructions
Follow these steps to run the project locally:

1️⃣ Clone the Repository
bash
Copy
Edit
git clone https://github.com/your-username/student-portal.git
cd student-portal
2️⃣ Open in IDE
Open the project in your preferred IDE
(Eclipse, IntelliJ IDEA, NetBeans, etc.)

Ensure Apache Tomcat is configured properly in your IDE.

3️⃣ Setup the Database
Launch phpMyAdmin or MySQL Workbench.

Create a database named:

sql
Copy
Edit
CREATE DATABASE school;
Import the provided SQL script:

Navigate to the database/ folder

Import school.sql

4️⃣ Configure DB Connection
In your DBConnection.java file (typically under src/utils/ or src/dao/), update the credentials:

java
Copy
Edit
String url = "jdbc:mysql://localhost:3306/school";
String username = "root"; // or your username
String password = "";     // your DB password
5️⃣ Deploy the Web App
Run the project using your IDE:

Right-click → Run on Server → Select Tomcat
OR

Build a .war file and deploy it to /webapps/ in Tomcat

Then open in browser:

bash
Copy
Edit
http://localhost:8080/student-portal/
📂 Project Structure
plaintext
Copy
Edit
📁 StudentPortal/
├── 📦 build/                    # Compiled classes
├── 📦 dist/
│   └── StudentPortal.war       # Deployable WAR file
├── 📁 src/
│   ├── 📄 DBConnection.java
│   ├── 📄 AddUserServlet.java
│   ├── 📄 EditUserServlet.java
│   ├── 📄 RegisterServlet.java
│   ├── 📄 login.java
│   ├── 📄 logout.java
│   ├── 📄 userProfile.java
│   └── 📄 Student_Test_Grades.java
├── 🌐 web/
│   ├── 📄 index.html
│   ├── 📄 registration.html
│   ├── 📄 reset_password.html
│   └── 📄 forgot.html
└── 📄 web.xml                   # Deployment descriptor
You can expand this with your actual folder names and more servlets/pages.

🖼️ Screenshots
Add your screenshots in the screenshots/ folder and update this section accordingly:

html
Copy
Edit
<div style="display: flex; flex-wrap: wrap; gap: 10px;">
  <img src="./screenshots/1.png" width="500"/>
  <img src="./screenshots/2.png" width="500"/>
  <!-- Add more as needed -->
</div>
👤 Author
Name: [Your Full Name]
Email: [your.email@example.com]
Course: Advanced Internet Programming
Institution: [Your University Name]



