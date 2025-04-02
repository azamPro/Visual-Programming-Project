package auth;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import auth.Events;
import auth.EventsDataBase;

public class HomePage extends JFrame implements ActionListener {
    // Menu and main components
    private JButton Home_Button, Tickets_Button, Event_Management_Button, Profile_Button, Notification_Button, Text_Search_Button; // menu
    private JButton Create_Event_Button, Combo_Search_Button;
    private JPanel menuPanel, Tickets_Panel, contentPanel, Event_Management_Panel, Profile_Panel, Notification_Panel, mainPanel, eventsPanel;
    private JComboBox Date, Category, Location;
    private JLabel Password_Label, User_Name_Label, Email_Label, Title_Label, Tickets_Label, Event_Management_Label, Reg_Events_Label, Your_Events_Label, Profile_Label, Notification_Label;
    private JTextField Search_Field;
    private String[] c1 = { "date" };
    private String[] c2 = { "Category" };
    private String[] c3 = { "Location" };

    int simpleEventY = 285; // where to place next event label

    // Notification components as class members
    private JLabel iconLabel, titleLabel, descriptionLabel, timeLabel;
    private String[] notifTitleArr = {"New Notification"};
    private String[] notifDescArr = {"You have a new message waiting in your inbox. Click to read more."};
    private Date[] notifTimeArr = {
        new Date(System.currentTimeMillis() - 3600000),   // 1 hour ago
        new Date(System.currentTimeMillis() - 7200000),   // 2 hours ago
        new Date(System.currentTimeMillis() - 86400000),  // 1 day ago
        new Date()                                        // current time
    };

    public HomePage() {
        setTitle("Event Management - Home Page");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Component Definition
        Home_Button = new JButton("Home");
        Tickets_Button = new JButton("Tickets");
        Event_Management_Button = new JButton("Event Management");
        Profile_Button = new JButton("Profile");
        Create_Event_Button = new JButton("Create");
        Notification_Button = new JButton("Notifications");
        Text_Search_Button = new JButton("Search");
        Combo_Search_Button = new JButton("Filter");
        Search_Field = new JTextField();
        Date = new JComboBox(c1);
        Category = new JComboBox(c2);
        Location = new JComboBox(c3);
        Tickets_Label = new JLabel("Tickets", SwingConstants.CENTER);
        Event_Management_Label = new JLabel("Event Management", SwingConstants.CENTER);
        Title_Label = new JLabel("Current Available Events", SwingConstants.CENTER);
        Reg_Events_Label = new JLabel("Events (registered in):");
        Your_Events_Label = new JLabel("Your events (created by you):");
        Profile_Label = new JLabel("Profile", SwingConstants.CENTER);
        Password_Label = new JLabel("Password: ********");
        Notification_Label = new JLabel("Notifications", SwingConstants.CENTER);

        // Side menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1, 10, 10));
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(Color.GRAY);

        menuPanel.add(Home_Button);
        menuPanel.add(Tickets_Button);
        menuPanel.add(Event_Management_Button);
        menuPanel.add(Profile_Button);
        menuPanel.add(Notification_Button);

        // ===== Menu Components Modifications (Design) =====
        Home_Button.setBackground(Color.GRAY);
        Home_Button.setForeground(Color.WHITE);
        Home_Button.setFocusPainted(false);
        Home_Button.setBorderPainted(false);
        Home_Button.setContentAreaFilled(true);
        Home_Button.setFont(new Font("Arial", Font.BOLD, 16));
        Home_Button.addActionListener(this);

        Tickets_Button.setBackground(Color.GRAY);
        Tickets_Button.setForeground(Color.WHITE);
        Tickets_Button.setFocusPainted(false);
        Tickets_Button.setBorderPainted(false);
        Tickets_Button.setContentAreaFilled(true);
        Tickets_Button.setFont(new Font("Arial", Font.BOLD, 16));
        Tickets_Button.addActionListener(this);

        Event_Management_Button.setBackground(Color.GRAY);
        Event_Management_Button.setForeground(Color.WHITE);
        Event_Management_Button.setFocusPainted(false);
        Event_Management_Button.setBorderPainted(false);
        Event_Management_Button.setContentAreaFilled(true);
        Event_Management_Button.setFont(new Font("Arial", Font.BOLD, 16));
        Event_Management_Button.addActionListener(this);

        Profile_Button.setBackground(Color.GRAY);
        Profile_Button.setForeground(Color.WHITE);
        Profile_Button.setFocusPainted(false);
        Profile_Button.setBorderPainted(false);
        Profile_Button.setContentAreaFilled(true);
        Profile_Button.setFont(new Font("Arial", Font.BOLD, 16));
        Profile_Button.addActionListener(this);

        Notification_Button.setBackground(Color.GRAY);
        Notification_Button.setForeground(Color.WHITE);
        Notification_Button.setFocusPainted(false);
        Notification_Button.setBorderPainted(false);
        Notification_Button.setContentAreaFilled(true);
        Notification_Button.setFont(new Font("Arial", Font.BOLD, 16));
        Notification_Button.addActionListener(this);

        // ===== MAIN PANEL =====
        mainPanel = new JPanel(new BorderLayout());

        // === Main content panel (Home) ===
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);

