import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainerController implements ActionListener {
    private Trainer model; //the user function model
    private TrainerView view; //the user view (GUI)

    public TrainerController(Trainer model, TrainerView view) {
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
                    view.displayDashboard();
                    break;
                case "myInfo":
                    view.displayListView();
                    model.getPersonalInfo();
                    break;
                case "addSession":
                    view.displayListView();
                    ArrayList<String> in = view.getNewSessionInput();
                    model.addSession(in.get(0), in.get(1), in.get(2), in.get(3));
                    break;
                case "bookedSessions":
                    view.displayListView();
                    model.getBookedSessions();
                    break;
                case "addClass":
                    view.displayListView();
                    ArrayList<String> l = view.getNewSessionInput();
                    model.addClass(l.get(0), l.get(1), l.get(2), l.get(3));
                    break;
                case "bookedClasses":
                    view.displayListView();
                    model.getBookedClasses();
                    break;
                case "search":
                    view.displayListView();
                    String mem_email = view.getSearchInput();
                    model.searchMember(mem_email);
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
