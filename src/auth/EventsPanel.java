package auth;

import javax.swing.*;
 import java.awt.*; 
 import java.sql.*; 
 import java.text.SimpleDateFormat;
 import db.DBConnection;


public class EventsPanel extends JPanel { 
    public EventsPanel() {
         setLayout(new BorderLayout());
          setBackground(Color.WHITE);
              // Title label at top
    JLabel titleLabel = new JLabel("Events Management", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
    titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    add(titleLabel, BorderLayout.NORTH);
    
    // Column headers panel
    JPanel columnPanel = new JPanel(new GridLayout(1, 6, 10, 0));
    columnPanel.setBackground(Color.LIGHT_GRAY);
    columnPanel.add(createHeaderLabel("Name"));
    columnPanel.add(createHeaderLabel("Date"));
    columnPanel.add(createHeaderLabel("Location"));
    columnPanel.add(createHeaderLabel("Description"));
    columnPanel.add(createHeaderLabel("Total Seats"));
    columnPanel.add(createHeaderLabel("Availability"));
    
    // Rows panel (holds each event row)
    JPanel rowsPanel = new JPanel(new GridLayout(0, 6, 10, 0));
    rowsPanel.setBackground(Color.WHITE);
    
    // Fetch event data from the database and populate rows
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT event_name, date, location, description, total_seats, availablity FROM events";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        while (rs.next()) {
            String name = rs.getString("event_name");
            Timestamp dateTS = rs.getTimestamp("date");
            String dateStr = (dateTS != null) ? sdf.format(dateTS) : "";
            String location = rs.getString("location");
            String description = rs.getString("description");
            int totalSeats = rs.getInt("total_seats");
            boolean available = rs.getBoolean("availablity");
            String availableStr = available ? "Yes" : "No";
            
            rowsPanel.add(createDataLabel(name));
            rowsPanel.add(createDataLabel(dateStr));
            rowsPanel.add(createDataLabel(location));
            rowsPanel.add(createDataLabel(description));
            rowsPanel.add(createDataLabel(String.valueOf(totalSeats)));
            rowsPanel.add(createDataLabel(availableStr));
        }
        
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        rowsPanel.add(new JLabel("Error loading events from database."));
    }
    
    // Wrap the rows panel in a scroll pane for usability
    JScrollPane scrollPane = new JScrollPane(rowsPanel);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    // Combine column headers and rows into a main events panel
    JPanel eventsContainer = new JPanel(new BorderLayout());
    eventsContainer.add(columnPanel, BorderLayout.NORTH);
    eventsContainer.add(scrollPane, BorderLayout.CENTER);
    
    add(eventsContainer, BorderLayout.CENTER);
}

// Helper to create header labels
private JLabel createHeaderLabel(String text) {
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    return label;
}

// Helper to create data labels
private JLabel createDataLabel(String text) {
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    return label;
}}
