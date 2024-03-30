import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminController implements ActionListener {
    private AdminStaff model; //the user function model
    private AdminView view; //the user view (GUI)

    public AdminController(AdminStaff model, AdminView view) {
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
                case "rooms":
                    view.displayListView();
                    model.getRooms();
                    break;
                case "equip":
                    view.displayListView();
                    model.getEquipment();
                    break;
                case "billing":
                    view.displayListView();
                    model.getBillings();
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
