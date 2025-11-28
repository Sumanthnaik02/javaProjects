import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDBConnection {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";  // Replace 'orcl' with your SID or Service Name
        String username = "FLEX147";  // Change to your Oracle username
        String password = "FLEX147";  // Change to your Oracle password

        try {
            // Load the Oracle JDBC Driver
            Class.forName("oracle.jdbc.OracleDriver");

            // Establish Connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Oracle Database!");

            // Execute a simple query
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SYSDATE FROM dual");

            while (rs.next()) {
                System.out.println("Current Database Date: " + rs.getString(1));
            }

            // Close resources
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
