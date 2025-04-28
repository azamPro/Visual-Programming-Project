package sections;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import auth.Session; 

import services.EventService; 
import java.sql.Timestamp; 
import services.RegistrationService;

import java.util.List;

public class EventManagementPanel extends JPanel {
    public JLabel titleLabel, registeredLabel, yourEventsLabel;
    public JButton createEventButton;
    public JPanel registeredBox;
    public JPanel eventListPanel; 



    public EventManagementPanel() {

        setLayout(null);
        setBounds(0, 0, 800, 600);
        setBackground(Color.WHITE);

        titleLabel = new JLabel("Event Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBounds(0, 0, 800, 80);
        titleLabel.setForeground(Color.DARK_GRAY);
        add(titleLabel);

        createEventButton = new JButton("Create");
        createEventButton.setBounds(20, 100, 100, 35);
        createEventButton.setBackground(Color.GRAY);
        createEventButton.setForeground(Color.WHITE);
        createEventButton.setBorderPainted(false);
        createEventButton.setFocusPainted(false);
        createEventButton.setFont(new Font("Arial", Font.BOLD, 14));
        createEventButton.addActionListener(e -> openCreateEventDialog());
        add(createEventButton);

        if (!"organizer".equals(Session.getRole())) {
            createEventButton.setVisible(false);
        }
        registeredLabel = new JLabel("Events (registered in):");
        registeredLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        registeredLabel.setBounds(20, 150, 300, 25);
        add(registeredLabel);

        registeredBox = new JPanel();
        registeredBox.setLayout(new BoxLayout(registeredBox, BoxLayout.Y_AXIS));
        registeredBox.setBackground(Color.WHITE);
        
        JScrollPane registeredScrollPane = new JScrollPane(registeredBox);
        registeredScrollPane.setBounds(20, 180, 740, 380); // Bigger height
        registeredScrollPane.setBorder(BorderFactory.createTitledBorder("Registered Events"));
        registeredScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        
        add(registeredScrollPane);
        

        loadRegisteredEvents();

    }


    private void createEvent(String eventName, String location, Timestamp dateTime, int totalSeats) {
        if (EventService.createEvent(eventName, location, dateTime, totalSeats)) {
            JOptionPane.showMessageDialog(this, "Event created successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create event.");
        }
    }
    
    

    private void openCreateEventDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JTextField eventNameField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField totalSeatsField = new JTextField();
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd HH:mm:ss");
        dateSpinner.setEditor(timeEditor);
    
        panel.add(new JLabel("Event Name:"));
        panel.add(eventNameField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("Date and Time:"));
        panel.add(dateSpinner);
        panel.add(new JLabel("Total Seats:"));
        panel.add(totalSeatsField);

    
        int result = JOptionPane.showConfirmDialog(this, panel, "Create Event",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            int totalSeats = Integer.parseInt(totalSeatsField.getText());

            String eventName = eventNameField.getText();
            String location = locationField.getText();
            Date date = (Date) dateSpinner.getValue();
            
            // Save to database
            createEvent(eventName, location, new java.sql.Timestamp(date.getTime()), totalSeats);
        }
    }

    
    public void loadRegisteredEvents() {
        registeredBox.removeAll(); 
    
        List<RegistrationService.RegisteredEvent> registeredEvents = RegistrationService.getRegisteredEvents(Session.getUserId());
    
        if (registeredEvents.isEmpty()) {
            JLabel emptyLabel = new JLabel("No events registered.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            emptyLabel.setForeground(Color.GRAY);
            registeredBox.add(emptyLabel);
        } else {
            for (RegistrationService.RegisteredEvent event : registeredEvents) {
                JPanel eventPanel = new JPanel(new GridLayout(0, 1));
                eventPanel.setBackground(Color.LIGHT_GRAY);
                eventPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    
                JLabel nameLabel = new JLabel("[Event Name] " + event.getEventName());
                nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    
                JLabel locationLabel = new JLabel("[Location] " + event.getLocation());
                locationLabel.setFont(new Font("Arial", Font.PLAIN, 13));
    
                JLabel dateLabel = new JLabel("[Time] " + event.getFormattedDate());
                dateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
                dateLabel.setForeground(Color.DARK_GRAY);
    
                eventPanel.add(nameLabel);
                eventPanel.add(locationLabel);
                eventPanel.add(dateLabel);
    
                registeredBox.add(Box.createVerticalStrut(10));
                registeredBox.add(eventPanel);
            }
        }
    
        registeredBox.revalidate();
        registeredBox.repaint();
    }
    

    // private String formatDate(String rawDate) {
    //     try {
    //         SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //         SimpleDateFormat output = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
    //         Date date = input.parse(rawDate);
    //         return output.format(date);
    //     } catch (ParseException e) {
    //         return rawDate;
    //     }
    // }
}
