import javax.swing.*;
import java.awt.*;

public class PaymentPage extends JFrame {
    private String from;
    private String to;
    private int parkingPrice;

    private JTextField creditCardField;
    private JTextField expiryDateField;
    private JTextField cvvField;

    public PaymentPage(String from, String to, int parkingPrice) {
        this.from = from;
        this.to = to;
        this.parkingPrice = parkingPrice;

        setTitle("Payment Gateway");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.anchor = GridBagConstraints.WEST;

        // Title Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Payment Gateway", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // From Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel("From:", labelFont), gbc);
        gbc.gridx = 1;
        JLabel fromLabel = new JLabel(from);
        fromLabel.setFont(labelFont);
        add(fromLabel, gbc);

        // To Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createLabel("To:", labelFont), gbc);
        gbc.gridx = 1;
        JLabel toLabel = new JLabel(to);
        toLabel.setFont(labelFont);
        add(toLabel, gbc);

        // Parking Price Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(createLabel("Parking Price:", labelFont), gbc);
        gbc.gridx = 1;
        JLabel parkingPriceLabel = new JLabel(String.valueOf(parkingPrice));
        parkingPriceLabel.setFont(labelFont);
        add(parkingPriceLabel, gbc);

        // Credit Card Number Field
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(createLabel("Credit Card Number:", labelFont), gbc);
        gbc.gridx = 1;
        creditCardField = new JTextField(16);
        add(creditCardField, gbc);

        // Expiry Date Field
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(createLabel("Expiry Date (MM/YY):", labelFont), gbc);
        gbc.gridx = 1;
        expiryDateField = new JTextField(5);
        add(expiryDateField, gbc);

        // CVV Field
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(createLabel("CVV:", labelFont), gbc);
        gbc.gridx = 1;
        cvvField = new JTextField(3);
        add(cvvField, gbc);

        // Pay Button
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JButton payButton = new JButton("Pay Now");
        payButton.addActionListener(e -> {
            if (validateFields()) {
                // Implement payment processing logic here
                JOptionPane.showMessageDialog(this, "Payment Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // After showing the payment success message, redirect to FeedbackPage
                new FeedbackPage();
                dispose(); // Close the payment page
            }
        });
        add(payButton, gbc);

        setVisible(true);
    }

    private boolean validateFields() {
        if (creditCardField.getText().trim().isEmpty() || expiryDateField.getText().trim().isEmpty() ||
                cvvField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!creditCardField.getText().matches("\\d{16}")) {
            JOptionPane.showMessageDialog(this, "Invalid credit card number", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!expiryDateField.getText().matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            JOptionPane.showMessageDialog(this, "Invalid expiry date format", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cvvField.getText().matches("\\d{3}")) {
            JOptionPane.showMessageDialog(this, "Invalid CVV", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    public static void main(String[] args) {
        // For testing, run the page with example data
        new PaymentPage("Hyderabad", "Mumbai", 300);
    }
}
