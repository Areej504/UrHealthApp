import javax.swing.*;
import java.awt.*;

public class TrainerView extends JFrame{
    private Trainer user;
    private JList<String> listView;
    private JScrollPane listScrollPane;
    private JPanel homePanel;
    public TrainerView(Trainer user) {
        super("UrHealth App");
        this.user = user;

        //create and add the JList to the frame
        listView = new JList<>(user);
        listScrollPane =new JScrollPane(listView);
        this.add(listScrollPane, BorderLayout.CENTER);

        homePanel = new JPanel();

        //create the JMenuBar, JMenu and JMenu Items
        JMenuBar menuBar = new JMenuBar();
        JMenu homeMenu = new JMenu("Home");
        JMenuItem dashboard = new JMenuItem("Dashboard");
        JMenu profileMenu = new JMenu("Profile");
        JMenu scheduleMenu = new JMenu("My Schedule");
        JMenu searchMenu = new JMenu("Search");
        JMenuItem myInfo = new JMenuItem("My Info");
        JMenuItem addSessions = new JMenuItem("Add Session");
        JMenuItem mySessions = new JMenuItem("My Sessions");
        JMenuItem search = new JMenuItem("Search Member");

        //add menu items to menus
        homeMenu.add(dashboard);
        profileMenu.add(myInfo);
        scheduleMenu.add(addSessions);
        scheduleMenu.add(mySessions);
        searchMenu.add(search);

        //add menus to menu bar
        menuBar.add(homeMenu);
        menuBar.add(profileMenu);
        menuBar.add(scheduleMenu);
        menuBar.add(searchMenu);
        // adds menu bar to the frame
        this.setJMenuBar(menuBar);

        //listen for menu selections
        TrainerController control = new TrainerController(user, this);
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

        //Set up home screen which is the first thing user sees
        setUpHomeScreen();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
    }
    //display the list pane to view user data
    public void displayListView(){
        homePanel.setVisible(false);
        this.add(listScrollPane, BorderLayout.CENTER);
        listScrollPane.setVisible(true);
    }
    //display the list pane to view user data
    public void displayDashboard(){
        listScrollPane.setVisible(false);
        this.add(homePanel);
        homePanel.setVisible(true);
    }

    private void setUpHomeScreen(){
        //trainer home screen only contains logo and welcome msgs
        // Set panel layout
        homePanel.setLayout(new BorderLayout());

        // Create logo panel
        JPanel logoPanel = new JPanel();
        ImageIcon logoIcon = new ImageIcon("logo.PNG"); ///path to logo image
        JLabel logoLabel = new JLabel(logoIcon);
        logoPanel.add(logoLabel);
        homePanel.add(logoPanel, BorderLayout.NORTH);

        this.setBackground(Color.orange); // Set background color to orange
        this.add(homePanel);
        homePanel.setVisible(true);
    }
}
