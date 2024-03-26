import java.sql.Connection;

public class AdminStaff extends User{

    public AdminStaff(String email, Connection conn) {
        super(email, conn);
    }
}
