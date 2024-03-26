import java.sql.Connection;

public class Member extends User{

    public Member(String email, Connection conn) {
        super(email, conn);
    }
}
