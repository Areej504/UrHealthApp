import java.sql.Connection;

public class User {
    private int userID;

    private Connection conn;

    public User(int userID) {
        this.userID = userID;
    }
}
