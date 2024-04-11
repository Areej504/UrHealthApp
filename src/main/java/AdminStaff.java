import java.sql.*;

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

    public void getPersonalSessions() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        //get all personal sessions and include the room info for each
        String SQL = "SELECT ps.*\n" +
                "FROM Personal_sessions ps;";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            StringBuilder sb = new StringBuilder("<html>");
            String session_id = rs.getString("session_id");
            String trainer_email = rs.getString("trainer_email");
            String session_date = rs.getString("session_date");
            String session_time = rs.getString("session_time");
            String room_id = rs.getString("room_id");
            sb.append("session_id: "+session_id);
            sb.append("<br>trainer_email: "+trainer_email);
            sb.append("<br>session_date: "+session_date);
            sb.append("<br>session_time: "+session_time);
            sb.append("<br>room_id: "+room_id);
            sb.append("</html>");
            addElement(sb.toString());
        }
        // Close resources
        rs.close();
        stmt.close();

    }
    public void getGroupClasses() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        //get all group classes (capacity limit for group classes not implemented)
        String SQL = "SELECT gc.*\n" +
                "FROM Group_classes gc;";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            StringBuilder sb = new StringBuilder("<html>");
            String class_id = rs.getString("class_id");
            String trainer_email = rs.getString("trainer_email");
            String class_date = rs.getString("class_date");
            String class_time = rs.getString("class_time");
            String room_id = rs.getString("room_id");
            sb.append("class_id: "+class_id);
            sb.append("<br>trainer_email: "+trainer_email);
            sb.append("<br>class_date: "+class_date);
            sb.append("<br>class_time: "+class_time);
            sb.append("<br>room_id: "+room_id);
            sb.append("</html>");
            addElement(sb.toString());
        }
        // Close resources
        rs.close();
        stmt.close();
    }

    public void cancelPersonalSession(int index){
        int session_id = parseSessionId((String) getElementAt(index));
        //delete the session from personal_sessions and any booking for that session from personal_bookings
        String deleteSQL = "DELETE FROM personal_sessions\n" +
                "WHERE session_id = ?;\n"+
                "DELETE FROM personal_bookings\n" +
                "WHERE session_id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, session_id);
            pstmt.setInt(2, session_id);
            pstmt.executeUpdate();
            System.out.println("Data deleted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void cancelGroupClass(int index){
        int class_id = parseSessionId((String) getElementAt(index));

        //delete the class from group_classes and any booking for that class from group_bookings
        String deleteSQL = "DELETE FROM group_classes\n" +
                "WHERE class_id = ?;\n"+
                "DELETE FROM group_bookings\n" +
                "WHERE class_id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, class_id);
            pstmt.setInt(2, class_id);
            pstmt.executeUpdate();
            System.out.println("Data deleted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void changePersonalRoom(int index, String newRoom) {
        int session_id = parseSessionId((String) getElementAt(index));
        //update the room number with the given newRoom.
        String insertSQL = "UPDATE personal_sessions\n" +
                "SET room_id = ? \n" +
                "WHERE session_id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, Integer.parseInt(newRoom));
            pstmt.setInt(2, session_id);
            pstmt.executeUpdate();
            System.out.println("Data updated using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void changeGroupRoom(int index, String newRoom) {
        int class_id = parseSessionId((String) getElementAt(index));
        //update the room number with the given newRoom.
        String insertSQL = "UPDATE group_classes\n" +
                "SET room_id = ? \n" +
                "WHERE class_id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, Integer.parseInt(newRoom));
            pstmt.setInt(2, class_id);
            pstmt.executeUpdate();
            System.out.println("Data updated using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
