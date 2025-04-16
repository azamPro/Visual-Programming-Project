package sections;

import javax.swing.*;
import java.awt.*;

public class TicketsPanel extends JPanel {
    public JLabel ticketsLabel;

    public TicketsPanel() {
        setLayout(null);
        setBounds(0, 0, 800, 600);

        ticketsLabel = new JLabel("Tickets", SwingConstants.CENTER);
        ticketsLabel.setFont(new Font("Arial", Font.BOLD, 25));
        ticketsLabel.setBounds(0, 0, 800, 80);
        ticketsLabel.setOpaque(false); // No background
        ticketsLabel.setForeground(Color.DARK_GRAY); // Match the home page text color
                
        add(ticketsLabel);
    }
}
