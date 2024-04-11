import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberView extends JFrame{
    private Member user;
    private JList<String> list;
    private JScrollPane listScrollPane;
    private JPanel homePanel ;
    public String currFunction;

    public MemberView(Member user) {
        super("UrHealth App");
        this.user = user;
        currFunction = "";

        //create and add the JList to the frame
        list = new JList<>(user);
        listScrollPane =new JScrollPane(list);
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
        JMenuItem updateInfo = new JMenuItem("Update Personal Info");
        JMenuItem updateMetrics = new JMenuItem("Update Health Metrics");
        JMenuItem addGoal = new JMenuItem("Add Fitness Goals");
        JMenuItem personalBooking = new JMenuItem("Personal Sessions");
        JMenuItem groupBooking = new JMenuItem("Group Classes");
        JMenuItem personalBrowse = new JMenuItem("Personal Sessions");
        JMenuItem groupBrowse = new JMenuItem("Group Classes");

        //add menu items to menus
        homeMenu.add(dashboard);
        profileMenu.add(myInfo);
        profileMenu.add(updateInfo);
        profileMenu.add(metrics);
        profileMenu.add(updateMetrics);
        profileMenu.add(goals);
        profileMenu.add(addGoal);
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
        updateInfo.setActionCommand("updateInfo");
        updateInfo.addActionListener(control);
        updateMetrics.setActionCommand("updateMetrics");
        updateMetrics.addActionListener(control);
        addGoal.setActionCommand("addGoal");
        addGoal.addActionListener(control);
        personalBooking.setActionCommand("personalBooking");
        personalBooking.addActionListener(control);
        groupBooking.setActionCommand("groupBooking");
        groupBooking.addActionListener(control);
        personalBrowse.setActionCommand("personalBrowse");
        personalBrowse.addActionListener(control);
        groupBrowse.setActionCommand("groupBrowse");
        groupBrowse.addActionListener(control);

        //listen for mouse clicks on the JList to book/cancel classes
        setUpListListener();

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
    public void updateInfoDialog(){
        //create array of attributes to update
        String[] choices = {"email","first_name","last_name"};
        //create a dropdown list of choices and a text field for new value
        JComboBox dropdown = new JComboBox(choices);
        JTextField newValue = new JTextField(20);

        //add input fields to a panel
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.add(new JLabel("Attribute"));
        panel.add(dropdown);
        panel.add(new JLabel("New value"));
        panel.add(newValue);

        //display JOptionPane and prompt user for input
        int result = JOptionPane.showConfirmDialog(this, panel,
                "Update Personal Info", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            //update the members personal info with given input
            System.out.println((String) dropdown.getSelectedItem());
            System.out.println(newValue.getText());
            user.updatePersonalInfo((String) dropdown.getSelectedItem(), newValue.getText());
        }
    }
    public void updateMetricDialog(){
        //create array of attributes to update
        String[] choices = {"blood_pressure","heart_rate","blood_sugar", "weight"};
        //create a dropdown list of choices and a text field for new value
        JComboBox dropdown = new JComboBox(choices);
        JTextField newValue = new JTextField(20);

        //add input fields to a panel
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.add(new JLabel("Health Metric"));
        panel.add(dropdown);
        panel.add(new JLabel("New value"));
        panel.add(newValue);

        //display JOptionPane and prompt user for input
        int result = JOptionPane.showConfirmDialog(this, panel,
                "Update Health Metrics", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            //update the members health metrics with given input
            System.out.println((String) dropdown.getSelectedItem());
            System.out.println(newValue.getText());
            user.updateHealthMetric((String) dropdown.getSelectedItem(), newValue.getText());
        }
    }
    public void addGoalDialog(){
        //show the input dialog and get user input for a new fitness goal
        String input = JOptionPane.showInputDialog(this, "Enter a new fitness goal",
                "Add a fitness goal", JOptionPane.OK_OPTION);

        //add the new goal to DB
        user.addFitnessGoal(input);

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

    private void setUpListListener(){
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    switch (currFunction){
                        case "goals":
                            achieveGoalDialog(index);
                            break;
                        case "personalBooking":
                        case "groupBooking":
                            cancelBookingDialog(index);
                            break;
                        case "personalBrowse":
                        case "groupBrowse":
                            confirmBookingDialog(index);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    private void cancelBookingDialog(int index){
        //Confirm the booking cancellation
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you want to cancel this booking?","Booking Management", JOptionPane.YES_NO_OPTION);
        if(choice==JOptionPane.YES_OPTION) {
            if(currFunction == "personalBooking"){
                user.cancelPersonalBooking(index);
            }else if(currFunction == "groupBooking"){
                user.cancelGroupBooking(index);
            }
        }
    }
    private void confirmBookingDialog(int index){
        //display confirm booking message
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you want to book this session/class?","Confirm Booking", JOptionPane.YES_NO_OPTION);
        if(choice==JOptionPane.YES_OPTION) {
            billingDialog(index); //display the billing dialog to add billing
        }
    }

    private void achieveGoalDialog(int index) {
        //Achieve fitness goal
        int choice = JOptionPane.showConfirmDialog(this,
                "Has this fitness goal been achieved?","New Achievement", JOptionPane.YES_NO_OPTION);
        if(choice==JOptionPane.YES_OPTION) {
            user.achieveFitnessGoal(index);
        }
    }

    private void billingDialog(int index){
        //display confirm Billing information message
        int choice = JOptionPane.showConfirmDialog(this,
                "Booking: personal session or group class\n" +
                        "Amount: $15\n"+"Press OK to proceed to third-party payment service"
                ,"BILLING", JOptionPane.OK_CANCEL_OPTION);
        if(choice==JOptionPane.OK_OPTION) {
            //assume integration with payment service to complete billing
            user.createBilling();
            //add this booking to DB
            if(currFunction == "personalBrowse"){
                user.addPersonalBooking(index);
            }else if(currFunction == "groupBrowse"){
                user.addGroupBooking(index);
            }
        }
    }
}


