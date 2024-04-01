import java.sql.*;

public class Trainer extends User{
    public Trainer(String email, Connection conn) {
        super(email, conn);
    }

    public void addSession(String session_date,String session_time,String room_id){
        String insertSQL = "INSERT INTO personal_sessions (trainer_email, session_date, session_time, room_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            Date date = Date.valueOf(session_date);//converting string into sql date type
            pstmt.setDate(2, date);
            Time time = Time.valueOf(session_time+":00");//converting string into sql time type
            pstmt.setTime(3, time);
            pstmt.setInt(4, Integer.parseInt(room_id));
            pstmt.executeUpdate();
            System.out.println("Data inserted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void getBookedSessions() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        //get all personal sessions booked for this user and include the room info for each
        String SQL = "SELECT ps.session_id, pb.mem_email, ps.session_date, ps.session_time, ps.room_id, r.building\n" +
                "FROM Personal_sessions ps\n" +
                "JOIN Personal_bookings pb ON ps.session_id = pb.session_id\n" +
                "JOIN Rooms r ON ps.room_id = r.room_id\n"+
                "WHERE ps.trainer_email = '"+email+"';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            String session_id = rs.getString("session_id");
            addElement("session_id: "+session_id);
            String mem_email = rs.getString("mem_email");
            addElement("mem_email: "+mem_email);
            String session_date = rs.getString("session_date");
            addElement("session_date: "+session_date);
            String session_time = rs.getString("session_time");
            addElement("session_time: "+session_time);
            String room_id = rs.getString("room_id");
            addElement("room_id: "+room_id);
            String building = rs.getString("building");
            addElement("building: "+building);
        }
        // Close resources
        rs.close();
        stmt.close();
    }
    public void addClass(String class_date,String class_time,String room_id){
        String insertSQL = "INSERT INTO group_classes (trainer_email, class_date, class_time, room_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            Date date = Date.valueOf(class_date);//converting string into sql date type
            pstmt.setDate(2, date);
            Time time = Time.valueOf(class_time+":00");//converting string into sql time type
            pstmt.setTime(3, time);
            pstmt.setInt(4, Integer.parseInt(room_id));
            pstmt.executeUpdate();
            System.out.println("Data inserted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void getBookedClasses() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        //get all group classes booked with this user and include the room info for each
        String SQL = "SELECT gc.class_id, gc.class_date, gc.class_time, gc.room_id, r.building\n" +
                "FROM Group_classes gc\n" +
                "JOIN Group_bookings gb ON gc.class_id = gb.class_id\n" +
                "JOIN Rooms r ON gc.room_id = r.room_id\n" +
                "WHERE gc.trainer_email = '"+email+"';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            String class_id = rs.getString("class_id");
            addElement("class_id: "+class_id);
            String class_date = rs.getString("class_date");
            addElement("class_date: "+class_date);
            String class_time = rs.getString("class_time");
            addElement("class_time: "+class_time);
            String room_id = rs.getString("room_id");
            addElement("room_id: "+room_id);
            String building = rs.getString("building");
            addElement("building: "+building);
        }
        // Close resources
        rs.close();
        stmt.close();
    }

    /**
     * Retrieve the profile information (personal info, fitness goals & health metrics)
     * of the specified member by email
     * @param mem_email
     */
    public void searchMember(String mem_email) {
        this.clear();
        Member m = new Member(mem_email, conn); //create a Member object to retrieve info
        addElement(m.getMemberProfile()); //display the searched member's profile
    }
}
