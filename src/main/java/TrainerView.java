import javax.swing.*;
import java.sql.Connection;

public class TrainerView extends JFrame{
    private User user;

    public TrainerView(User user) {
        super("UrHealth App");
        this.user = user;

        //create the JMenuBar, JMenu and JMenu Items
        JMenuBar menuBar = new JMenuBar();
        JMenu homeMenu = new JMenu("Home");
        JMenu profileMenu = new JMenu("Profile");
        JMenu scheduleMenu = new JMenu("My Schedule");
        JMenu searchMenu = new JMenu("Search");
        JMenuItem dashboard = new JMenuItem("Dashboard");
        JMenuItem myInfo = new JMenuItem("My Info");
        JMenuItem addSessions = new JMenuItem("Add Session");
        JMenuItem mySessions = new JMenuItem("My Sessions");
        JMenuItem search = new JMenuItem("Search Member");

        //add menu items to menus
        homeMenu.add(dashboard);
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
        Controller control = new Controller(user, this);
        dashboard.setActionCommand("dashboard");
        dashboard.addActionListener(control);
        myInfo.setActionCommand("myInfo");
        myInfo.addActionListener(control);
        addSessions.setActionCommand("addSession");
        addSessions.addActionListener(control);
        mySessions.setActionCommand("mySessions");
        mySessions.addActionListener(control);
        search.setActionCommand("search");
        search.addActionListener(control);

        //Set up the dashboard which is the first thing user sees
        //setUpDashboard();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
    }

    private void setUpDashboard(){
        //trainer dashboard only contains logo and welcome msg

    }

    public static void main(String[] args) {new MemberView(new User(10));}
}
