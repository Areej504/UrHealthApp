import javax.swing.*;
import java.sql.Connection;

public class User extends DefaultListModel {
    public int userID; //the unique user id
    public Connection conn; //postgreSQL connection

    public User() {
        super();
    }

    /**
     * set up a connection with postgreSQL for this user
     * */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
