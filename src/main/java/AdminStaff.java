import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminStaff extends User{

    public AdminStaff(String email, Connection conn) {
        super(email, conn);
    }

    public void getRooms() throws SQLException {
        this.clear(); //clear list to retrieve new info and display it
        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        String SQL = "SELECT * FROM Rooms;";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        String tuple;
        while(rs.next()){
            String room_id = rs.getString("room_id");
            String building = rs.getString("building");
            tuple="room_id: "+room_id+", building: "+building;
            addElement(tuple);
        }
        // Close resources
        rs.close();
        stmt.close();
    }

    public void getEquipment() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        String SQL = "SELECT * FROM Equipment;";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        String tuple;
        while(rs.next()){
            String equip_name = rs.getString("equip_name");
            String date = rs.getString("maintenance_date");
            tuple = "equip_name: "+equip_name+", maintenance_date: "+date;
            addElement(tuple);
        }
        // Close resources
        rs.close();
        stmt.close();
    }

    public void getBillings() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        String SQL = "SELECT * FROM Billings;";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        String tuple;
        while(rs.next()){
            String billing_id = rs.getString("billing_id");
            String mem_id = rs.getString("mem_email");
            String amount = rs.getString("amount");
            String date = rs.getString("date");
            tuple = "billing_id: "+billing_id+", mem_email: "+mem_id+", amount: "+amount+", date: "+date;
            addElement(tuple);
        }
        // Close resources
        rs.close();
        stmt.close();
    }
}
