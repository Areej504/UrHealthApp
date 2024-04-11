import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame{
    private AdminStaff user;
    private JList<String> listView;
    private JScrollPane listScrollPane;
    private JPanel homePanel;
    public AdminView(AdminStaff user) {
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
        JMenu manageMenu = new JMenu("Manage");
        JMenu schedule = new JMenu("Schedule");
        JMenuItem roomsMenu = new JMenuItem("Rooms");
        JMenuItem equipMenu = new JMenuItem("Equipment");
        JMenuItem billingMenu = new JMenuItem("Billings");
        JMenuItem personalSchedule= new JMenuItem("Personal Sessions");
        JMenuItem groupSchedule = new JMenuItem("Group Classes");

        //add menus to menu bar
        homeMenu.add(dashboard);
        menuBar.add(homeMenu);
        manageMenu.add(roomsMenu);
        manageMenu.add(equipMenu);
        manageMenu.add(billingMenu);
        menuBar.add(manageMenu);
        schedule.add(personalSchedule);
        schedule.add(groupSchedule);
        menuBar.add(schedule);
        // adds menu bar to the frame
        this.setJMenuBar(menuBar);

        //listen for menu selections
        AdminController control = new AdminController(user, this);
        dashboard.setActionCommand("dashboard");
        dashboard.addActionListener(control);
        personalSchedule.setActionCommand("personal");
        personalSchedule.addActionListener(control);
        groupSchedule.setActionCommand("group");
        groupSchedule.addActionListener(control);
        roomsMenu.setActionCommand("rooms");
        roomsMenu.addActionListener(control);
        equipMenu.setActionCommand("equip");
        equipMenu.addActionListener(control);
        billingMenu.setActionCommand("billing");
        billingMenu.addActionListener(control);

        //Set up the home screen which is the first thing user sees
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
