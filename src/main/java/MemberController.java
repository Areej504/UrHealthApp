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
        view.displayListView();
        try {
            if (command.equals("dashboard")) {
                model.updateDashboard();
                view.displayDashboard();
            } else if (command.equals("myInfo")) {
                model.getPersonalInfo();
            } else if (command.equals("metrics")) {
                model.getHealthMetrics();
            } else if (command.equals("goals")) {
                model.getFitnessGoals();
            } else if (command.equals("personalBooking")) {
                model.getPersonalBookings();
            } else if (command.equals("groupBooking")) {
                model.getGroupBookings();
            } else if (command.equals("personalBrowse")) {
                model.getAvailableSessions();
            } else if (command.equals("groupBrowse")) {
                model.getAvailableClasses();
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
