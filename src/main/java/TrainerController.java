import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
    }
}
