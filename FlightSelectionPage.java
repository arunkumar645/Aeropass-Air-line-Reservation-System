import BusinessFlightDetailsPage.BusinessFlightDetailsPage;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FlightSelectionPage extends JFrame {

    public FlightSelectionPage() {
        setTitle("AeroPass");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Set background image
        try {
            URL url = new URL("https://wallpaperaccess.com/full/254367.png"); // Replace with the URL of your image
            JLabel background = new JLabel(new ImageIcon(url));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(new ImageIcon(url).getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add title label
        JLabel title = new JLabel("The AeroPass offers two flights:", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE); // Use dark gray text color for contrast
        add(title, BorderLayout.NORTH);

        // Create panel for the buttons with a transparent background
        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false); // Transparent panel to show background image
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        // Create buttons with light red background color and smaller size
        Dimension buttonSize = new Dimension(150, 30);

        JButton domesticButton = new JButton("Domestic Flights");
        domesticButton.setFont(new Font("Arial", Font.PLAIN, 14));
        domesticButton.setBackground(new Color(255, 182, 193)); // Light red color
        domesticButton.setPreferredSize(buttonSize);
        domesticButton.setMaximumSize(buttonSize);

        JButton businessButton = new JButton("Business Flights");
        businessButton.setFont(new Font("Arial", Font.PLAIN, 14));
        businessButton.setBackground(new Color(255, 182, 193)); // Light red color
        businessButton.setPreferredSize(buttonSize);
        businessButton.setMaximumSize(buttonSize);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(new Color(255, 182, 193)); // Light red color
        backButton.setPreferredSize(buttonSize);
        backButton.setMaximumSize(buttonSize);

        // Add button actions
        domesticButton.addActionListener(e -> {
            new DomesticFlightDetailsPage();
            dispose();
        });
        businessButton.addActionListener(e -> {
            new BusinessFlightDetailsPage();
            dispose();
        });
        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        // Add buttons to panel with spacing
        domesticButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        businessButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(domesticButton);
        optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(businessButton);
        optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(backButton);

        // Adjust the panel to move buttons 8% to the right
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        JPanel offsetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10)); // Adjust the insets for 8% right
        offsetPanel.setOpaque(false);
        offsetPanel.add(optionsPanel);
        buttonPanel.add(offsetPanel, BorderLayout.WEST);

        // Add the panel to the center of the layout
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightSelectionPage();
    }
}
