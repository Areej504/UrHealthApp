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

    public void getPersonalInfo() throws SQLException {
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        String SQL = "SELECT *  FROM\n" +
                "(SELECT * FROM Members \n" +
                " UNION ALL \n" +
                " SELECT * FROM Trainer \n" +
                " UNION ALL \n" +
                " SELECT * FROM Admin_Staff) AS all_users\n" +
                " WHERE all_users.email = '"+email+"';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            String email = rs.getString("email");
            addElement("email: "+email);
            System.out.println(getElementAt(0));
            String firstName = rs.getString("first_name");
            addElement("first_name: "+firstName);
            System.out.println(getElementAt(1));
            String lastName = rs.getString("last_name");
            addElement("first_name: "+lastName);
            System.out.println(getElementAt(2));
        }
        // Close resources
        rs.close();
        stmt.close();
    }

}
