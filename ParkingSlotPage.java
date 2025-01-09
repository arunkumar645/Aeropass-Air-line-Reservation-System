import javax.swing.*;
import java.awt.*;

public class ParkingSlotPage extends JFrame {

    private String from;
    private String to;

    public ParkingSlotPage(String from, String to) {
        this.from = from;
        this.to = to;

        setTitle("Parking Slot Booking");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set light blue background color
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color

        // Create the main panel
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setOpaque(false); // Make the panel background transparent

        // Radio buttons for vehicle selection
        JRadioButton twoWheelerButton = new JRadioButton("Two Wheeler");
        JRadioButton fourWheelerButton = new JRadioButton("Four Wheeler");

        // Set background color for radio buttons
        twoWheelerButton.setBackground(new Color(173, 216, 230));
        fourWheelerButton.setBackground(new Color(173, 216, 230));

        // Add vehicle buttons to a group
        ButtonGroup vehicleGroup = new ButtonGroup();
        vehicleGroup.add(twoWheelerButton);
        vehicleGroup.add(fourWheelerButton);

        mainPanel.add(twoWheelerButton);
        mainPanel.add(fourWheelerButton);

        // Create the next button
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(100, 30));
        nextButton.addActionListener(e -> {
            if (twoWheelerButton.isSelected()) {
                // Navigate to TwoWheelerDetailsPage (assuming this exists)
                new TwoWheelerDetailsPage(from, to);
            } else if (fourWheelerButton.isSelected()) {
                // Navigate to FourWheelerDetailsPage (assuming this exists)
                new FourWheelerDetailsPage(from, to);
            }
            dispose();
        });

        // Create the back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> {
            new DomesticFlightDetailsPage(); // Assuming this exists
            dispose();
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(173, 216, 230)); // Light blue color
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        // Add panels to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ParkingSlotPage("defaultFrom", "defaultTo");
    }
}
