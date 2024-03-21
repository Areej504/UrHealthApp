import javax.swing.*;
import java.util.concurrent.CancellationException;

/** This class initializes the login view for UrHealth App and prompts
 * user for email address to retrieve user profile.
 * @author Areej Mahmoud
 */

public class LoginUI {
    private String userEmail;
    private UserViews selectedView;
    public LoginUI(){
        //create panel with buttons to select user type.
        String users[] = {"Member", "Trainer", "Admin Staff"};
        int selection = JOptionPane.showOptionDialog(null,
                "I am a: ", "Welcome to UrHealth App!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,
                users, users[0]);
        //set the selected view for the app based on user type
        if(selection==0){
            selectedView = UserViews.Member;
        }else if(selection==1){
            selectedView = UserViews.Trainer;
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

            //when OK clicked, check input is not empty
            if (result==JOptionPane.OK_OPTION) {
                String email = txt.getText();
                //if email field is empty, show error message
                if (email.isBlank() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a an email address!",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    validInput = false;
                } else {
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

    public static void main(String[] args) {
        new LoginUI();
    }
}
