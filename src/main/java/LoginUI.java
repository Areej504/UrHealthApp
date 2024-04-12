import javax.swing.*;
import java.awt.*;
import java.sql.*;

/** This class initializes the login view for UrHealth App and prompts
 * user for email address to autheticate and retrieve user profile.
 * @author Areej Mahmoud 101218260
 */

public class LoginUI {
    private String userEmail;
    private UserViews selectedView; //the login view selected by user
    private Connection conn; //DB connection for email authentication
    public LoginUI(Connection conn){
        //set connection
        this.conn = conn;

        //create panel with buttons to select user type.
        String users[] = {"Register","Member", "Trainer", "Admin Staff"};
        int selection = JOptionPane.showOptionDialog(null,
                "Register or choose your login portal: ", "Welcome to UrHealth App!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,
                users, users[0]);
        //set the selected view for the app based on user type
        if(selection==0){
            registerUI();
            selectedView = UserViews.Members;
            return;
        }else if(selection==1){
            selectedView = UserViews.Members;
        }else if(selection==2){
            selectedView = UserViews.Trainers;
        }else{
            selectedView = UserViews.Admin_Staff;
        }

        boolean validInput = false;
        while(!validInput) {
            //TODO: add password field and implement authentication
            JPanel panel = new JPanel();
            JLabel lbl = new JLabel("Enter your email address: ");
            JTextField txt = new JTextField(20);
            panel.add(lbl);
            panel.add(txt);
            //Prompt user for their email to authenticate (password functionality)
            int result = JOptionPane.showConfirmDialog(null, panel,"User Login",
                    JOptionPane.OK_CANCEL_OPTION);

            //when OK clicked, check input is not empty, and email exists
            if (result==JOptionPane.OK_OPTION) {
                String email = txt.getText();
                //if email field is empty, show error message
                if (email.isBlank() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a an email address!",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    validInput = false;
                } else if(!authenticateEmail(email)){
                    JOptionPane.showMessageDialog(null, "User not found! Enter a registered email address",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    validInput = false;
                }else {
                    userEmail = email; //set the user Email
                    validInput = true;
                }
            }else{
                //TODO: handle cancel being pressed to end program
                break; //if cancel is pressed, break loop
            }
        }
    }

    /**
     * display new member registration screen and prompt user to create an account
     * by entering their name and email.
     */
    private void registerUI(){
        //create JTextFields to collect input
        JTextField email = new JTextField(20);
        JTextField firstName = new JTextField(20);
        JTextField lastName = new JTextField(20);

        //add the input text fields and labels
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(3,0));
        myPanel.add(new JLabel("Email:"));
        myPanel.add(email);
        myPanel.add(new JLabel("First Name:"));
        myPanel.add(firstName);
        myPanel.add(new JLabel("Last Name:"));
        myPanel.add(lastName);

        //display JOptionPane and prompt user for input
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Welcome! Enter your details to register", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            //insert the new Member into the database
            addNewMember(email.getText(),firstName.getText(), lastName.getText());
            //set the member's email
            this.userEmail = email.getText();
        }
    }

    /**
     * Inserts a new member into the database.
     * @param email
     * @param first_name
     * @param last_name
     */
    private void addNewMember(String email, String first_name, String last_name){
        //insert the new members info and an null values for their health metrics
        String insertSQL = "INSERT INTO Members (email, first_name, last_name) VALUES (?, ?, ?);"+
                "INSERT INTO health_metrics (mem_email, blood_pressure, heart_rate, blood_sugar, weight)\n" +
                "VALUES (?, 0, 0, 0, 0);";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            pstmt.setString(2, first_name);
            pstmt.setString(3, last_name);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            System.out.println("Data inserted using PreparedStatement.");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    /**
     * Authenticate the email provided by user through the DB connection.
     */
    private boolean authenticateEmail(String email) {
        boolean exists = false; //set default to false
        try {
            // Create statement & query to check if email exists in selected view table
            Statement stmt = conn.createStatement(); // Create SQL query
            String SQL = "SELECT EXISTS (\n" +
                    "    SELECT 1\n" +
                    "    FROM "+getSelectedView()+"\n" +
                    "    WHERE email = '" + email + "'\n" +
                    ");\n";
            ResultSet rs = stmt.executeQuery(SQL); // Process the result set
            while (rs.next()) {
                exists = rs.getBoolean("exists");
                }
            // Close resources
            rs.close();
            stmt.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return exists;
    }
    /**
     * @return the email entered by the user
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @return the type of user view selected
     */
    public UserViews getSelectedView() {
        return selectedView;
    }
}
