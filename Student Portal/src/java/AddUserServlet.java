import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/School";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Display the HTML form for adding a user
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Add User</title>");
        out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css' rel='stylesheet' />");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #e9f0f7; margin: 0; padding: 0; }");
        out.println(".container { max-width: 700px; margin: 50px auto; padding: 30px 40px; background-color: #ffffff; border-radius: 12px; box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1); }");
        out.println("h1 { text-align: center; color: #2c3e50; margin-bottom: 30px; }");
        out.println("form { display: flex; flex-wrap: wrap; gap: 30px 20px; }");
        out.println(".half-width { flex: 1 1 45%; display: flex; flex-direction: column; }");
        out.println(".full-width { flex: 1 1 100%; display: flex; flex-direction: column; }");
        out.println("label { font-weight: 600; color: #34495e; margin-bottom: 5px; }");
        out.println("input, select { padding: 12px; border: 1px solid #ccc; border-radius: 6px; font-size: 15px; background-color: #f9f9f9; transition: border-color 0.3s; }");
        out.println("input:focus, select:focus { border-color: #3498db; outline: none; background-color: #ffffff; }");
        out.println(".btn { padding: 12px 20px; font-size: 16px; background-color: #3498db; color: #fff; border: none; border-radius: 6px; cursor: pointer; transition: background-color 0.3s ease; }");
        out.println(".btn:hover { background-color: #2980b9; }");
        out.println(".radio-group { display: flex; gap: 10px; align-items: center; }");
        out.println(".radio-group input { margin-right: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Add New User</h1>");
        out.println("<form action='AddUserServlet' method='POST'>");

        out.println("<div class='half-width'><label for='userId'>User ID</label><input type='text' id='userId' name='userId' required /></div>");
        out.println("<div class='half-width'><label for='name'>Name</label><input type='text' id='name' name='name' required /></div>");
        out.println("<div class='half-width'><label for='fatherName'>Father's Name</label><input type='text' id='fatherName' name='fatherName' required /></div>");
        out.println("<div class='half-width'><label for='motherName'>Mother's Name</label><input type='text' id='motherName' name='motherName' required /></div>");
        out.println("<div class='half-width'><label for='email'>Email</label><input type='email' id='email' name='email' required /></div>");
        out.println("<div class='half-width'><label for='phone'>Phone</label><input type='text' id='phone' name='phone' required /></div>");
        out.println("<div class='half-width'><label for='grade'>Grade</label><input type='text' id='grade' name='grade' required /></div>");
        out.println("<div class='half-width'><label for='city'>City</label><input type='text' id='city' name='city' required /></div>");

        out.println("<div class='half-width'><label for='state'>State</label><select id='state' name='state' required>");
        out.println("<option value='' disabled selected>Select State</option>");
        String[] states = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli and Daman and Diu", "Lakshadweep", "Delhi", "Puducherry", "Ladakh", "Jammu and Kashmir"};
        for (String state : states) {
            out.println("<option value='" + state + "'>" + state + "</option>");
        }
        out.println("</select></div>");

        out.println("<div class='half-width'><label for='religion'>Religion</label><select id='religion' name='religion' required>");
        out.println("<option value='' disabled selected>Select Religion</option>");
        String[] religions = {"Hindu", "Muslim", "Christian", "Sikh", "Buddhist", "Jain", "Other"};
        for (String religion : religions) {
            out.println("<option value='" + religion + "'>" + religion + "</option>");
        }
        out.println("</select></div>");

        out.println("<div class='full-width'><label>Gender</label>");
        out.println("<div class='radio-group'>");
        out.println("<label><input type='radio' name='gender' value='Male' required /> Male</label>");
        out.println("<label><input type='radio' name='gender' value='Female' required /> Female</label>");
        out.println("<label><input type='radio' name='gender' value='Other' required /> Other</label>");
        out.println("</div></div>");

        out.println("<div class='full-width'><label for='imglink'>Profile Image Path</label><input type='text' id='imglink' name='imglink' required /></div>");

        out.println("<div class='full-width'><button type='submit' class='btn'>Add User</button></div>");
        out.println("</form></div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String fatherName = request.getParameter("fatherName");
        String motherName = request.getParameter("motherName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String grade = request.getParameter("grade");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String religion = request.getParameter("religion");
        String gender = request.getParameter("gender");
        String profileImage = request.getParameter("imglink");
        String password = name;

        if (userId.isEmpty() || name.isEmpty() || fatherName.isEmpty() || motherName.isEmpty() ||
            email.isEmpty() || phone.isEmpty() || grade.isEmpty() || city.isEmpty() || state.isEmpty() ||
            religion.isEmpty() || gender.isEmpty() || profileImage.isEmpty() || password.isEmpty()) {
            response.getWriter().println("All fields are required.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "INSERT INTO users (userId, name, fatherName, motherName, email, phone, grade, city, state, religion, gender, profileImage, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, name);
            pstmt.setString(3, fatherName);
            pstmt.setString(4, motherName);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);
            pstmt.setString(7, grade);
            pstmt.setString(8, city);
            pstmt.setString(9, state);
            pstmt.setString(10, religion);
            pstmt.setString(11, gender);
            pstmt.setString(12, profileImage);
            pstmt.setString(13, password);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                response.sendRedirect("Dashboard");
            } else {
                response.getWriter().println("Error adding user.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
