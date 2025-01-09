import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

public class PaymentGatewayPage extends JFrame {

    private int totalPrice;

    public PaymentGatewayPage(int totalPrice) {
        this.totalPrice = totalPrice;

        setTitle("AeroPass Payment Page");
        setLayout(new BorderLayout(10, 10));
        
        // Common font for labels and fields
        Font commonFont = new Font("Arial", Font.PLAIN, 14);

        // Panel for payment method selection
        JPanel paymentMethodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel paymentLabel = new JLabel("Select Payment Method:");
        JRadioButton upiButton = new JRadioButton("UPI");
        JRadioButton creditCardButton = new JRadioButton("Credit Card");
        JRadioButton debitCardButton = new JRadioButton("Debit Card");

        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(upiButton);
        paymentGroup.add(creditCardButton);
        paymentGroup.add(debitCardButton);

        paymentMethodPanel.add(paymentLabel);
        paymentMethodPanel.add(upiButton);
        paymentMethodPanel.add(creditCardButton);
        paymentMethodPanel.add(debitCardButton);

        // Dynamic panel to display additional fields
        JPanel dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new BoxLayout(dynamicPanel, BoxLayout.Y_AXIS));

        // UPI options
        JPanel upiPanel = new JPanel();
        upiPanel.setLayout(new BoxLayout(upiPanel, BoxLayout.Y_AXIS));
        JRadioButton phonePeButton = new JRadioButton("PhonePe");
        JRadioButton googlePayButton = new JRadioButton("Google Pay");
        JRadioButton paytmButton = new JRadioButton("Paytm");
        JRadioButton bhimUPIButton = new JRadioButton("BHIM UPI");

        ButtonGroup upiGroup = new ButtonGroup();
        upiGroup.add(phonePeButton);
        upiGroup.add(googlePayButton);
        upiGroup.add(paytmButton);
        upiGroup.add(bhimUPIButton);

        upiPanel.add(new JLabel("Select UPI App:"));
        upiPanel.add(phonePeButton);
        upiPanel.add(googlePayButton);
        upiPanel.add(paytmButton);
        upiPanel.add(bhimUPIButton);

        // Card details fields
        JPanel cardPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        cardPanel.add(new JLabel("Card Number:"));
        JTextField cardNumberField = new JTextField(16);
        cardNumberField.setDocument(new OnlyNumbersDocument());
        cardPanel.add(cardNumberField);

        cardPanel.add(new JLabel("Card Holder Name:"));
        JTextField holderNameField = new JTextField(20);
        holderNameField.setDocument(new OnlyAlphabetsDocument());
        cardPanel.add(holderNameField);

        cardPanel.add(new JLabel("CVV:"));
        JPasswordField cvvField = new JPasswordField(3);
        cvvField.setDocument(new CVVDocument());
        cardPanel.add(cvvField);

        // Expiry date panel
        JPanel expPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        expPanel.add(new JLabel("Expiry Date (MM/YY):"));
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        JComboBox<String> monthComboBox = new JComboBox<>(months);
        String[] years = {"2024", "2025", "2026", "2027", "2028"};
        JComboBox<String> yearComboBox = new JComboBox<>(years);
        expPanel.add(monthComboBox);
        expPanel.add(yearComboBox);

        // Pay button
        JButton payButton = new JButton("Pay ₹" + totalPrice);
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.setBackground(new Color(0, 102, 204));
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);

        JLabel statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        statusLabel.setForeground(Color.GRAY);

        payButton.addActionListener(e -> {
            if (!upiButton.isSelected() && !creditCardButton.isSelected() && !debitCardButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select a payment method.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (upiButton.isSelected() && !phonePeButton.isSelected() && !googlePayButton.isSelected()
                    && !paytmButton.isSelected() && !bhimUPIButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select a UPI app.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ((creditCardButton.isSelected() || debitCardButton.isSelected())
                    && (cardNumberField.getText().trim().isEmpty()
                    || holderNameField.getText().trim().isEmpty()
                    || cvvField.getPassword().length != 3
                    || monthComboBox.getSelectedItem() == null
                    || yearComboBox.getSelectedItem() == null)) {
                JOptionPane.showMessageDialog(this, "Please fill in all card details.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                statusLabel.setText("Processing your payment...");
                new Timer(2000, event -> {
                    statusLabel.setText("Payment Successful!");
                    JOptionPane.showMessageDialog(this, "Payment Successfully Completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new FeedbackPage().setVisible(true);
                }).start();
            }
        });

        // Dynamic panel updates
        upiButton.addActionListener(e -> {
            dynamicPanel.removeAll();
            dynamicPanel.add(upiPanel);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        });
        creditCardButton.addActionListener(e -> {
            dynamicPanel.removeAll();
            dynamicPanel.add(cardPanel);
            dynamicPanel.add(expPanel);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        });
        debitCardButton.addActionListener(e -> {
            dynamicPanel.removeAll();
            dynamicPanel.add(cardPanel);
            dynamicPanel.add(expPanel);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        });

        // Layout configuration
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(payButton);
        buttonPanel.add(statusLabel);

        add(paymentMethodPanel, BorderLayout.NORTH);
        add(dynamicPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Custom document filters
    class OnlyNumbersDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str.matches("[0-9]+")) {
                super.insertString(offs, str, a);
            }
        }
    }

    class OnlyAlphabetsDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str.matches("[a-zA-Z ]+")) {
                super.insertString(offs, str, a);
            }
        }
    }

    class CVVDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str.matches("[0-9]{1,3}")) {
                super.insertString(offs, str, a);
            }
        }
    }

    public static void main(String[] args) {
        new PaymentGatewayPage(5000); // Example price
    }
}
