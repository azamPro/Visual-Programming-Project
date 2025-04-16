package sections;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventManagementPanel extends JPanel {
    public JLabel titleLabel, registeredLabel, yourEventsLabel;
    public JButton createEventButton;
    public JPanel registeredBox;
    public JPanel eventListPanel; // New panel for scrolling

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
        add(createEventButton);

        registeredLabel = new JLabel("Events (registered in):");
        registeredLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        registeredLabel.setBounds(20, 150, 300, 25);
        add(registeredLabel);

        registeredBox = new JPanel();
        registeredBox.setBounds(20, 180, 700, 40);
        registeredBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        registeredBox.add(new JLabel("Workshop @ Room B - May 10"));
        add(registeredBox);

        yourEventsLabel = new JLabel("Your events (created by you):");
        yourEventsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        yourEventsLabel.setBounds(20, 230, 300, 25);
        add(yourEventsLabel);

    }

    public void addEventBox(String name, String location, String rawDate) {
        JPanel eventBox = new JPanel();
        eventBox.setLayout(new BoxLayout(eventBox, BoxLayout.Y_AXIS));
        eventBox.setBackground(Color.LIGHT_GRAY);
        eventBox.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        eventBox.setMaximumSize(new Dimension(720, 80));

        JLabel nameLabel = new JLabel("\uD83D\uDCC5 " + name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel locationLabel = new JLabel("\uD83D\uDCCD " + location);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel dateLabel = new JLabel("\uD83D\uDD52 " + formatDate(rawDate));
        dateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        dateLabel.setForeground(Color.DARK_GRAY);

        eventBox.add(nameLabel);
        eventBox.add(locationLabel);
        eventBox.add(dateLabel);

        eventListPanel.add(Box.createVerticalStrut(10));
        eventListPanel.add(eventBox);
        eventListPanel.revalidate();
        eventListPanel.repaint();
    }

    private String formatDate(String rawDate) {
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
            Date date = input.parse(rawDate);
            return output.format(date);
        } catch (ParseException e) {
            return rawDate;
        }
    }
}
