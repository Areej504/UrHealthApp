import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/URHealthApp";
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
                LoginUI ui = new LoginUI();
                User currUser;

                //construct user and launch appropriate user view
                if(ui.getSelectedView()==UserViews.Member){
                    currUser = new Member();
                    new MemberView(currUser);
                } else if (ui.getSelectedView()==UserViews.Trainer) {
                    currUser = new Trainer();
                    new TrainerView(currUser);
                }else if(ui.getSelectedView()==UserViews.Admin_Staff){
                    currUser= new AdminStaff();
                    new AdminView(currUser);
                }

            } else {
                System.out.println("Failed to establish connection.");
            }
            //Close the connection
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
