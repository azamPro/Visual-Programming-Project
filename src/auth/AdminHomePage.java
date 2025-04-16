package auth;
import javax.swing.*;

import db.DBConnection;

import java.awt.*;
import java.awt.event.*;/* 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
*/
import java.text.SimpleDateFormat;
import java.sql.*;
import java.text.SimpleDateFormat;

public class AdminHomePage extends JFrame{
    private JButton HomeButton , EventsButton,UsersButton;
    private  JPanel contentPanel,menuPanel;
    
    public AdminHomePage() {
        setTitle("Admin - Dashboard");
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
        setHomeContent();
        add(contentPanel, BorderLayout.CENTER);
    
        
    
        // Menu listener assignment
        MenuListener menuListener = new MenuListener();
        HomeButton.addActionListener(menuListener);
        EventsButton.addActionListener(menuListener);
        UsersButton.addActionListener(menuListener);
    
        setVisible(true);
    }
    
    // Set home content
    private void setHomeContent() {
        contentPanel.removeAll();
        JLabel homeLabel = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);
        homeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(homeLabel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    // Set users content
    private void setUsersContent() {
        contentPanel.removeAll();
        JLabel usersLabel = new JLabel("Manage Users", SwingConstants.CENTER);
        usersLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(usersLabel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    // Set events content using EventsPanel
    private void setEventsContent() {
        contentPanel.removeAll();
        EventsPanel eventsPanel = new EventsPanel(); 
        contentPanel.add(eventsPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    // Button style configuration
    private void configureButton(JButton button) {
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }
    
    // Inner class to handle menu button clicks
    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == HomeButton) {
                setHomeContent();
            } else if (e.getSource() == EventsButton) {
                contentPanel.removeAll();
                EventsPanel eventsPanel = new EventsPanel();
                contentPanel.add(eventsPanel, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }            
            else if (e.getSource() == UsersButton) {
                contentPanel.removeAll();
                // Make sure to import com.mycompany.userspanel.UsersPanel if necessary.
                UsersPanel usersPanel = new UsersPanel();
                contentPanel.add(usersPanel, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        }
    }
    
    public static void main(String[] args) {
        new AdminHomePage();
    }}
    