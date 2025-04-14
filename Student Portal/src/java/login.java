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

@WebServlet("/login")
public class login extends HttpServlet {

    // Admin credentials
    private static final String ADMIN_EMAIL = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve email and password from form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Admin login check
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedIn", true);
            session.setAttribute("role", "admin");

            response.sendRedirect("Dashboard");
            return;
        }

        // Try user login from database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", true);
                session.setAttribute("role", "user");

                // Store user data
                session.setAttribute("userId", rs.getInt("userId"));
                session.setAttribute("name", rs.getString("name"));
                session.setAttribute("fatherName", rs.getString("fatherName"));
                session.setAttribute("motherName", rs.getString("motherName"));
                session.setAttribute("phone", rs.getString("phone"));
                session.setAttribute("grade", rs.getString("grade"));
                session.setAttribute("city", rs.getString("city"));
                session.setAttribute("state", rs.getString("state"));
                session.setAttribute("religion", rs.getString("religion"));
                session.setAttribute("gender", rs.getString("gender"));
                session.setAttribute("profileImage", rs.getString("profileImage"));

                response.sendRedirect("userProfile");

            } else {
                // Invalid credentials: Show styled error page
                out.println("<html><head><title>Login Failed</title>");
                out.println("<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap' rel='stylesheet'>");
                out.println("<style>");
                out.println("body { background: #f8f9fa; font-family: 'Roboto', sans-serif; display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0; }");
                out.println(".card { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 0 20px rgba(0,0,0,0.1); text-align: center; width: 400px; }");
                out.println(".card h2 { color: #dc3545; margin-bottom: 20px; }");
                out.println(".btn { background-color: #007bff; color: white; padding: 10px 20px; border: none; border-radius: 8px; font-size: 16px; cursor: pointer; text-decoration: none; }");
                out.println(".btn:hover { background-color: #0056b3; }");
                out.println("</style></head><body>");
                out.println("<div class='card'>");
                out.println("<h2>Invalid Email or Password</h2>");
                out.println("<p>Please check your credentials and try again.</p>");
                out.println("<a href='index.html' class='btn'>Go Back</a>");
                out.println("</div>");
                out.println("</body></html>");
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