        Title_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Title_Label.setBounds(0, 0, 800, 80);
        contentPanel.add(Title_Label);

        Search_Field.setBounds(10, 110, 200, 30);
        Search_Field.setFont(new Font("Arial", Font.PLAIN, 14));
        Search_Field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        Search_Field.setBackground(Color.WHITE);
        Search_Field.setForeground(Color.DARK_GRAY);

        Text_Search_Button.setBounds(220, 110, 80, 30);
        Text_Search_Button.setBackground(Color.GRAY);
        Text_Search_Button.setForeground(Color.WHITE);
        Text_Search_Button.setFocusPainted(false);
        Text_Search_Button.setBorderPainted(false);

        Date.setBounds(320, 110, 100, 30);
        Date.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        Date.setBackground(Color.WHITE);
        Date.setForeground(Color.DARK_GRAY);
        Date.setFont(new Font("Arial", Font.PLAIN, 14));

        Category.setBounds(430, 110, 100, 30);
        Category.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        Category.setBackground(Color.WHITE);
        Category.setForeground(Color.DARK_GRAY);
        Category.setFont(new Font("Arial", Font.PLAIN, 14));

        Location.setBounds(540, 110, 100, 30);
        Location.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        Location.setBackground(Color.WHITE);
        Location.setForeground(Color.DARK_GRAY);
        Location.setFont(new Font("Arial", Font.PLAIN, 14));

        Combo_Search_Button.setBounds(650, 110, 80, 30);
        Combo_Search_Button.setBackground(Color.GRAY);
        Combo_Search_Button.setForeground(Color.WHITE);
        Combo_Search_Button.setFocusPainted(false);
        Combo_Search_Button.setBorderPainted(false);

        contentPanel.add(Search_Field);
        contentPanel.add(Text_Search_Button);
        contentPanel.add(Date);
        contentPanel.add(Category);
        contentPanel.add(Location);
        contentPanel.add(Combo_Search_Button);

        // ======= Panel contains Available events in (Home Page)========
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        eventsPanel.setBounds(0, 140, 800, 400);
        eventsPanel.setBackground(Color.WHITE);

        // ========== Tickets Panel and ITS DESIGN ============
        Tickets_Panel = new JPanel();
        Tickets_Panel.setLayout(null);
        Tickets_Panel.setBounds(0, 0, 800, 600);

        Tickets_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Tickets_Label.setBounds(0, 0, 800, 80);
        Tickets_Label.setOpaque(true);
        Tickets_Panel.add(Tickets_Label);

        // ========= Event Management Panel and ITS DESIGN ============
        Event_Management_Panel = new JPanel();
        Event_Management_Panel.setLayout(null);
        Event_Management_Panel.setBounds(0, 0, 800, 600);
        Event_Management_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Event_Management_Label.setBounds(0, 0, 800, 80);
        Event_Management_Label.setOpaque(true);
        Event_Management_Panel.add(Event_Management_Label);

        Create_Event_Button.setBounds(20, 100, 100, 35);
        Create_Event_Button.setBackground(Color.GRAY);
        Create_Event_Button.setForeground(Color.WHITE);
        Create_Event_Button.setBorderPainted(false);
        Create_Event_Button.setFocusPainted(false);
        Create_Event_Button.setFont(new Font("Arial", Font.BOLD, 14));
        Create_Event_Button.addActionListener(this);
        Event_Management_Panel.add(Create_Event_Button);

