import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EditUser")
public class EditUserServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/School";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("userId");

        if (userId == null || userId.isEmpty()) {
            out.println("User ID is required.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "SELECT * FROM users WHERE userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(userId));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                out.println("<html><head><title>Edit User</title>");
                out.println("<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap' rel='stylesheet'>");
                out.println("<style>");
                out.println("body { font-family: 'Roboto', sans-serif; background: #f8f9fa; margin: 0; padding: 0; }");
                out.println(".container { width: 100%; max-width: 600px; margin: 50px auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 10px 40px rgba(0,0,0,0.1); }");
                out.println("h1 { text-align: center; color: #343a40; margin-bottom: 30px; font-size: 28px; }");
                out.println("form label { display: block; margin: 15px 0 5px; font-weight: 500; color: #333; }");
                out.println("form input[type='text'], form input[type='email'] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px; }");
                out.println("form input[type='radio'] { margin-right: 5px; }");
                out.println(".gender-group { margin-top: 10px; }");
                out.println(".submit-btn { margin-top: 30px; background-color: #007bff; color: white; border: none; padding: 12px 20px; border-radius: 6px; font-size: 16px; cursor: pointer; width: 100%; }");
                out.println(".submit-btn:hover { background-color: #0056b3; }");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<div class='container'>");
                out.println("<h1>Edit Student</h1>");
                out.println("<form action='EditUser' method='POST'>");

                out.println("<input type='hidden' name='userId' value='" + rs.getInt("userId") + "'>");
                out.println("<label>Name:</label><input type='text' name='name' value='" + rs.getString("name") + "' required>");
                out.println("<label>Father's Name:</label><input type='text' name='fatherName' value='" + rs.getString("fatherName") + "' required>");
                out.println("<label>Mother's Name:</label><input type='text' name='motherName' value='" + rs.getString("motherName") + "' required>");
                out.println("<label>Email:</label><input type='email' name='email' value='" + rs.getString("email") + "' required>");
                out.println("<label>Phone:</label><input type='text' name='phone' value='" + rs.getString("phone") + "' required>");
                out.println("<label>Grade:</label><input type='text' name='grade' value='" + rs.getString("grade") + "' required>");
                out.println("<label>City:</label><input type='text' name='city' value='" + rs.getString("city") + "' required>");
                out.println("<label>State:</label><input type='text' name='state' value='" + rs.getString("state") + "' required>");
                out.println("<label>Religion:</label><input type='text' name='religion' value='" + rs.getString("religion") + "' required>");

                out.println("<label>Gender:</label>");
                out.println("<div class='gender-group'>");
                out.println("<input type='radio' name='gender' value='Male' " + (rs.getString("gender").equals("Male") ? "checked" : "") + "> Male");
                out.println("<input type='radio' name='gender' value='Female' " + (rs.getString("gender").equals("Female") ? "checked" : "") + "> Female");
                out.println("</div>");

                out.println("<label>Profile Image URL:</label><input type='text' name='profileImage' value='" + rs.getString("profileImage") + "' required>");

                out.println("<button class='submit-btn' type='submit'>Update Student</button>");
                out.println("</form></div></body></html>");
            } else {
                out.println("User not found!");
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred while fetching the user.");
        }
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
        String profileImage = request.getParameter("profileImage");

        HttpSession session = request.getSession();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "UPDATE users SET name = ?, fatherName = ?, motherName = ?, email = ?, phone = ?, grade = ?, city = ?, state = ?, religion = ?, gender = ?, profileImage = ? WHERE userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, fatherName);
            pstmt.setString(3, motherName);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setString(6, grade);
            pstmt.setString(7, city);
            pstmt.setString(8, state);
            pstmt.setString(9, religion);
            pstmt.setString(10, gender);
            pstmt.setString(11, profileImage);
            pstmt.setInt(12, Integer.parseInt(userId));

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                session.setAttribute("message", "User updated successfully!");
            } else {
                session.setAttribute("message", "Failed to update user.");
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "An error occurred while updating the user.");
        }

        response.sendRedirect("Dashboard");
    }
}
