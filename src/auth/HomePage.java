package auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame implements ActionListener {
    private JButton Home_Button, Tickets_Button, Event_Management_Button, Profile_Button, Notification_Button;
    private JPanel menuPanel, Tickets_Panel, contentPanel, Event_Management_Panel, Profile_Panel, Notification_Panel,
            mainPanel;
    private JComboBox date, category, location;
    private String[] c1 = { "date" };
    private String[] c2 = { "Category" };
    private String[] c3 = { "Location" };

    public HomePage() {
        setTitle("Event Management - Home Page");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Side menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1, 10, 10));
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(Color.GRAY);

        Home_Button = new JButton("Home");
        Tickets_Button = new JButton("Tickets");
        Event_Management_Button = new JButton("Event Management");
        Profile_Button = new JButton("Profile");
        Notification_Button = new JButton("Notifications");

        menuPanel.add(Home_Button);
        menuPanel.add(Tickets_Button);
        menuPanel.add(Event_Management_Button);
        menuPanel.add(Profile_Button);
        menuPanel.add(Notification_Button);

        // Buttons design
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

        // === Title ===
        JLabel titleLabel = new JLabel("Current Available Events", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBounds(0, 0, 800, 80);
        contentPanel.add(titleLabel);

        // === Search field + button ===
        JTextField searchField = new JTextField();
        searchField.setBounds(10, 110, 200, 30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.DARK_GRAY);

        JButton textSearchButton = new JButton("Search");
        textSearchButton.setBounds(220, 110, 80, 30);
        textSearchButton.setBackground(Color.GRAY);
        textSearchButton.setForeground(Color.WHITE);
        textSearchButton.setFocusPainted(false);
        textSearchButton.setBorderPainted(false);

        // === Combo boxes + filter button ===
        date = new JComboBox(c1);
        date.setBounds(320, 110, 100, 30);

        category = new JComboBox(c2);
        category.setBounds(430, 110, 100, 30);

        location = new JComboBox(c3);
        location.setBounds(540, 110, 100, 30);

        date.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        date.setBackground(Color.WHITE);
        date.setForeground(Color.DARK_GRAY);
        date.setFont(new Font("Arial", Font.PLAIN, 14));

        category.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        category.setBackground(Color.WHITE);
        category.setForeground(Color.DARK_GRAY);
        category.setFont(new Font("Arial", Font.PLAIN, 14));

        location.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        location.setBackground(Color.WHITE);
        location.setForeground(Color.DARK_GRAY);
        location.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton comboSearchButton = new JButton("Filter");
        comboSearchButton.setBounds(650, 110, 80, 30);
        comboSearchButton.setBackground(Color.GRAY);
        comboSearchButton.setForeground(Color.WHITE);
        comboSearchButton.setFocusPainted(false);
        comboSearchButton.setBorderPainted(false);

        // Add to filter panel
        contentPanel.add(searchField);
        contentPanel.add(textSearchButton);
        contentPanel.add(date);
        contentPanel.add(category);
        contentPanel.add(location);
        contentPanel.add(comboSearchButton);

        // === Events display area ===
        JPanel eventsPanel = new JPanel();
        eventsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        eventsPanel.setBounds(0, 140, 800, 400);
        eventsPanel.setBackground(Color.WHITE);

        for (int i = 1; i <= 6; i++) {
            JPanel eventBox = new JPanel();
            eventBox.setPreferredSize(new Dimension(120, 100));
            eventBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            eventBox.add(new JLabel("Event " + i));
            eventsPanel.add(eventBox);
        }

        contentPanel.add(eventsPanel);

        // === Tickets Panel ===
        Tickets_Panel = new JPanel(new BorderLayout());
        JLabel Tickets_Label = new JLabel("Tickets", SwingConstants.CENTER);
        Tickets_Label.setPreferredSize(new Dimension(0, 80));
        Tickets_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Tickets_Label.setOpaque(true);
        Tickets_Panel.add(Tickets_Label, BorderLayout.NORTH);

        // === Event Management Panel ===
        Event_Management_Panel = new JPanel(new BorderLayout());
        JLabel Event_Management_Label = new JLabel("Event Management", SwingConstants.CENTER);
        Event_Management_Label.setPreferredSize(new Dimension(0, 80));
        Event_Management_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Event_Management_Label.setOpaque(true);
        Event_Management_Panel.add(Event_Management_Label, BorderLayout.NORTH);

        // === Profile Panel ===
        Profile_Panel = new JPanel(new BorderLayout());
        JLabel Profile_Label = new JLabel("Profile", SwingConstants.CENTER);
        Profile_Label.setPreferredSize(new Dimension(0, 80));
        Profile_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Profile_Label.setOpaque(true);
        Profile_Panel.add(Profile_Label, BorderLayout.NORTH);

        // === Notification Panel ===
        Notification_Panel = new JPanel(new BorderLayout());
        JLabel Notification_Label = new JLabel("Notifications", SwingConstants.CENTER);
        Notification_Label.setPreferredSize(new Dimension(0, 80));
        Notification_Label.setFont(new Font("Arial", Font.BOLD, 25));
        Notification_Label.setOpaque(true);
        Notification_Panel.add(Notification_Label, BorderLayout.NORTH);

        // Show home panel by default
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Add to JFrame
        add(menuPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Remove current panel from mainPanel
        mainPanel.removeAll();

        if (e.getSource() == Home_Button) {
            mainPanel.add(contentPanel, BorderLayout.CENTER);
        } else if (e.getSource() == Tickets_Button) {
            mainPanel.add(Tickets_Panel, BorderLayout.CENTER);
        } else if (e.getSource() == Event_Management_Button) {
            mainPanel.add(Event_Management_Panel, BorderLayout.CENTER);
        } else if (e.getSource() == Profile_Button) {
            mainPanel.add(Profile_Panel, BorderLayout.CENTER);
        } else if (e.getSource() == Notification_Button) {
            mainPanel.add(Notification_Panel, BorderLayout.CENTER);
        }

        // Refresh panel display
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}