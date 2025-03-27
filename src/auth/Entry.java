package auth;

import javax.swing.*;
import java.awt.*;

public class Entry extends JFrame {
    JLabel Entry;
    JButton Admin, User;

    public Entry() {
        setTitle("Entry");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // def
        Entry = new JLabel("Signinig in as?");
        Admin = new JButton("Admin");
        User = new JButton("User");

        Entry.setFont(new Font("Segoe UI", Font.BOLD, 32));
        Entry.setBounds(120, 40, 200, 40);

        User.setFont(new Font("Segoe UI", Font.BOLD, 16));
        User.setBounds(50, 200, 150, 45);
        User.setBorder(null);
        User.setBackground(Color.gray);
        User.setForeground(Color.white);

        Admin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        Admin.setBounds(250, 200, 150, 45);
        Admin.setBorder(null);
        Admin.setBackground(Color.GRAY);
        Admin.setForeground(Color.white);

        JPanel p = (JPanel) this.getContentPane();
        p.setLayout(null);
        p.setBackground(Color.white);
        p.add(Entry);
        p.add(Admin);
        p.add(User);

        this.setVisible(true);

    }
}
