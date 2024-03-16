import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private User currUser;
    private JFrame view;

    public Controller(User currUser, JFrame view) {
        this.currUser = currUser;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
