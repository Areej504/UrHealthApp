import javax.swing.*;
import java.awt.*;

public class MemberView extends JFrame{
    private Member user;
    private JList<String> listView;
    private JScrollPane listScrollPane;
    private JPanel homePanel ;

    public MemberView(Member user) {
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
        MemberController control = new MemberController(user, this);
        dashboard.setActionCommand("dashboard");
        dashboard.addActionListener(control);
        myInfo.setActionCommand("myInfo");
        myInfo.addActionListener(control);
        metrics.setActionCommand("metrics");
        metrics.addActionListener(control);
        goals.setActionCommand("goals");
        goals.addActionListener(control);
        personalBooking.setActionCommand("personalBooking");
        personalBooking.addActionListener(control);
        groupBooking.setActionCommand("groupBooking");
        groupBooking.addActionListener(control);
        personalBrowse.setActionCommand("personalBrowse");
        personalBrowse.addActionListener(control);
        groupBrowse.setActionCommand("groupBrowse");
        groupBrowse.addActionListener(control);

        //Set up the dashboard which is the first thing user sees
        setUpDashboard();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
    }
    //display the list pane to view user data
    public void displayListView(){
        homePanel.setVisible(false);
        this.add(listScrollPane, BorderLayout.CENTER);
        listScrollPane.setVisible(true);
        System.out.println("listview");
    }
    //display the list pane to view user data
    public void displayDashboard(){
        listScrollPane.setVisible(false);
        this.add(homePanel);
        homePanel.setVisible(true);
        System.out.println("dashboard");
    }

    //Set up dashboard design and feel
    // Display exercise routines, fitness achievements, health statistics
    private void setUpDashboard(){
        // Set panel layout
        homePanel.setLayout(new BorderLayout());

        // Create logo panel
        JPanel logoPanel = new JPanel();
        ImageIcon logoIcon = new ImageIcon("logo.PNG"); ///path to logo image
        JLabel logoLabel = new JLabel(logoIcon);
        logoPanel.add(logoLabel);
        homePanel.add(logoPanel, BorderLayout.NORTH);

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 3 rows, 1 column, with 10px padding
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around content

        // Add exercise routines
        JLabel exerciseLabel = new JLabel("Exercise Routines");
        exerciseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        contentPanel.add(exerciseLabel);
        //TODO: Get this info from the DB connection through model
        JTextArea exerciseTextArea = new JTextArea("Exercise routine 1\nExercise routine 2\nExercise routine 3");
        exerciseTextArea.setEditable(false);
        exerciseTextArea.setLineWrap(true);
        exerciseTextArea.setWrapStyleWord(true);
        JScrollPane exerciseScrollPane = new JScrollPane(exerciseTextArea);
        contentPanel.add(exerciseScrollPane);

        // Add fitness achievements
        JLabel achievementsLabel = new JLabel("Fitness Achievements");
        achievementsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        contentPanel.add(achievementsLabel);
        //TODO: Get this info from the DB connection through model
        JTextArea achievementsTextArea = new JTextArea("Achievement 1\nAchievement 2\nAchievement 3");
        achievementsTextArea.setEditable(false);
        achievementsTextArea.setLineWrap(true);
        achievementsTextArea.setWrapStyleWord(true);
        JScrollPane achievementsScrollPane = new JScrollPane(achievementsTextArea);
        contentPanel.add(achievementsScrollPane);

        // Add health statistics
        JLabel statisticsLabel = new JLabel("Health Statistics");
        statisticsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        contentPanel.add(statisticsLabel);
        //TODO: Get this info from the DB connection through model
        JTextArea statisticsTextArea = new JTextArea("Statistic 1: Value\nStatistic 2: Value\nStatistic 3: Value");
        statisticsTextArea.setEditable(false);
        statisticsTextArea.setLineWrap(true);
        statisticsTextArea.setWrapStyleWord(true);
        JScrollPane statisticsScrollPane = new JScrollPane(statisticsTextArea);
        contentPanel.add(statisticsScrollPane);

        contentPanel.setBackground(Color.orange); // Set background color to orange
        // Add content panel to main panel
        homePanel.add(contentPanel, BorderLayout.CENTER);
        this.add(homePanel);
        homePanel.setVisible(true);
    }
}


