import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Member extends User{

    public Member(String email, Connection conn) {
        super(email, conn);
    }

    public void loadDashboard() {
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
            String weight = rs.getString("weight");
            addElement("weight: "+weight);
            String running_time = rs.getString("running_time");
            addElement("running_time: "+running_time);
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
            String session_id = rs.getString("session_id");
            addElement("session_id: "+session_id);
            String trainer_email = rs.getString("trainer_email");
            addElement("trainer_email: "+trainer_email);
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
            String class_id = rs.getString("class_id");
            addElement("class_id: "+class_id);
            String trainer_email = rs.getString("trainer_email");
            addElement("trainer_email: "+trainer_email);
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
            String session_id = rs.getString("session_id");
            addElement("session_id: "+session_id);
            String trainer_email = rs.getString("trainer_email");
            addElement("trainer_email: "+trainer_email);
            String session_date = rs.getString("session_date");
            addElement("session_date: "+session_date);
            String session_time = rs.getString("session_time");
            addElement("session_time: "+session_time);
            String room_id = rs.getString("room_id");
            addElement("room_id: "+room_id);
            System.out.println(getElementAt(1));
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
            String class_id = rs.getString("class_id");
            addElement("class_id: "+class_id);
            String trainer_email = rs.getString("trainer_email");
            addElement("trainer_email: "+trainer_email);
            String class_date = rs.getString("class_date");
            addElement("class_date: "+class_date);
            String class_time = rs.getString("class_time");
            addElement("class_time: "+class_time);
            String room_id = rs.getString("room_id");
            addElement("room_id: "+room_id);
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
        sb.append("--Personal Info--<br>\n");
        sb.append(this); //append the personal info retrieved
        try{
            getFitnessGoals(); //retrieve fitness goals and add to listModel
        }catch(SQLException e){e.printStackTrace();}
        sb.append("\n<br>--Fitness Goals--<br>\n");
        sb.append(this); //append the fitness goals retrieved
        try{
            getHealthMetrics(); //retrieve health metrics and add to listModel
        }catch(SQLException e){e.printStackTrace();}
        sb.append("\n<br>--Health Metrics--<br>\n");
        sb.append(this); //append the health metrics retrieved
        sb.append("</html>");

        //return the string builder as a string
        return sb.toString();
    }
}
