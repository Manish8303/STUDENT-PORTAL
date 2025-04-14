import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/School";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String userId = request.getParameter("id");
            deleteStudent(userId, response);
            return;
        }

        String userId = request.getParameter("userId");
        if (userId != null) {
            showStudentDetails(userId, response);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users ORDER BY userId ASC");

            out.println("<html><head><title>Admin Dashboard</title>");
            out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { font-family: 'Roboto', sans-serif; background: linear-gradient(to right, #f8f9fa, #e9ecef); margin: 0; padding: 0; }");
            out.println("h1 { text-align: center; color: #343a40; margin-top: 30px; font-size: 36px; }");
            out.println(".container { width: 90%; max-width: 1200px; margin: 30px auto; background-color: white; border-radius: 12px; padding: 30px; box-shadow: 0 10px 40px rgba(0,0,0,0.1); }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 30px; }");
            out.println("th, td { padding: 15px; text-align: left; border-bottom: 1px solid #dee2e6; font-size: 16px; }");
            out.println("th { background-color: #007bff; color: white; }");
            out.println("tr:hover { background-color: #f1f3f5; transition: background 0.3s ease; }");
            out.println(".btn { padding: 10px 18px; font-size: 14px; font-weight: 500; cursor: pointer; color: white; border: none; border-radius: 6px; transition: all 0.3s ease; text-decoration: none; display: inline-block; }");
            out.println(".btn-add { background-color: #17a2b8; margin-bottom: 15px; }");
            out.println(".btn-add:hover { background-color: #138496; }");
            out.println(".btn-edit { background-color: #ffc107; color: black; }");
            out.println(".btn-edit:hover { background-color: #e0a800; }");
            out.println(".btn-delete { background-color: #dc3545; }");
            out.println(".btn-delete:hover { background-color: #c82333; }");
            out.println(".btn-view { background-color: #28a745; }");
            out.println(".btn-view:hover { background-color: #218838; }");
            out.println(".btn-logout { background-color: #343a40; position: absolute; top: 50px; right: 120px; }");
            out.println(".btn-logout:hover { background-color: #23272b; }");
            out.println(".actions { display: flex; gap: 10px; }");
            out.println("</style>");
            out.println("</head><body>");

            out.println("<a href='logout'><button class='btn btn-logout'><i class='fas fa-sign-out-alt'></i> Logout</button></a>");
            out.println("<div class='container'>");
            out.println("<h1>Student Dashboard</h1>");
            out.println("<a href='AddUserServlet' class='btn btn-add'><i class='fas fa-user-plus'></i> Add New User</a>");
            out.println("<a href='Student_Test_Grades.html' class='btn btn-add'><i class='fas fa-clipboard'></i> Add User Grades</a>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Grade</th><th>City</th><th>Gender</th><th>Phone</th><th>Actions</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("userId") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("grade") + "</td>");
                out.println("<td>" + rs.getString("city") + "</td>");
                out.println("<td>" + rs.getString("gender") + "</td>");
                out.println("<td>" + rs.getString("phone") + "</td>");
                out.println("<td><div class='actions'>");
                out.println("<a href='Dashboard?userId=" + rs.getInt("userId") + "' class='btn btn-view'>View</a>");
                out.println("<a href='EditUser?userId=" + rs.getInt("userId") + "' class='btn btn-edit'>Edit</a>");
                out.println("<a href='Dashboard?action=delete&id=" + rs.getInt("userId") + "' class='btn btn-delete' onclick='return confirm(\"Are you sure you want to delete this user?\");'><i class='fas fa-trash'></i> Delete</a>");
                out.println("</div></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</div></body></html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<p>Error loading data.</p>");
        }
    }

    private void showStudentDetails(String userId, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE userId = ?");
        stmt.setString(1, userId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            out.println("<html><head><title>Student Profile</title>");
            out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css' rel='stylesheet' />");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { margin: 0; padding: 0; font-family: 'Roboto', sans-serif; background: #f1f2f6; display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".card { background: #fff; border-radius: 16px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1); padding: 30px 40px; max-width: 800px; width: 90%; position: relative; }");
            out.println(".close { position: absolute; top: 20px; right: 30px; font-size: 26px; cursor: pointer; color: #888; }");
            out.println(".profile { display: flex; gap: 30px; align-items: center; }");
            out.println(".profile img { width: 140px; height: 140px; border-radius: 50%; object-fit: cover; border: 3px solid #ddd; }");
            out.println(".info { flex: 1; }");
            out.println(".info h2 { margin-bottom: 10px; font-size: 28px; color: #333; }");
            out.println(".info p { margin: 6px 0; font-size: 16px; color: #555; }");
            out.println(".info p strong { color: #222; }");
            out.println("@media (max-width: 600px) { .profile { flex-direction: column; align-items: center; } .profile img { margin-bottom: 20px; } }");
            out.println("</style>");
            out.println("</head><body>");

            out.println("<div class='card'>");
            out.println("<span class='close' onclick='window.location.href=\"Dashboard\"'>&times;</span>");
            out.println("<div class='profile'>");

            String profileImage = rs.getString("profileImage");
            if (profileImage != null && !profileImage.isEmpty()) {
                out.println("<img src='" + profileImage + "' alt='Profile Image' />");
            } else {
                out.println("<img src='https://via.placeholder.com/140?text=No+Image' alt='Profile Image' />");
            }

            out.println("<div class='info'>");
            out.println("<h2>" + rs.getString("name") + "</h2>");
            out.println("<p><strong>Father's Name:</strong> " + rs.getString("fatherName") + "</p>");
            out.println("<p><strong>Mother's Name:</strong> " + rs.getString("motherName") + "</p>");
            out.println("<p><strong>Email:</strong> " + rs.getString("email") + "</p>");
            out.println("<p><strong>Phone:</strong> " + rs.getString("phone") + "</p>");
            out.println("<p><strong>Grade:</strong> " + rs.getString("grade") + "</p>");
            out.println("<p><strong>City:</strong> " + rs.getString("city") + "</p>");
            out.println("<p><strong>State:</strong> " + rs.getString("state") + "</p>");
            out.println("<p><strong>Religion:</strong> " + rs.getString("religion") + "</p>");
            out.println("<p><strong>Gender:</strong> " + rs.getString("gender") + "</p>");
            out.println("</div></div></div>");
            out.println("</body></html>");
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace(out);
        out.println("<p>Error fetching student details.</p>");
    }
}


    private void deleteStudent(String userId, HttpServletResponse response) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "DELETE FROM users WHERE userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            response.sendRedirect("Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
