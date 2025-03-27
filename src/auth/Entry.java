package auth;

import javax.swing.*;
import java.awt.*;

public class Entry extends JFrame {
    JLabel Entry;
    JButton Admin, User;

    public Entry() {
        setTitle("Entry");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // def
        Entry = new JLabel("Loggin in as?");
        Admin = new JButton("Admin");
        User = new JButton("User");

        JPanel p = (JPanel) this.getContentPane();
        p.setLayout(null);

    }
}
