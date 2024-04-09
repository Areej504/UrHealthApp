import java.sql.*;
import java.time.LocalDate;

public class Member extends User{

    public Member(String email, Connection conn) {
        super(email, conn);
    }

    public void loadDashboard() {
    }

    public void updatePersonalInfo(){

    }
    public void updateHealthMetrics(){

    }
    public void updateFitnessGoals(){

    }
    public void achieveFitnessGoal(int index){
        //Add the selected fitness goal to achievements and delete it from existing fitness goals
        String goal = ((String) getElementAt(index));
        String insertSQL = "INSERT INTO achievement (mem_email, achievement) VALUES (?, ?);\n"
                +"DELETE FROM fitness_goals WHERE mem_email = ? AND goal = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            pstmt.setString(2, goal);
            pstmt.setString(3, email);
            pstmt.setString(4, goal);
            pstmt.executeUpdate();
            System.out.println("Data inserted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void addPersonalBooking(int index){
        int session_id = parseSessionId((String) getElementAt(index)); //parse session id from selected list element
        String insertSQL = "INSERT INTO personal_bookings (session_id, mem_email) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, session_id);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Data inserted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void addGroupBooking(int index){
        int class_id = parseSessionId((String) getElementAt(index));
        String insertSQL = "INSERT INTO group_bookings (class_id, mem_email) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, class_id);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Data inserted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void cancelPersonalBooking(int index){
        int session_id = parseSessionId((String) getElementAt(index));
        String deleteSQL = "DELETE FROM personal_bookings\n" +
                "WHERE session_id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, session_id);
            pstmt.executeUpdate();
            System.out.println("Data deleted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void cancelGroupBooking(int index){
        int class_id = parseSessionId((String) getElementAt(index));
        String deleteSQL = "DELETE FROM group_bookings\n" +
                "WHERE class_id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, class_id);
            pstmt.executeUpdate();
            System.out.println("Data deleted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void createBilling(){
        String insertSQL = "INSERT INTO billings (mem_email, amount, date) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            pstmt.setInt(2, 15); //default price per session is $15
            Date today = Date.valueOf(LocalDate.now());
            pstmt.setDate(3, today); //set today's date as the billing date
            pstmt.executeUpdate();
            System.out.println("Data inserted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void getHealthMetrics() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        String SQL = "SELECT *\n" +
                "FROM Health_metrics\n" +
                "WHERE mem_email = '" + email + "';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            String blood_pressure = rs.getString("blood_pressure");
            addElement("blood_pressure: "+blood_pressure);
            String heart_rate = rs.getString("heart_rate");
            addElement("heart_rate: "+heart_rate);
            String blood_sugar = rs.getString("blood_sugar");
            addElement("blood_sugar: "+blood_sugar);
            String weight = rs.getString("weight");
            addElement("weight: "+weight);
        }
        // Close resources
        rs.close();
        stmt.close();
    }

    public void getFitnessGoals() throws  SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        String SQL = "SELECT *\n" +
                "FROM Fitness_goals\n" +
                "WHERE mem_email = '" + email + "';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            String goal = rs.getString("goal");
            addElement(goal);
        }
        // Close resources
        rs.close();
        stmt.close();
    }

    public void getPersonalBookings() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        //get all personal sessions booked by this user and include the room info for each
        String SQL = "SELECT ps.session_id, ps.trainer_email, ps.session_date, ps.session_time, ps.room_id, r.building\n" +
                "FROM Personal_sessions ps\n" +
                "JOIN Personal_bookings pb ON ps.session_id = pb.session_id\n" +
                "JOIN Rooms r ON ps.room_id = r.room_id\n" +
                "WHERE pb.mem_email = '"+email+"';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            StringBuilder sb = new StringBuilder("<html>");
            String session_id = rs.getString("session_id");
            String trainer_email = rs.getString("trainer_email");
            String session_date = rs.getString("session_date");
            String session_time = rs.getString("session_time");
            String room_id = rs.getString("room_id");
            String building = rs.getString("building");
            sb.append("session_id: "+session_id);
            sb.append("<br>trainer_email: "+trainer_email);
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

    public void getGroupBookings() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        //get all group classes booked by this user and include the room info for each
        String SQL = "SELECT gc.class_id, gc.trainer_email, gc.class_date, gc.class_time, gc.room_id, r.building\n" +
                "FROM Group_classes gc\n" +
                "JOIN Group_bookings gb ON gc.class_id = gb.class_id\n" +
                "JOIN Rooms r ON gc.room_id = r.room_id\n" +
                "WHERE gb.mem_email = '"+email+"';";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result set
        while(rs.next()){
            StringBuilder sb = new StringBuilder("<html>");
            String class_id = rs.getString("class_id");
            String trainer_email = rs.getString("trainer_email");
            String class_date = rs.getString("class_date");
            String class_time = rs.getString("class_time");
            String room_id = rs.getString("room_id");
            String building = rs.getString("building");
            sb.append("class_id: "+class_id);
            sb.append("<br>trainer_email: "+trainer_email);
            sb.append("<br>class_date: "+class_date);
            sb.append("<br>class_time: "+class_time);
            sb.append("<br>room_id: "+room_id);
            sb.append("<br>building: "+building);
            sb.append("</html>");
            addElement(sb.toString());
        }
        // Close resources
        rs.close();
        stmt.close();
    }

    public void getAvailableSessions() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        //get all personal sessions booked by this user and include the room info for each
        String SQL = "SELECT ps.*\n" +
                "FROM Personal_sessions ps\n" +
                "LEFT JOIN Personal_bookings pb ON ps.session_id = pb.session_id\n" +
                "WHERE pb.session_id IS NULL;";
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

    public void getAvailableClasses() throws SQLException{
        this.clear(); //clear list to retrieve new info and display it

        // Create statement
        Statement stmt = conn.createStatement(); // Execute SQL query
        //get all group classes NOT booked yet
        String SQL = "SELECT gc.*\n" +
                "FROM Group_classes gc\n" +
                "LEFT JOIN Group_bookings gb ON gc.class_id = gb.class_id\n" +
                "WHERE gb.class_id IS NULL;";
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

    /**
     * Compiles member profile attributes into a string to be displayed.
     * @return A string of personal info, fitness goals and health metrics
     */
    public String getMemberProfile(){
        StringBuilder sb = new StringBuilder("<html>");
        try{
            getPersonalInfo(); //retrieve personal info and add to listModel
        }catch(SQLException e){e.printStackTrace();}
        sb.append("--Personal Info--<br>");
        sb.append(this); //append the personal info retrieved
        try{
            getFitnessGoals(); //retrieve fitness goals and add to listModel
        }catch(SQLException e){e.printStackTrace();}
        sb.append("<br>--Fitness Goals--<br>");
        sb.append(this); //append the fitness goals retrieved
        try{
            getHealthMetrics(); //retrieve health metrics and add to listModel
        }catch(SQLException e){e.printStackTrace();}
        sb.append("<br>--Health Metrics--<br>");
        sb.append(this); //append the health metrics retrieved
        sb.append("</html>");

        //return the string builder as a string
        return sb.toString();
    }
}
