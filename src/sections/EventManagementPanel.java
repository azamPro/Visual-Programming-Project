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

        /*
         * registeredBox = new JPanel();
         * registeredBox.setLayout(new BoxLayout(registeredBox, BoxLayout.Y_AXIS));
         * registeredBox.setBackground(Color.WHITE);
         * 
         * JScrollPane registeredScrollPane = new JScrollPane(registeredBox);
         * registeredScrollPane.setBounds(20, 180, 740, 380); // Bigger height
         * registeredScrollPane.setBorder(BorderFactory.
         * createTitledBorder("your events:"));
         * registeredScrollPane.getVerticalScrollBar().setUnitIncrement(10);
         * 
         * add(registeredScrollPane);
         */

        eventListPanel = new JPanel();
        eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS));
        eventListPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(eventListPanel);
        scrollPane.setBounds(20, 180, 740, 350);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        add(scrollPane);

        loadCreatedEvents();
    }

    // this method creates an event and saves it to the database
    private void createEvent(String eventName, String location, Timestamp dateTime, int totalSeats) {
        if (EventService.createEvent(eventName, location, dateTime, totalSeats)) {
            JOptionPane.showMessageDialog(this, "Event created successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create event.");
        }
    }

    // this method opens a dialog to create an event
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

    // this method loads the events registered by the user
    // public void loadRegisteredEvents() {
    // registeredBox.removeAll();

    // List<RegistrationService.RegisteredEvent> registeredEvents =
    // RegistrationService.getRegisteredEvents(Session.getUserId());

    // if (registeredEvents.isEmpty()) {
    // JLabel emptyLabel = new JLabel("No events registered.");
    // emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
    // emptyLabel.setForeground(Color.GRAY);
    // registeredBox.add(emptyLabel);
    // } else {
    // for (RegistrationService.RegisteredEvent event : registeredEvents) {
    // JPanel eventPanel = new JPanel(new GridLayout(0, 1));
    // eventPanel.setBackground(Color.LIGHT_GRAY);
    // eventPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    // JLabel nameLabel = new JLabel("[Event Name] " + event.getEventName());
    // nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

    // JLabel locationLabel = new JLabel("[Location] " + event.getLocation());
    // locationLabel.setFont(new Font("Arial", Font.PLAIN, 13));

    // JLabel dateLabel = new JLabel("[Time] " + event.getFormattedDate());
    // dateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
    // dateLabel.setForeground(Color.DARK_GRAY);

    // eventPanel.add(nameLabel);
    // eventPanel.add(locationLabel);
    // eventPanel.add(dateLabel);

    // registeredBox.add(Box.createVerticalStrut(10));
    // registeredBox.add(eventPanel);
    // }
    // }

    // registeredBox.revalidate();
    // registeredBox.repaint();
    // }

    // this method loads the events created by the organizer
    public void loadCreatedEvents() {
        System.out.println("Organizer ID: " + Session.getUserId());
        eventListPanel.removeAll();

        List<EventService.CreatedEvent> createdEvents = EventService.getCreatedEvents(Session.getUserId());
        System.out.println("Created Events Found: " + createdEvents.size());

        if (createdEvents.isEmpty()) {
            JLabel emptyLabel = new JLabel("You haven't created any events.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            emptyLabel.setForeground(Color.GRAY);
            eventListPanel.add(emptyLabel);
        } else {
            for (EventService.CreatedEvent event : createdEvents) {
                JPanel eventBox = new JPanel();
                eventBox.setLayout(new BoxLayout(eventBox, BoxLayout.Y_AXIS));
                eventBox.setBackground(Color.LIGHT_GRAY);
                eventBox.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
                eventBox.setMaximumSize(new Dimension(720, 80));

                //////// this for edit ////////////
                /*
                 * JButton edit_Button = new JButton("Edit");
                 * edit_Button.addActionListener(ev -> {
                 * JTextField nameField = new JTextField(event.name);
                 * JTextField dateField = new JTextField(event.date);
                 * JTextField locationField = new JTextField(event.location);
                 * 
                 * JPanel editPanel = new JPanel(new GridLayout(0, 1));
                 * editPanel.add(new JLabel("Event Name:"));
                 * editPanel.add(nameField);
                 * editPanel.add(new JLabel("Date:"));
                 * editPanel.add(dateField);
                 * editPanel.add(new JLabel("Location:"));
                 * editPanel.add(locationField);
                 * 
                 * int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit Event",
                 * JOptionPane.OK_CANCEL_OPTION);
                 * if (result == JOptionPane.OK_OPTION) {
                 * event.name = nameField.getText().trim();
                 * event.date = dateField.getText().trim();
                 * event.location = locationField.getText().trim();
                 * edit_Button.setText(event.name + " @ " + event.location + " - " +
                 * event.date);
                 * }
                 * });
                 */
                //////////

                JButton delete_Button = new JButton("delete");
                delete_Button.setPreferredSize(new Dimension(60, 25)); // Smaller size
                delete_Button.setFocusPainted(false); // Removes dotted focus border
                delete_Button.setBorderPainted(true); // Removes button border
                delete_Button.setContentAreaFilled(false); // Optional: removes background fill for cleaner look

                JLabel nameLabel = new JLabel("[Event Name] " + event.getName());
                nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

                JLabel locationLabel = new JLabel("[Location] " + event.getLocation());
                locationLabel.setFont(new Font("Arial", Font.PLAIN, 13));

                JLabel dateLabel = new JLabel("[Date] " + event.getDate());
                dateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
                dateLabel.setForeground(Color.DARK_GRAY);
                // eventBox.add(edit_Button);
                eventBox.add(delete_Button);//
                eventBox.add(nameLabel);
                eventBox.add(locationLabel);
                eventBox.add(dateLabel);

                eventListPanel.add(Box.createVerticalStrut(10));
                eventListPanel.add(eventBox);
            }
        }

        eventListPanel.revalidate();
        eventListPanel.repaint();
    }

    // private String formatDate(String rawDate) {
    // try {
    // SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // SimpleDateFormat output = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
    // Date date = input.parse(rawDate);
    // return output.format(date);
    // } catch (ParseException e) {
    // return rawDate;
    // }
    // }
}
