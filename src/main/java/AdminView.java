import javax.swing.*;

public class AdminView extends JFrame{
    private User user;
    private JList<String> listView;
    private JScrollPane listPane;
    private JPanel homePanel;
    public AdminView(User user) {
        super("UrHealth App");
        this.user = user;

        //create listView ScrollPane
        listPane =new JScrollPane(listView);
        //this.add(listPane, BorderLayout.CENTER);
        listPane.setVisible(false);

        //create the JMenuBar, JMenu and JMenu Items
        JMenuBar menuBar = new JMenuBar();
        JMenu homeMenu = new JMenu("Home");
        JMenu roomsMenu = new JMenu("Rooms");
        JMenu equipMenu = new JMenu("Equipment");
        JMenu billingMenu = new JMenu("Billings");

        //add menus to menu bar
        menuBar.add(homeMenu);
        menuBar.add(roomsMenu);
        menuBar.add(equipMenu);
        menuBar.add(billingMenu);
        // adds menu bar to the frame
        this.setJMenuBar(menuBar);

        //listen for menu selections
        Controller control = new Controller(user, this);
        homeMenu.setActionCommand("home");
        homeMenu.addActionListener(control);
        roomsMenu.setActionCommand("rooms");
        roomsMenu.addActionListener(control);
        equipMenu.setActionCommand("equip");
        equipMenu.addActionListener(control);
        billingMenu.setActionCommand("billing");
        billingMenu.addActionListener(control);

        //Set up the home screen which is the first thing user sees
        //setUpHomeScreen()

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
    }

    private void setUpHomeScreen(){
        //Admin home screen only contains logo and welcome msg

    }

    public static void main(String[] args) {new MemberView(new User());}
}
