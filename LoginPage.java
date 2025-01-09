import DatabaseConnection.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("Aeropass Login");
        setSize(400, 500);  // Increased size to accommodate the logo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Load and resize Logo Image
        JLabel logoLabel = new JLabel();
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/flight.jpeg"));
            Image logoImage = logoIcon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(logoImage));
        } catch (Exception e) {
            System.out.println("Logo image not found.");
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(logoLabel, gbc);

        // Login Components
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        gbc.gridy = 1;
        panel.add(titleLabel, gbc);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);

        JLabel registerLabel = new JLabel("<HTML><U>Don't have an account? Register here</U></HTML>");
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        gbc.gridy = 5;
        panel.add(registerLabel, gbc);

        add(panel);

        // Action for login button
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (!email.isEmpty() && !password.isEmpty()) {
                if (validateLogin(email, password)) {
                    JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MainMenu().setVisible(true);  // Open the main menu
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both email and password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action for register label (redirect to registration page)
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new RegistrationPage().setVisible(true);  // Open the registration page
            }
        });

        setVisible(true);
    }

    // Validate login using database credentials
    private boolean validateLogin(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();  // Return true if login successful

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Database error. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
