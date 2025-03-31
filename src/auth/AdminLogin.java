package auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//class for ADMIN Login JFrame
public class AdminLogin extends JFrame implements ActionListener {
    private JTextField User_Name_Field;
    private JPasswordField Password_Field;
    private JLabel User_Name_Label, Password_Label, Tit;
    private JButton Login_Button, Return_Button;

    public AdminLogin() {
        setTitle("Admim Login");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Components Definition
        User_Name_Field = new JTextField();
        User_Name_Label = new JLabel("User Name");
        Password_Label = new JLabel("Password");
        Password_Field = new JPasswordField();
        Login_Button = new JButton("Login");
        Tit = new JLabel("Admin Sign In");
        Return_Button = new JButton("Go Back");

        // Component Modifications(Design)
        User_Name_Label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        User_Name_Label.setBounds(70, 120, 100, 30);
        User_Name_Field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        User_Name_Field.setBounds(70, 150, 300, 35);
        User_Name_Field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        Return_Button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        Return_Button.setBounds(20, 450, 100, 45);
        Return_Button.setBorder(null);
        Return_Button.setBackground(Color.gray);
        Return_Button.setForeground(Color.white);

        Password_Label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        Password_Label.setBounds(70, 200, 100, 30);
        Password_Field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        Password_Field.setBounds(70, 230, 300, 35);
        Password_Field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        Login_Button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        Login_Button.setBounds(70, 320, 300, 45);
        Login_Button.setBorder(null);
        Login_Button.setBackground(Color.gray);
        Login_Button.setForeground(Color.white);

        Tit.setFont(new Font("Segoe UI", Font.BOLD, 29));
        Tit.setBounds(120, 40, 350, 40); // x, y, width, height

        // Admin Login Panel
        JPanel p1 = (JPanel) this.getContentPane();
        p1.setLayout(null);
        p1.setBackground(Color.white);
        p1.add(Tit);
        p1.add(User_Name_Label);
        p1.add(User_Name_Field);
        p1.add(Password_Label);
        p1.add(Password_Field);
        p1.add(Login_Button);
        p1.add(Return_Button);
        Return_Button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Return_Button) {
            this.setVisible(false);
            Entry a = new Entry();
        }
    }
}
