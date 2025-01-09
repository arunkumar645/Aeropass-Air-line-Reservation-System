import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Welcome to Aeropass");
        setSize(400, 300); // Set size of the Main Menu window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Set light blue background color
        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230)); // Light blue color

        JLabel welcomeLabel = new JLabel("Welcome to Aeropass!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE); // Set text color to white for better visibility
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the same size for all buttons
        Dimension buttonSize = new Dimension(200, 40); // Set the width to 200px and height to 40px

        // Create and add the Flights button
        JButton flightsButton = new JButton("Flights");
        flightsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        flightsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        flightsButton.setPreferredSize(buttonSize); // Set size of button
        flightsButton.setMaximumSize(buttonSize); // Ensure all buttons are the same size
        flightsButton.addActionListener(e -> {
            new FlightSelectionPage().setVisible(true);  // Open the FlightSelectionPage
            dispose();  // Close the MainMenu window
        });

        // Create and add the AI Chat Assistance button
        JButton aiChatButton = new JButton("AI Chat Assistance");
        aiChatButton.setFont(new Font("Arial", Font.PLAIN, 14));
        aiChatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiChatButton.setPreferredSize(buttonSize); // Set size of button
        aiChatButton.setMaximumSize(buttonSize); // Ensure all buttons are the same size
        aiChatButton.addActionListener(e -> {
            new AIChatAssistancePage().setVisible(true);  // Open the AIChatAssistancePage
            dispose();  // Close the MainMenu window
        });

        // Create and add the About Us button
        JButton aboutUsButton = new JButton("About Us");
        aboutUsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        aboutUsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutUsButton.setPreferredSize(buttonSize); // Set size of button
        aboutUsButton.setMaximumSize(buttonSize); // Ensure all buttons are the same size
        aboutUsButton.addActionListener(e -> {
            new AboutUsPage().setVisible(true);  // Open the AboutUsPage
            dispose();  // Close the MainMenu window
        });

        // Create and add the Instructions button
        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsButton.setPreferredSize(buttonSize); // Set size of button
        instructionsButton.setMaximumSize(buttonSize); // Ensure all buttons are the same size
        instructionsButton.addActionListener(e -> {
            new InstructionsPage().setVisible(true);  // Open the InstructionsPage
            dispose();  // Close the MainMenu window
        });

        // Use BoxLayout to arrange the components vertically
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue()); // Add space before
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(20)); // Add some spacing
        panel.add(flightsButton);
        panel.add(Box.createVerticalStrut(10)); // Add some spacing
        panel.add(aiChatButton);
        panel.add(Box.createVerticalStrut(10)); // Add some spacing
        panel.add(aboutUsButton);
        panel.add(Box.createVerticalStrut(10)); // Add some spacing
        panel.add(instructionsButton);
        panel.add(Box.createVerticalGlue()); // Add space after

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
