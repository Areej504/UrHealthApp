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
        view.currFunction = command; //save the clicked menu's command to the view
        try {
            switch (command) {
                case "dashboard":
                    view.displayDashboard();
                    break;
                case "myInfo":
                    view.displayListView();
                    model.getPersonalInfo();
                    break;
                case "updateInfo":
                    view.updateInfoDialog();
                    break;
                case "metrics":
                    view.displayListView();
                    model.getHealthMetrics();
                    break;
                case "updateMetrics":
                    view.updateMetricDialog();
                    break;
                case "goals":
                    view.displayListView();
                    model.getFitnessGoals();
                    break;
                case "addGoal":
                    view.addGoalDialog();
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
