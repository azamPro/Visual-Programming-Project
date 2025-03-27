package auth;

import javax.swing.*;
import utils.MessageBox;

public class Register extends JFrame {

    public Register() {
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 80, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(130, 30, 200, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 70, 80, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(130, 70, 200, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 80, 25);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(130, 110, 200, 25);
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(130, 160, 100, 30);
        add(registerButton);

        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            // Basic validation
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                MessageBox.showError("All fields are required.");
            } else {
                MessageBox.showSuccess("Registration successful (simulated).");
                // In the future, call AuthService.register(name, email, password);
            }
        });

        setVisible(true);
    }
}
