import javax.swing.*;
import java.awt.*;

public class PriceDetailsPage extends JFrame {

    public PriceDetailsPage(String from, String to, int numberOfTickets, int parkingPrice) {
        setTitle("Price Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Calculate the flight price based on the ticket quantity
        int flightPrice = calculateFlightPrice(from, to, numberOfTickets);
        int totalPrice = parkingPrice + flightPrice;

        // Create and display price details
        String priceDetailsText = "<html>Parking Amount: " + parkingPrice + "<br>" +
                                  "Flight Amount: " + flightPrice + "<br>" +
                                  "Total Amount: " + totalPrice + "</html>";
        JLabel priceDetailsLabel = new JLabel(priceDetailsText, JLabel.CENTER);
        priceDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(priceDetailsLabel, BorderLayout.CENTER);

        // Proceed to payment button
        JButton proceedButton = new JButton("Proceed to Payment");
        proceedButton.setPreferredSize(new Dimension(150, 40));
        proceedButton.addActionListener(e -> {
            new PaymentGatewayPage(totalPrice); // Pass the total price to PaymentGatewayPage
            dispose(); // Close the PriceDetailsPage
        });
        add(proceedButton, BorderLayout.SOUTH);

        // Set the frame visible
        setVisible(true);
    }

    PriceDetailsPage(String from, String to, int parkingPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // This method calculates the flight price based on the number of tickets
    private int calculateFlightPrice(String from, String to, int numberOfTickets) {
        // Example price per ticket
        int pricePerTicket = 1000; 
        return pricePerTicket * numberOfTickets;
    }

    public static void main(String[] args) {
        // Example to open the price details page with sample data
        new PriceDetailsPage("New York", "Los Angeles", 1, 200); // 1 ticket, 200 parking fee
    }
}
