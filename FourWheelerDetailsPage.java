import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FourWheelerDetailsPage extends JFrame {

    private JTextField vehicleNumberField, modelField, hoursField;
    private JLabel priceLabel;
    private String from, to;

    public FourWheelerDetailsPage(String from, String to) {
        this.from = from;
        this.to = to;

        setTitle("Four Wheeler Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load background image
        JLabel backgroundLabel = new JLabel();
        try {
            URL imageURL = new URL("https://images.cars.com/cldstatic/wp-content/uploads/19_June_New-Car_Deals_PD.jpg");
            ImageIcon backgroundImageIcon = new ImageIcon(imageURL);
            Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setLayout(new BorderLayout());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentPane(backgroundLabel);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false); // Make the panel background transparent to show the background image
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Add labels and text fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(createLabel("Vehicle Number:"), gbc);
        gbc.gridx = 1;
        vehicleNumberField = new JTextField(15);
        mainPanel.add(vehicleNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(createLabel("Model:"), gbc);
        gbc.gridx = 1;
        modelField = new JTextField(15);
        mainPanel.add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(createLabel("How many hours:"), gbc);
        gbc.gridx = 1;
        hoursField = new JTextField(15);
        mainPanel.add(hoursField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(createLabel("Price:"), gbc);
        gbc.gridx = 1;
        priceLabel = new JLabel();
        mainPanel.add(priceLabel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);

        JButton dontNeedButton = new JButton("Don't Need");
        dontNeedButton.setPreferredSize(new Dimension(100, 30));
        dontNeedButton.addActionListener(e -> {
            new ParkingSlotPage(from, to);
            dispose();
        });
        buttonPanel.add(dontNeedButton);

        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 30));
        okButton.addActionListener(e -> {
            int hours;
            try {
                hours = Integer.parseInt(hoursField.getText());
                if (hours > 5) {
                    JOptionPane.showMessageDialog(this, "No, you are not allowed to park for more than 5 hours", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int price = hours * 200; // Calculate the price based on hours (200 per hour for four-wheeler)
                    priceLabel.setText(String.valueOf(price));
                    JOptionPane.showMessageDialog(this, "Ok, you can proceed. Price: " + price, "Information", JOptionPane.INFORMATION_MESSAGE);
                    new LuggageTransportPage(from, to, price).setVisible(true);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of hours", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(okButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    public static void main(String[] args) {
        new FourWheelerDetailsPage("defaultFrom", "defaultTo");
    }
}
