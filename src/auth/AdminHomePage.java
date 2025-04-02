package auth;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminHomePage extends JFrame{
    private JButton HomeButton , EventsButton,UsersButton;
    private  JPanel contentPanel,menuPanel;
    
 public AdminHomePage() {
    setTitle("Admin - Home Page");
    setSize(1000, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    // Side menu panel
     menuPanel = new JPanel(new GridLayout(3, 1, 10, 10));
    menuPanel.setPreferredSize(new Dimension(200, 0));
    menuPanel.setBackground(Color.GRAY);

    HomeButton = new JButton("Home");
    EventsButton = new JButton("Events");
    UsersButton = new JButton("Users");

    configureButton(HomeButton);
    configureButton(EventsButton);
    configureButton(UsersButton);

    menuPanel.add(HomeButton);
    menuPanel.add(EventsButton);
    menuPanel.add(UsersButton);

    add(menuPanel, BorderLayout.WEST);

    // Main content panel
     contentPanel = new JPanel(new BorderLayout());
    JLabel homeLabel = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);
    homeLabel.setFont(new Font("Arial", Font.BOLD, 24));
    contentPanel.add(homeLabel, BorderLayout.CENTER);

    add(contentPanel, BorderLayout.CENTER);

    // Set up the menu listener and assign to buttons
    MenuListener menuListener = new MenuListener();
    HomeButton.addActionListener(menuListener);
    EventsButton.addActionListener(menuListener);
    UsersButton.addActionListener(menuListener);

    setVisible(true);
}

// function to change button to desired style
private void configureButton(JButton button) {
    button.setBackground(Color.GRAY);
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setContentAreaFilled(true);
    button.setFont(new Font("Arial", Font.BOLD, 16));
}


private class MenuListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
         contentPanel.removeAll();

        if (e.getSource() == HomeButton) {
            JLabel homeLabel = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);
            homeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            contentPanel.add(homeLabel, BorderLayout.CENTER);
        } else if (e.getSource() == EventsButton) {
            JLabel eventsLabel = new JLabel("Manage Events", SwingConstants.CENTER);
            eventsLabel.setFont(new Font("Arial", Font.BOLD, 24));
            contentPanel.add(eventsLabel, BorderLayout.CENTER);
            
        } else if (e.getSource() == UsersButton) {
            JLabel usersLabel = new JLabel("Manage Users", SwingConstants.CENTER);
            usersLabel.setFont(new Font("Arial", Font.BOLD, 24));
            contentPanel.add(usersLabel, BorderLayout.CENTER);
            
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}

public static void main(String[] args) {
    new AdminHomePage();
}}