        Reg_Events_Label.setFont(new Font("Arial", Font.PLAIN, 16));
        Reg_Events_Label.setBounds(20, 150, 300, 25);
        Event_Management_Panel.add(Reg_Events_Label);

        JPanel regEvent2 = new JPanel();
        regEvent2.setBounds(20, 180, 700, 40);
        regEvent2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        regEvent2.add(new JLabel("Workshop @ Room B - May 10"));
        Event_Management_Panel.add(regEvent2);

        Your_Events_Label.setFont(new Font("Arial", Font.PLAIN, 16));
        Your_Events_Label.setBounds(20, 250, 300, 25);
        Event_Management_Panel.add(Your_Events_Label);

        // =========== Profile Panel and ITS DESIGN ===========
        Profile_Panel = new JPanel();
        Profile_Panel.setLayout(null);
        Profile_Panel.setBounds(0, 0, 800, 600);

        Profile_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Profile_Label.setBounds(0, 0, 800, 80);
        Profile_Label.setOpaque(true);
        Profile_Panel.add(Profile_Label);

        User_Name_Label = new JLabel("Username: your_username");
        User_Name_Label.setBounds(50, 120, 300, 30);
        User_Name_Label.setFont(new Font("Arial", Font.PLAIN, 16));
        Profile_Panel.add(User_Name_Label);

        Email_Label = new JLabel("Email: your_email@example.com");
        Email_Label.setBounds(50, 160, 300, 30);
        Email_Label.setFont(new Font("Arial", Font.PLAIN, 16));
        Profile_Panel.add(Email_Label);

        Password_Label.setBounds(50, 200, 300, 30);
        Password_Label.setFont(new Font("Arial", Font.PLAIN, 16));
        Profile_Panel.add(Password_Label);

        // ============ Notification Panel and ITS DESIGN ============
        Notification_Panel = new JPanel();
        Notification_Panel.setLayout(null);
        Notification_Panel.setBounds(0, 0, 800, 600);

        Notification_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Notification_Label.setBounds(0, 0, 800, 80);
        Notification_Label.setOpaque(true);
        Notification_Panel.add(Notification_Label);
        
        // --- Insert Notification code (using class members) ---
        // Pick a random notification time
        Random notifRandom = new Random();
        int notifIndex = notifRandom.nextInt(notifTimeArr.length);
        // Initialize notification components
        Icon notifIcon = UIManager.getIcon("OptionPane.informationIcon");
        iconLabel = new JLabel(notifIcon);
        titleLabel = new JLabel(notifTitleArr[0]);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        descriptionLabel = new JLabel(notifDescArr[0]);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        timeLabel = new JLabel(sdf.format(notifTimeArr[notifIndex]));
        timeLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        // Create a sub-panel to hold the text (title and description)
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(descriptionLabel, BorderLayout.CENTER);
        // Create a panel for the notification content
        JPanel notificationContent = new JPanel(new FlowLayout());
        notificationContent.setBounds(0, 90, 800, 100);
        notificationContent.add(iconLabel);
        notificationContent.add(textPanel);
        notificationContent.add(timeLabel);
        // Add the notification content panel to the Notification_Panel
        Notification_Panel.add(notificationContent);
        // --- End Notification code insertion ---

