import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends DefaultListModel {
    public String email; //the unique user id
    public Connection conn; //postgreSQL connection

    public User(String email, Connection conn) {
        super();
        this.email = email;
        this.conn = conn;
    }

    /**
     * set up a connection with postgreSQL for this user
     * */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void getPersonalInfo() throws SQLException {
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        String SQL = "SELECT *\n" +
                "FROM Members\n" +
                "WHERE email = '" + email + "';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            String email = rs.getString("email");
            addElement(email);
            String firstName = rs.getString("first_name");
            addElement(firstName);
            String lastName = rs.getString("last_name");
            addElement(lastName);
        }
        // Close resources
        rs.close();
        stmt.close();
    }

}
