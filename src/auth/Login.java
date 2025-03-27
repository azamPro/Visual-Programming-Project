package auth;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField User_Name;
    private JPasswordField Password;
    private JLabel User_Name_Label, Password_Label, Tit;
    private JButton Login, Register;

    public Login() {
        setTitle("Login");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        // Components Definition
        User_Name = new JTextField();
        User_Name_Label = new JLabel("User Name");
        Password_Label = new JLabel("Password");
        Password = new JPasswordField();
        Register = new JButton("Create Account");
        Login = new JButton("Login");
        Tit = new JLabel("Sign In");

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

        Login.setFont(new Font("Segoe UI", Font.BOLD, 16));
        Login.setBounds(70, 320, 130, 45);
        Login.setBorder(null);
        Login.setBackground(Color.gray);
        Login.setForeground(Color.white);

        Register.setFont(new Font("Segoe UI", Font.BOLD, 16));
        Register.setBounds(220, 320, 150, 45);
        Register.setBorder(null);
        Register.setBackground(Color.GRAY);
        Register.setForeground(Color.white);

        Tit.setFont(new Font("Segoe UI", Font.BOLD, 32));
        Tit.setBounds(160, 40, 200, 40); // x, y, width, height

        // Main Panel
        JPanel p1 = (JPanel) this.getContentPane();
        p1.setLayout(null);
        p1.setBackground(Color.white);
        p1.add(Tit);
        p1.add(User_Name_Label);
        p1.add(User_Name);
        p1.add(Password_Label);
        p1.add(Password);
        p1.add(Login);
        p1.add(Register);
        setVisible(true);
    }
}
