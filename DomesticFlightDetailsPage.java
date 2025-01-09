import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DomesticFlightDetailsPage extends JFrame {
    private JTextField nameField, emailField, phoneNumberField, ticketsField, passportNumberField;
    private JLabel priceLabel;
    private boolean destinationSelected = false;
    private String destinationFrom = "";
    private String destinationTo = "";

    public DomesticFlightDetailsPage() {
        setTitle("Domestic Flight Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel("Name:", labelFont), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);
        nameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    e.consume();
                }
            }
        });

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel("Email:", labelFont), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createLabel("Phone Number:", labelFont), gbc);
        gbc.gridx = 1;
        phoneNumberField = new JTextField(20);
        add(phoneNumberField, gbc);
        phoneNumberField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || phoneNumberField.getText().length() >= 10) {
                    e.consume();
                }
            }
        });

        // Number of Tickets
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(createLabel("Number of Tickets:", labelFont), gbc);
        gbc.gridx = 1;
        ticketsField = new JTextField(5);
        add(ticketsField, gbc);
        ticketsField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
            public void keyReleased(KeyEvent e) {
                try {
                    int tickets = Integer.parseInt(ticketsField.getText());
                    if (tickets > 5) {
                        JOptionPane.showMessageDialog(DomesticFlightDetailsPage.this, "Sorry, a person can book only 5 tickets.", "Error", JOptionPane.ERROR_MESSAGE);
                        ticketsField.setText("");
                    } else if (!destinationSelected) {
                        JOptionPane.showMessageDialog(DomesticFlightDetailsPage.this, "Please select a destination first.", "Error", JOptionPane.ERROR_MESSAGE);
                        ticketsField.setText("");
                    } else {
                        int price = tickets * 1000;
                        priceLabel.setText("Price: " + price);
                    }
                } catch (NumberFormatException ignored) {}
            }
        });

        // Passport Number
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(createLabel("Passport Number:", labelFont), gbc);
        gbc.gridx = 1;
        passportNumberField = new JTextField(20);
        add(passportNumberField, gbc);
        passportNumberField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });

        // Price Label
        gbc.gridx = 0;
        gbc.gridy = 5;
        priceLabel = new JLabel("Price: ");
        add(priceLabel, gbc);

        // Hyperlink for parking slot
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JLabel parkingLink = new JLabel("<html><u>Do you want to book a parking slot?</u></html>");
        parkingLink.setForeground(Color.BLUE.darker());
        parkingLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        parkingLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ParkingSlotPage(destinationFrom, destinationTo); // Pass parameters
                dispose();
            }
        });
        add(parkingLink, gbc);

        // Buttons Panel
        gbc.gridy = 7;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);

        JButton selectDestinationButton = new JButton("Select Destination");
        selectDestinationButton.addActionListener(e -> new DestinationSelectionPage(this));
        buttonPanel.add(selectDestinationButton);

        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            if (validateFields() && destinationSelected) {
                int tickets = Integer.parseInt(ticketsField.getText());
                int price = tickets * 1000;
                
                // Passing the entered details to the TicketDetailsPage
                new TicketDetailsPage(
                    nameField.getText(),
                    emailField.getText(),
                    phoneNumberField.getText(),
                    tickets,
                    passportNumberField.getText(),
                    price
                );
                dispose();
            } else if (!destinationSelected) {
                JOptionPane.showMessageDialog(DomesticFlightDetailsPage.this, "Please select a destination first.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(proceedButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new FlightSelectionPage();
            dispose();
        });
        buttonPanel.add(backButton);
        add(buttonPanel, gbc);

        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private boolean validateFields() {
        if (nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() ||
                phoneNumberField.getText().trim().isEmpty() || ticketsField.getText().trim().isEmpty() ||
                passportNumberField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!emailField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (phoneNumberField.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 digits", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void setDestination(String from, String to) {
        this.destinationFrom = from;
        this.destinationTo = to;
        this.destinationSelected = true;
        JOptionPane.showMessageDialog(this, "Destination selected: " + from + " to " + to, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new DomesticFlightDetailsPage();
    }
}
