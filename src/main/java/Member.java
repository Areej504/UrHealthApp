import java.sql.Connection;

public class Member extends User{

    public Member(String email, Connection conn) {
        super(email, conn);
    }

    public void updateDashboard() {
    }

    public void getHealthMetrics() {
    }

    public void getFitnessGoals() {
    }

    public void getPersonalBookings() {
    }

    public void getGroupBookings() {
    }

    public void getAvailableSessions() {
    }

    public void getAvailableClasses() {
    }
}
