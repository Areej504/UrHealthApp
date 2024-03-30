import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MemberController implements ActionListener {
    private Member model; //the user function model
    private MemberView view; //the user view (GUI)

    public MemberController(Member model, MemberView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //action commands for when a Menu/MenuItem is pressed
        //call model class functions to retrieve data
        String command = e.getActionCommand();
        try {
            switch (command) {
                case "dashboard":
                    model.updateDashboard();
                    view.displayDashboard();
                    break;
                case "myInfo":
                    view.displayListView();
                    model.getPersonalInfo();
                    break;
                case "metrics":
                    view.displayListView();
                    model.getHealthMetrics();
                    break;
                case "goals":
                    view.displayListView();
                    model.getFitnessGoals();
                    break;
                case "personalBooking":
                    view.displayListView();
                    model.getPersonalBookings();
                    break;
                case "groupBooking":
                    view.displayListView();
                    model.getGroupBookings();
                    break;
                case "personalBrowse":
                    view.displayListView();
                    model.getAvailableSessions();
                    break;
                case "groupBrowse":
                    view.displayListView();
                    model.getAvailableClasses();
                    break;
                default:
                    System.out.println("Unknown Cmd");// Handle unknown command
                    break;
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
