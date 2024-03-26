import java.sql.Connection;

public class Trainer extends User{
    public Trainer(String email, Connection conn) {
        super(email, conn);
    }
}
