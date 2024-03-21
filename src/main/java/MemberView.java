import javax.swing.*;
public class MemberView extends JFrame{
    private User user;
    private JList<String> listView;
    private JScrollPane listPane;
    private JPanel homePanel;

    public MemberView(User user) {
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
        JMenu bookingMenu = new JMenu("My Bookings");
        JMenu browseMenu = new JMenu("Browse");
        JMenuItem dashboard = new JMenuItem("Dashboard");
        JMenuItem myInfo = new JMenuItem("My Info");
        JMenuItem metrics = new JMenuItem("Health Metrics");
        JMenuItem goals = new JMenuItem("Fitness Goals");
        JMenuItem personalBooking = new JMenuItem("Personal Sessions");
        JMenuItem groupBooking = new JMenuItem("Group Classes");
        JMenuItem personalBrowse = new JMenuItem("Personal Sessions");
        JMenuItem groupBrowse = new JMenuItem("Group Classes");

        //add menu items to menus
        homeMenu.add(dashboard);
        profileMenu.add(myInfo);
        profileMenu.add(metrics);
        profileMenu.add(goals);
        bookingMenu.add(personalBooking);
        bookingMenu.add(groupBooking);
        browseMenu.add(personalBrowse);
        browseMenu.add(groupBrowse);

        //add menus to menu bar
        menuBar.add(homeMenu);
        menuBar.add(profileMenu);
        menuBar.add(bookingMenu);
        menuBar.add(browseMenu);
        // adds menu bar to the frame
        this.setJMenuBar(menuBar);

        //listen for menu selections
        Controller control = new Controller(user, this);
        dashboard.setActionCommand("dashboard");
        dashboard.addActionListener(control);
        myInfo.setActionCommand("myInfo");
        myInfo.addActionListener(control);
        metrics.setActionCommand("metrics");
        metrics.addActionListener(control);
        goals.setActionCommand("goals");
        goals.addActionListener(control);
        personalBooking.setActionCommand(" personalBooking");
        personalBooking.addActionListener(control);
        groupBooking.setActionCommand("groupBooking");
        groupBooking.addActionListener(control);
        personalBrowse.setActionCommand("personalBrowse");
        personalBrowse.addActionListener(control);
        groupBrowse.setActionCommand("groupBrowse");
        groupBrowse.addActionListener(control);

        //Set up the dashboard which is the first thing user sees
        //setUpDashboard();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
    }

    private void setUpDashboard(){
        //Display exercise routines, fitness achievements, health statistics
        //For now, these cannot be updated except manually through DB

    }

    public static void main(String[] args) {new MemberView(new User());}
}


