package auth;

import javax.swing.*;

public class Login extends JFrame {

    public Login() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(null);

        JLabel label = new JLabel("Welcome to the Event Manager Login!");
        label.setBounds(30, 50, 300, 30);
        add(label);

        setVisible(true);
    }
}
