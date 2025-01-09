import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DestinationSelectionPage extends JFrame {
    private JComboBox<String> fromComboBox, toComboBox;
    private DomesticFlightDetailsPage parent;

    public DestinationSelectionPage(DomesticFlightDetailsPage parent) {
        this.parent = parent;

        setTitle("Select Destination");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Hide parent page while this page is open
        parent.setVisible(false);

        // Load background image
        JLabel backgroundLabel = new JLabel();
        try {
            URL imageURL = new URL("https://pixnio.com/free-images/2017/05/14/2017-05-14-15-18-07.jpg"); // Replace with your image URL
            ImageIcon backgroundImageIcon = new ImageIcon(imageURL);
            Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setLayout(new BorderLayout());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentPane(backgroundLabel);

        // Title label
        JLabel titleLabel = createStyledLabel("Select Your Journey:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        String[] places = {"Goa", "Hyderabad", "Mumbai", "Karnataka", "Kerala", "Dubai", "Gokarna", "Maharashtra", "Chennai", "Ladakh", "Munnar", "Andhra Pradesh", "Vizag", "Arunachal Pradesh", "America", "Australia"};
        fromComboBox = new JComboBox<>(places);
        toComboBox = new JComboBox<>(places);
        fromComboBox.setPreferredSize(new Dimension(200, 30));
        toComboBox.setPreferredSize(new Dimension(200, 30));

        JPanel comboBoxPanel = new JPanel(new FlowLayout());
        comboBoxPanel.setOpaque(false);
        comboBoxPanel.add(fromComboBox);
        comboBoxPanel.add(createStyledLabel(" to "));
        comboBoxPanel.add(toComboBox);

        inputPanel.add(comboBoxPanel, gbc);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);

        JButton selectDestinationButton = new JButton("Select Destination");
        selectDestinationButton.setPreferredSize(new Dimension(200, 40));
        selectDestinationButton.addActionListener(e -> {
            String from = (String) fromComboBox.getSelectedItem();
            String to = (String) toComboBox.getSelectedItem();
            parent.setDestination(from, to); // Set the selected destination in parent page
            parent.setVisible(true); // Show the parent page again
            dispose(); // Close this page
        });
        buttonPanel.add(selectDestinationButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        return label;
    }
}
