import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

class FeedbackPage extends JFrame {
    public FeedbackPage() {
        setTitle("Feedback");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load background image
        JLabel backgroundLabel = new JLabel();
        try {
            URL imageURL = new URL("https://media.istockphoto.com/id/1306480742/vector/user-reviews-and-feedback-concept.jpg?s=612x612&w=0&k=20&c=7Dcc3LW5sAhXUKKUJWB2JMgSuFWI4ebV-Ac8lc4Wjno="); // Replace with your image URL
            ImageIcon backgroundImageIcon = new ImageIcon(imageURL);
            Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
            backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setLayout(new BorderLayout());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentPane(backgroundLabel);

        JLabel titleLabel = new JLabel("We value your feedback!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK); // Set the title text color to black
        add(titleLabel, BorderLayout.NORTH);

        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new GridLayout(5, 2, 10, 10));
        feedbackPanel.setOpaque(false); // Make the panel background transparent

        // Name field - only allow letters
        feedbackPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(20);
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignore non-alphabetic input
                }
            }
        });
        feedbackPanel.add(nameField);

        // Email field - simple validation
        feedbackPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField(20);
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String email = emailField.getText();
                if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    emailField.setForeground(Color.RED); // Invalid email
                } else {
                    emailField.setForeground(Color.BLACK); // Valid email
                }
            }
        });
        feedbackPanel.add(emailField);

        // Rating field
        feedbackPanel.add(new JLabel("Rating (1-5):"));
        JComboBox<String> ratingComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        feedbackPanel.add(ratingComboBox);

        // Comments field - only allow alphabetic input
        feedbackPanel.add(new JLabel("Comments:"));
        JTextArea commentsArea = new JTextArea(3, 20);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        commentsArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignore non-alphabetic characters
                }
            }
        });
        feedbackPanel.add(new JScrollPane(commentsArea));

        add(feedbackPanel, BorderLayout.CENTER);

        // Submit button
        JButton submitButton = new JButton("Submit Feedback");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String rating = (String) ratingComboBox.getSelectedItem();
                String comments = commentsArea.getText();

                // Validate inputs
                if (name.isEmpty() || email.isEmpty() || rating == null || comments.isEmpty()) {
                    JOptionPane.showMessageDialog(FeedbackPage.this, 
                            "All fields are required!", 
                            "Validation Error", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Simple email validation
                if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    JOptionPane.showMessageDialog(FeedbackPage.this, 
                            "Please enter a valid email address!", 
                            "Validation Error", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Show a message confirming feedback submission
                JOptionPane.showMessageDialog(FeedbackPage.this, 
                        "Thank you, " + name + ", for your feedback!", 
                        "Feedback Submitted", 
                        JOptionPane.INFORMATION_MESSAGE);

                // You can add logic here to store feedback in a file or database

                // Close the feedback page after submission
                dispose();
            }
        });

        add(submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // For testing, create a FeedbackPage instance
        new FeedbackPage();
    }
}
