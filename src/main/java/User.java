import javax.swing.*;
import java.sql.Connection;

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
}
