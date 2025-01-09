import javax.swing.*;
import java.awt.*;

public class LuggageTransportPage extends JFrame {

    private String from;
    private String to;
    private int parkingPrice;

    public LuggageTransportPage(String from, String to, int parkingPrice) {
        this.from = from;
        this.to = to;
        this.parkingPrice = parkingPrice;

        setTitle("Luggage Transport");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color

        // Create the main panel for the content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false); // Make the content panel transparent to show background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel luggageLabel = new JLabel("You have booked the parking slot, so AeroPass offers free luggage transport.");
        luggageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(luggageLabel, gbc);

        JButton bookButton = new JButton("Free Luggage Transport");
        bookButton.setPreferredSize(new Dimension(200, 30));
        bookButton.addActionListener(e -> {
            new PaymentPage(from, to, parkingPrice).setVisible(true); // Open PaymentPage
            dispose(); // Close the current page
        });
        gbc.gridy = 1;
        contentPanel.add(bookButton, gbc);

        // Add the content panel on top of the background
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LuggageTransportPage("defaultFrom", "defaultTo", 0);
    }
}
