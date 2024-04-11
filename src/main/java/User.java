import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User extends DefaultListModel {
    public String email; //the unique user id
    public Connection conn; //postgreSQL connection

    public User(String email, Connection conn) {
        super();
        this.email = email;
        this.conn = conn;
    }

    public void getPersonalInfo() throws SQLException {
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        String SQL = "SELECT *  FROM\n" +
                "(SELECT * FROM Members \n" +
                " UNION ALL \n" +
                " SELECT * FROM Trainers \n" +
                " UNION ALL \n" +
                " SELECT * FROM Admin_Staff) AS all_users\n" +
                " WHERE all_users.email = '"+email+"';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            String email = rs.getString("email");
            addElement("email: "+email);
            String firstName = rs.getString("first_name");
            addElement("first_name: "+firstName);
            String lastName = rs.getString("last_name");
            addElement("first_name: "+lastName);
        }
        // Close resources
        rs.close();
        stmt.close();
    }
    public int parseSessionId(String input) {
        // Regular expression pattern to match "session_id: <id>" or "class_id: <id>"
        Pattern pattern = Pattern.compile("<html>(session|class)_id: (\\d+)");
        Matcher matcher = pattern.matcher(input);

        // Check if the pattern is found in the input string
        if (matcher.find()) {
            // Extract and parse the session ID
            String sessionIdStr = matcher.group(2);
            try {
                return Integer.parseInt(sessionIdStr);
            } catch (NumberFormatException e) {
                // Handle parsing errors
                e.printStackTrace();
            }
        }
        // Return -1 if the pattern is not found or there's a parsing error
        //for debugging purposes
        return -1;
    }
}
