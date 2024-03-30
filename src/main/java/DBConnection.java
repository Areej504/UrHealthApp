import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/UrHealthApp";
        String user = "postgres";
        String password = "24AreejSQL";
        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to PostgreSQL successfully!");
                DBConnection dbmsConn = new DBConnection();

                //Launch the GUI
                LoginUI ui = new LoginUI(conn);

                //construct user and launch appropriate user view
                if(ui.getSelectedView()==UserViews.Members){
                    Member currUser = new Member(ui.getUserEmail(), conn);
                    new MemberView(currUser);
                } else if (ui.getSelectedView()==UserViews.Trainer) {
                    Trainer currUser = new Trainer(ui.getUserEmail(), conn);
                    new TrainerView(currUser);
                }else if(ui.getSelectedView()==UserViews.Admin_Staff){
                    AdminStaff currUser= new AdminStaff(ui.getUserEmail(), conn);
                    new AdminView(currUser);
                }

            } else {
                System.out.println("Failed to establish connection.");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
