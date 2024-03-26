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
            if (command.equals("dashboard")) {
                model.updateDashboard();
                view.displayDashboard();
            } else if (command.equals("myInfo")) {
                view.displayListView();
                model.getPersonalInfo();
            } else if (command.equals("metrics")) {
                view.displayListView();
                model.getHealthMetrics();
            } else if (command.equals("goals")) {
                view.displayListView();
                model.getFitnessGoals();
            } else if (command.equals("personalBooking")) {
                view.displayListView();
                model.getPersonalBookings();
            } else if (command.equals("groupBooking")) {
                view.displayListView();
                model.getGroupBookings();
            } else if (command.equals("personalBrowse")) {
                view.displayListView();
                model.getAvailableSessions();
            } else if (command.equals("groupBrowse")) {
                view.displayListView();
                model.getAvailableClasses();
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
