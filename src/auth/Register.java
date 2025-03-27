package auth;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {
        private JTextField User_Name, Email;
        private JPasswordField Password;
        private JLabel User_Name_Label, Password_Label, Email_Label, Tit;
        private JButton Register;

        public Register() {
                setTitle("Register");
                setSize(450, 550);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null); // Center on screen
                // Components Definition
                User_Name = new JTextField();
                User_Name_Label = new JLabel("User Name");
                Password_Label = new JLabel("Password");
                Password = new JPasswordField();
                Register = new JButton("Register");
                Email = new JTextField();
                Email_Label = new JLabel("Email");
                Tit = new JLabel("Create an Account");

                // Component Modifications(Design)
                User_Name_Label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                User_Name_Label.setBounds(70, 120, 100, 30);
                User_Name.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                User_Name.setBounds(70, 150, 300, 35);
                User_Name.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

                Password_Label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                Password_Label.setBounds(70, 200, 100, 30);
                Password.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                Password.setBounds(70, 230, 300, 35);
                Password.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
                // email
                Email_Label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                Email_Label.setBounds(70, 280, 100, 30);
                Email.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                Email.setBounds(70, 310, 300, 35);
                Email.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

                Register.setFont(new Font("Segoe UI", Font.BOLD, 16));
                Register.setBounds(120, 420, 200, 45);
                Register.setBorder(null);
                Register.setBackground(Color.GRAY);
                Register.setForeground(Color.white);

                Tit.setFont(new Font("Segoe UI", Font.BOLD, 23));
                Tit.setBounds(120, 40, 200, 40); // x, y, width, height

                // Main Panel
                JPanel p1 = (JPanel) this.getContentPane();
                p1.setLayout(null);
                p1.setBackground(Color.white);
                p1.add(Tit);
                p1.add(User_Name_Label);
                p1.add(User_Name);
                p1.add(Password_Label);
                p1.add(Password);
                p1.add(Email);
                p1.add(Email_Label);
                p1.add(Register);
                setVisible(true);
        }
}