        // =============== menu and main JPanels ADDED TO JFRAME =========
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        mainPanel.removeAll();
        if (e.getSource() == Home_Button) { // === RETURNS TO HOME PANEL ===
            mainPanel.add(contentPanel, BorderLayout.CENTER);
        } else if (e.getSource() == Tickets_Button) { // ==== GOES TO TICKETS PANEL ===
            mainPanel.add(Tickets_Panel, BorderLayout.CENTER);
        } else if (e.getSource() == Event_Management_Button) { // === GOES TO EVENT MANAGEMENT PANEL ===
            mainPanel.add(Event_Management_Panel, BorderLayout.CENTER);
        } else if (e.getSource() == Profile_Button) { // === GOES TO PROFILE PANEL ===
            mainPanel.add(Profile_Panel, BorderLayout.CENTER);
            User_Name_Label.setText("User Name: " + DataBaseSim.Users[DataBaseSim.Current_User].un);
            Email_Label.setText("User Email: " + DataBaseSim.Users[DataBaseSim.Current_User].mail);
            Password_Label.setText("User Password: " + DataBaseSim.Users[DataBaseSim.Current_User].pass);
        } else if (e.getSource() == Notification_Button) { // === GOES TO NOTIFICATION PANEL ===
            mainPanel.add(Notification_Panel, BorderLayout.CENTER);
        } else if (e.getSource() == Create_Event_Button) { // === FOR THE CREATE EVENT BUTTON IN EVENT MANAGEMENT PANEL TO CREATE EVENT ===
            JTextField Name_Field = new JTextField();
            JTextField Date_Field = new JTextField();
            JTextField Location_Field = new JTextField();

            JPanel panel = new JPanel(new GridLayout(0, 1)); // POP UP PANEL DESIGN
            panel.add(new JLabel("Event Name:"));
            panel.add(Name_Field);
            panel.add(new JLabel("Date:"));
            panel.add(Date_Field);
            panel.add(new JLabel("Location:"));
            panel.add(Location_Field);

            int result = JOptionPane.showConfirmDialog(this, panel, "Create New Event", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) { // IF USER PRESS OK
                String name = Name_Field.getText().trim();
                String date = Date_Field.getText().trim();
                String location = Location_Field.getText().trim();

                if (!name.isEmpty() && !date.isEmpty() && !location.isEmpty()) { // CHECKS IF NAME, DATE, LOCATION IS EMPTY OR NOT
                    JButton newEvent = new JButton(name + " @ " + location + " - " + date);
                    newEvent.setBounds(20, simpleEventY, 700, 40);
                    newEvent.setBackground(Color.GRAY);
                    newEvent.setForeground(Color.WHITE);
                    newEvent.setBorderPainted(false);
                    newEvent.setFocusPainted(false);

                    // EVENT DATA BASE SIMULATION START
                    Events nEvent = new Events(name, location, date); // CREATE EVENT TO STORE IN DATA BASE
                    EventsDataBase.Available_Events[EventsDataBase.ne] = nEvent;
                    int eventIndex = EventsDataBase.ne;
                    EventsDataBase.ne++;
                    // EVENT DATA BASE SIMULATION END

                    // ADD: Edit functionality
                    newEvent.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JTextField nameField = new JTextField(nEvent.name);
                            JTextField dateField = new JTextField(nEvent.date);
                            JTextField locationField = new JTextField(nEvent.location);

                            JPanel editPanel = new JPanel(new GridLayout(0, 1));
                            editPanel.add(new JLabel("Event Name:"));
                            editPanel.add(nameField);
                            editPanel.add(new JLabel("Date:"));
                            editPanel.add(dateField);
                            editPanel.add(new JLabel("Location:"));
                            editPanel.add(locationField);

                            int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit Event",
                                    JOptionPane.OK_CANCEL_OPTION);
                            if (result == JOptionPane.OK_OPTION) {
                                nEvent.name = nameField.getText().trim();
                                nEvent.date = dateField.getText().trim();
                                nEvent.location = locationField.getText().trim();
                                newEvent.setText(nEvent.name + " @ " + nEvent.location + " - " + nEvent.date);
                            }
                        }
                    });

                    Event_Management_Panel.add(newEvent);
                    Event_Management_Panel.repaint();
                    simpleEventY += 50;

                    // Also refresh and create events on home
                    eventsPanel.removeAll(); // deletes previous events to avoid duplicates
                    for (int i = 0; i < EventsDataBase.ne; i++) {
                        JButton eventBox = new JButton("<html>"
                                + "<div style='text-align:center;'>"
                                + "Name: " + EventsDataBase.Available_Events[i].name + "<br>"
                                + "Location: " + EventsDataBase.Available_Events[i].location + "<br>"
                                + "Date: " + EventsDataBase.Available_Events[i].date
                                + "</div></html>");
                        eventBox.setPreferredSize(new Dimension(200, 100));
                        eventBox.setBackground(Color.LIGHT_GRAY);
                        eventBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        eventBox.setFocusPainted(false);
                        eventBox.setFont(new Font("Arial", Font.PLAIN, 12));
                        eventsPanel.add(eventBox);
                    }
                    contentPanel.add(eventsPanel);
                }
            }
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
