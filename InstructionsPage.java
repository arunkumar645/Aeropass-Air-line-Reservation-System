import javax.swing.*;
import java.awt.*;

public class InstructionsPage extends JFrame {

    public InstructionsPage() {
        setTitle("Instructions");
        setSize(400, 400); // Adjusted size of the Instructions window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        // HTML content for instructions
        JLabel instructionsLabel = new JLabel("<html><body style='text-align: center;'>"
                + "<h2>Flight Reservation Instructions</h2>"
                + "<ul style='text-align: left;'>"
                + "<li><b>Step 1:</b> Enter your departure and destination cities.</li>"
                + "<li><b>Step 2:</b> Select your travel dates for departure and return (if applicable).</li>"
                + "<li><b>Step 3:</b> Choose the number of passengers and cabin class (Economy, Business, or First Class).</li>"
                + "<li><b>Step 4:</b> Review available flight options and select the one that best suits your needs.</li>"
                + "<li><b>Step 5:</b> Enter passenger details, including full name and contact information.</li>"
                + "<li><b>Step 6:</b> Review your booking and proceed to the payment section to complete the reservation.</li>"
                + "<li><b>Step 7:</b> After payment, you will receive a confirmation email with your booking details and e-ticket.</li>"
                + "</ul>"
                + "<p>Note: Make sure all information is accurate to avoid issues during check-in and boarding.</p>"
                + "</body></html>");
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionsLabel.setVerticalAlignment(SwingConstants.CENTER);

        panel.add(instructionsLabel, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new InstructionsPage();
    }
}
