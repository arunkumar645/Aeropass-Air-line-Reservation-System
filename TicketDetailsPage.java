import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DatabaseConnection.DatabaseConnection;

public class TicketDetailsPage extends JFrame {

    public TicketDetailsPage(String name, String email, String phone, int tickets, String passport, int price) {
        setTitle("Ticket Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Set light blue background color
        getContentPane().setBackground(new Color(173, 216, 230));

        // Define the font for labels
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Display ticket details
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel("Name: " + name, labelFont), gbc);
        gbc.gridy++;
        add(createLabel("Email: " + email, labelFont), gbc);
        gbc.gridy++;
        add(createLabel("Phone Number: " + phone, labelFont), gbc);
        gbc.gridy++;
        add(createLabel("Number of Tickets: " + tickets, labelFont), gbc);
        gbc.gridy++;
        add(createLabel("Passport Number: " + passport, labelFont), gbc);
        gbc.gridy++;
        add(createLabel("Total Price: ₹" + price, labelFont), gbc);

        // Save details to database
        saveToDatabase(name, email, phone, tickets, passport, price);

        // Buttons Panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> {
            new PaymentGatewayPage(price); // Pass the price to PaymentGatewayPage
            dispose(); // Close TicketDetailsPage
        });
        buttonPanel.add(doneButton);

        add(buttonPanel, gbc);

        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private void saveToDatabase(String name, String email, String phone, int tickets, String passport, int price) {
        String insertQuery = "INSERT INTO ticket_details (name, email, phone, tickets, passport, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setInt(4, tickets);
            preparedStatement.setString(5, passport);
            preparedStatement.setInt(6, price);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ticket details saved successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving details to the database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TicketDetailsPage("John Doe", "john@example.com", "1234567890", 2, "A1234567", 5000);
    }
}
