import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }
}
