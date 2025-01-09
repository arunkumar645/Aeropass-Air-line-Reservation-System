import javax.swing.*;
import java.awt.*;

public class ThankYouPage extends JFrame {

    public ThankYouPage() {
        setTitle("Thank You");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel thankYouLabel = new JLabel("Thank you for booking with AeroPass!", JLabel.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 26));
        add(thankYouLabel, BorderLayout.CENTER);

        JButton feedbackButton = new JButton("OK");
        feedbackButton.addActionListener(e -> {
            new FeedbackPage();
            dispose();
        });

        add(feedbackButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}
