import javax.swing.*;
import java.awt.*;

public class TrainerView extends JFrame{
    private Trainer user;
    private JList<String> listView;
    private JScrollPane listPane;
    private JPanel homePanel;
    public TrainerView(Trainer user) {
        super("UrHealth App");
        this.user = user;

        //create listView ScrollPane
        listPane =new JScrollPane(listView);
        //this.add(listPane, BorderLayout.CENTER);
        listPane.setVisible(false);

        //create the JMenuBar, JMenu and JMenu Items
        JMenuBar menuBar = new JMenuBar();
        JMenu homeMenu = new JMenu("Home");
        JMenu profileMenu = new JMenu("Profile");
        JMenu scheduleMenu = new JMenu("My Schedule");
        JMenu searchMenu = new JMenu("Search");
        JMenuItem myInfo = new JMenuItem("My Info");
        JMenuItem addSessions = new JMenuItem("Add Session");
        JMenuItem mySessions = new JMenuItem("My Sessions");
        JMenuItem search = new JMenuItem("Search Member");

        //add menu items to menus
        profileMenu.add(myInfo);
        scheduleMenu.add(addSessions);
        scheduleMenu.add(mySessions);
        searchMenu.add(searchMenu);

        //add menus to menu bar
        menuBar.add(homeMenu);
        menuBar.add(profileMenu);
        menuBar.add(scheduleMenu);
        menuBar.add(searchMenu);
        // adds menu bar to the frame
        this.setJMenuBar(menuBar);

        //listen for menu selections
        TrainerController control = new TrainerController(user, this);
        homeMenu.setActionCommand("home");
        homeMenu.addActionListener(control);
        myInfo.setActionCommand("myInfo");
        myInfo.addActionListener(control);
        addSessions.setActionCommand("addSession");
        addSessions.addActionListener(control);
        mySessions.setActionCommand("mySessions");
        mySessions.addActionListener(control);
        search.setActionCommand("search");
        search.addActionListener(control);

        //Set up home screen which is the first thing user sees
        //setUpHomeScreen();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
    }

    private void setUpHomeScreen(){
        //trainer home screen only contains logo and welcome msgs
        homePanel = new JPanel(new BorderLayout());
    }
}
