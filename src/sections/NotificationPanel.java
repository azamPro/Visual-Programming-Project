package sections;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;

public class NotificationPanel extends JPanel {
    public JLabel titleLabel;
    private JPanel notificationListPanel;


    public NotificationPanel() {
        setLayout(null);
        setBounds(0, 0, 800, 600);
        setBackground(Color.WHITE);

        // Title
        titleLabel = new JLabel("Notifications", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBounds(0, 0, 800, 80);
        titleLabel.setForeground(Color.DARK_GRAY);
        add(titleLabel);

        // ============ Fake Notification Data ============
        String notifTitle = "New Notification";
        String notifDesc = "You have a new message waiting in your inbox. Click to read more.";
        Date createdAt = new Date(System.currentTimeMillis() - (2 * 60 * 1000)); // 2 min ago

        // ============ Notification Card ============ 
        JPanel notifCard = new JPanel();
        notifCard.setLayout(new BorderLayout(10, 5));
        notifCard.setBackground(Color.WHITE);
        notifCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        notifCard.setBounds(50, 100, 700, 80);

        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));

        JLabel notifTitleLabel = new JLabel(notifTitle);
        notifTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel notifDescLabel = new JLabel(notifDesc);
        notifDescLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel timeLabel = new JLabel(getRelativeTime(createdAt));
        timeLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        timeLabel.setForeground(Color.GRAY);
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.add(notifTitleLabel, BorderLayout.NORTH);
        textPanel.add(notifDescLabel, BorderLayout.CENTER);
        textPanel.add(timeLabel, BorderLayout.SOUTH);

        notifCard.add(iconLabel, BorderLayout.WEST);
        notifCard.add(textPanel, BorderLayout.CENTER);

        add(notifCard);
    }

    // ===== Helper Method for Relative Time =====
    private String getRelativeTime(Date past) {
        long diff = System.currentTimeMillis() - past.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long days = TimeUnit.MILLISECONDS.toDays(diff);

        if (minutes < 1) return "Just now";
        if (minutes < 60) return minutes + "m ago";
        if (hours < 24) return hours + "h ago";
        return days + "d ago";
    }

    public void clearNotifications() {
        notificationListPanel.removeAll();
        notificationListPanel.revalidate();
        notificationListPanel.repaint();
    }
    
    public void addNotificationCard(String message, Timestamp sentTime) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);
    
        JLabel msgLabel = new JLabel("<html><b>" + message + "</b></html>");
        msgLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    
        JLabel timeLabel = new JLabel(new SimpleDateFormat("MMM dd, yyyy hh:mm a").format(sentTime));
        timeLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        timeLabel.setForeground(Color.GRAY);
    
        card.add(msgLabel, BorderLayout.CENTER);
        card.add(timeLabel, BorderLayout.SOUTH);
    
        notificationListPanel.add(Box.createVerticalStrut(10));
        notificationListPanel.add(card);
    }
    
}
