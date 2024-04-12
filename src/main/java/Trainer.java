import java.sql.*;

public class Trainer extends User{
    public Trainer(String email, Connection conn) {
        super(email, conn);
    }

    public void addSession(String routine,String session_date,String session_time,String room_id){
        String insertSQL = "INSERT INTO personal_sessions (trainer_email, routine, session_date, session_time, room_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            pstmt.setString(2, routine);
            Date date = Date.valueOf(session_date);//converting string into sql date type
            pstmt.setDate(3, date);
            Time time = Time.valueOf(session_time+":00");//converting string into sql time type
            pstmt.setTime(4, time);
            pstmt.setInt(5, Integer.parseInt(room_id));
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
        String SQL = "SELECT ps.session_id, pb.mem_email, ps.routine, ps.session_date, ps.session_time, ps.room_id, r.building\n" +
                "FROM Personal_sessions ps\n" +
                "JOIN Personal_bookings pb ON ps.session_id = pb.session_id\n" +
                "JOIN Rooms r ON ps.room_id = r.room_id\n"+
                "WHERE ps.trainer_email = '"+email+"';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        StringBuilder sb = new StringBuilder("<html>");
        while(rs.next()){
            String session_id = rs.getString("session_id");
            String mem_email = rs.getString("mem_email");
            String routine = rs.getString("routine");
            String session_date = rs.getString("session_date");
            String session_time = rs.getString("session_time");
            String room_id = rs.getString("room_id");
            String building = rs.getString("building");
            sb.append("session_id: "+session_id);
            sb.append("<br>mem_email: "+mem_email);
            sb.append("<br>routine: "+routine);
            sb.append("<br>session_date: "+session_date);
            sb.append("<br>session_time: "+session_time);
            sb.append("<br>room_id: "+room_id);
            sb.append("<br>building: "+building);
            sb.append("</html>");
            addElement(sb.toString());
        }
        // Close resources
        rs.close();
        stmt.close();
    }
    public void addClass(String routine, String class_date,String class_time,String room_id){
        String insertSQL = "INSERT INTO group_classes (trainer_email, routine, class_date, class_time, room_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            pstmt.setString(2, routine);
            Date date = Date.valueOf(class_date);//converting string into sql date type
            pstmt.setDate(3, date);
            Time time = Time.valueOf(class_time+":00");//converting string into sql time type
            pstmt.setTime(4, time);
            pstmt.setInt(5, Integer.parseInt(room_id));
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
        //get all group classes booked with this trainer and include the room info for each
        // and the number of members who booked this class
        String SQL = "SELECT gc.class_id,\n" +
                "       gc.routine,\n" +
                "       gc.class_date,\n" +
                "       gc.class_time,\n" +
                "       r.room_id,\n" +
                "       r.building,\n" +
                "       COUNT(*) AS num_bookings\n" +
                "FROM Group_classes gc\n" +
                "JOIN Group_bookings gb ON gc.class_id = gb.class_id\n" +
                "JOIN Rooms r ON gc.room_id = r.room_id\n" +
                "WHERE gc.trainer_email = '"+email+"'\n" +
                "GROUP BY gc.class_id, gc.routine, gc.class_date, gc.class_time, r.room_id, r.building;\n";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        StringBuilder sb = new StringBuilder("<html>");
        while(rs.next()){
            String class_id = rs.getString("class_id");
            String routine = rs.getString("routine");
            String class_date = rs.getString("class_date");
            String class_time = rs.getString("class_time");
            String room_id = rs.getString("room_id");
            String building = rs.getString("building");
            String count = rs.getString("num_bookings");
            sb.append("class_id: "+class_id);
            sb.append("<br>routine: "+routine);
            sb.append("<br>class_date: "+class_date);
            sb.append("<br>class_time: "+class_time);
            sb.append("<br>room_id: "+room_id);
            sb.append("<br>building: "+building);
            sb.append("<br>num_bookings: "+count);
            sb.append("</html>");
            addElement(sb.toString());
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
