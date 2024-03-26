import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** This class initializes the login view for UrHealth App and prompts
 * user for email address to retrieve user profile.
 * @author Areej Mahmoud
 */

public class LoginUI {
    private String userEmail;
    private UserViews selectedView;
    private Connection conn; //DB connection for email authentication
    public LoginUI(Connection conn){
        //set connection
        this.conn = conn;

        //create panel with buttons to select user type.
        String users[] = {"Member", "Trainer", "Admin Staff"};
        int selection = JOptionPane.showOptionDialog(null,
                "Choose your login portal: ", "Welcome to UrHealth App!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,
                users, users[0]);
        //set the selected view for the app based on user type
        if(selection==0){
            selectedView = UserViews.Members;
        }else if(selection==1){
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
