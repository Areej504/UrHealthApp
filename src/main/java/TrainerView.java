import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        JMenuItem addSession = new JMenuItem("Add Personal Session");
        JMenuItem bookedSessions = new JMenuItem("Booked Sessions");
        JMenuItem addClass = new JMenuItem("Add Group Class");
        JMenuItem bookedClasses = new JMenuItem("Booked Classes");
        JMenuItem search = new JMenuItem("Search Member");

        //add menu items to menus
        homeMenu.add(dashboard);
        profileMenu.add(myInfo);
        scheduleMenu.add(addSession);
        scheduleMenu.add(bookedSessions);
        scheduleMenu.add(addClass);
        scheduleMenu.add(bookedClasses);
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
        addSession.setActionCommand("addSession");
        addSession.addActionListener(control);
        bookedSessions.setActionCommand("bookedSessions");
        bookedSessions.addActionListener(control);
        addClass.setActionCommand("addClass");
        addClass.addActionListener(control);
        bookedClasses.setActionCommand("bookedClasses");
        bookedClasses.addActionListener(control);
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

    public ArrayList<String> getNewSessionInput() {
        //create JTextFields to collect input
        JTextField dateField = new JTextField("YYYY-MM-DD",10);
        JTextField timeField = new JTextField("HH-MI",5);
        JTextField roomField = new JTextField(2);

        //add the input text fields and labels
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(3,0));
        myPanel.add(new JLabel("Date:"));
        myPanel.add(dateField);
        myPanel.add(new JLabel("Time:"));
        myPanel.add(timeField);
        myPanel.add(new JLabel("Room id:"));
        myPanel.add(roomField);

        //display JOptionPane and prompt user for input
        int result = JOptionPane.showConfirmDialog(this, myPanel,
                "Please enter details", JOptionPane.OK_CANCEL_OPTION);

        //retrieve and store user input to return
        ArrayList<String> inputList = new ArrayList<>();
        if (result == JOptionPane.OK_OPTION) {
            inputList.add(dateField.getText());
            inputList.add(timeField.getText());
            inputList.add(roomField.getText());
        }
        return inputList;
    }

    public String getSearchInput() {
        String input=JOptionPane.showInputDialog(this,"Enter member email: ","Search Member Profiles", JOptionPane.QUESTION_MESSAGE);
        return input;
    }
}
