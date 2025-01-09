import DatabaseConnection.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationPage extends JFrame {
    public RegistrationPage() {
        setTitle("AeroPass Registration");
        setSize(700, 400);  // Adjusted the size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set light blue background color for the frame
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color

        // Panel for registration components
        JPanel registrationPanel = new JPanel(new GridBagLayout());
        registrationPanel.setOpaque(false); // Transparent to show frame background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Aeropass Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        registrationPanel.add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(15);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");  // Back button to return to LoginPage

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        registrationPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(usernameField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        registrationPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(nameField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        registrationPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(emailField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        registrationPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(passwordField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        registrationPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(confirmPasswordField, gbc);

        // Panel to hold both "Back" and "Register" buttons side by side
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(backButton);
        buttonPanel.add(registerButton);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        registrationPanel.add(buttonPanel, gbc);

        // Logo Panel with Image from the Web
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setOpaque(false); // Transparent to show frame background
        try {
            URL logoURL = new URL("https://tse3.mm.bing.net/th?id=OIP.ONMbEpR0fKNzjLgOXScpQAAAAA&pid=Api&P=0&h=180"); // Replace with the URL of your logo
            ImageIcon logoIcon = new ImageIcon(logoURL);
            JLabel logoLabel = new JLabel(logoIcon);
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoPanel.add(logoLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel combining registration and logo panels with padding for better spacing
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false); // Transparent to show frame background
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.insets = new Insets(20, 20, 20, 20); // Adjust padding for spacing between components
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainPanel.add(registrationPanel, mainGbc);
        mainGbc.gridx = 1;
        mainPanel.add(logoPanel, mainGbc);

        add(mainPanel);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (username.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (registerUser(username, name, email, password)) {
                    JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new LoginPage().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Registration Failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> {
            dispose(); // Close the registration page
            new LoginPage().setVisible(true); // Open the login page
        });

        setVisible(true);
    }

    private boolean registerUser(String username, String name, String email, String password) {
        String sql = "INSERT INTO users (username, name, email, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationPage::new);
    }
}
