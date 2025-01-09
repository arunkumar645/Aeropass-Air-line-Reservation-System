import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashMap;

public class AIChatAssistancePage extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private final StringBuilder conversationHistory;
    private int messageCount = 0;
    private final int MAX_MESSAGES = 90;

    private final HashMap<String, String> knowledgeBase;

    public AIChatAssistancePage() {
        setTitle("Aero Chat Assistance");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Initialize the knowledge base
        knowledgeBase = new HashMap<>();
        populateKnowledgeBase();

        // Create the custom menu bar with back arrow icon
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(); // Empty menu to hold the back arrow
        menuBar.add(menu); // Add empty menu to the left side

        // Load back arrow icon from URL
        try {
            URL arrowUrl = new URL("https://i0.wp.com/www.dailyfreepsd.com/wp-content/uploads/2013/06/Blue-round-buttonleft-arrow-vector.jpg?fit=1279%2C1280&ssl=1");
            ImageIcon backArrowIcon = new ImageIcon(new ImageIcon(arrowUrl).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
            JLabel backArrowLabel = new JLabel(backArrowIcon);

            backArrowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            backArrowLabel.setToolTipText("Back to Main Menu");

            // Add click event to the back arrow label
            backArrowLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose(); // Close this window
                    new MainMenu(); // Open MainMenu
                }
            });

            menu.add(backArrowLabel); // Add back arrow to the menu
        } catch (Exception e) {
            e.printStackTrace();
        }

        setJMenuBar(menuBar); // Set custom menu bar as frame's menu bar

        // Set up the main panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Chat area to display conversation
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input field for user questions
        inputField = new JTextField();
        inputField.addActionListener(new SendMessageListener());

        // Send button
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendMessageListener());

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        // Input panel with input field, back button, and send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(sendButton);

        inputPanel.add(buttonPanel, BorderLayout.EAST);

        // Initialize conversation history manager
        conversationHistory = new StringBuilder();

        // Add components to the main panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    // Listener class for handling user messages and bot responses
    private class SendMessageListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userMessage = inputField.getText().trim();
            if (!userMessage.isEmpty()) {
                addMessage("You: " + userMessage);
                inputField.setText("");
                String botResponse = getBotResponse(userMessage);
                addMessage("Aero: " + botResponse);
            }
        }
    }

    // Method to add messages to the chat area and handle message count
    private void addMessage(String message) {
        if (messageCount >= MAX_MESSAGES) {
            // If message count exceeds max, clear the oldest message
            conversationHistory.delete(0, conversationHistory.indexOf("\n") + 1);
        } else {
            messageCount++;
        }
        conversationHistory.append(message).append("\n");
        chatArea.setText(conversationHistory.toString());
    }

    // Method to generate responses dynamically based on user input
    private String getBotResponse(String message) {
        message = message.toLowerCase();
        for (String key : knowledgeBase.keySet()) {
            if (message.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        return "I'm here to assist with any questions about Aeropass. Could you ask something specific about Aeropass?";
    }

    // Populate the knowledge base with questions and responses
    private void populateKnowledgeBase() {
        knowledgeBase.put("what is aeropass", "Aeropass is a loyalty program that offers benefits like discounts on flights, priority boarding, and more.");
        knowledgeBase.put("benefits", "Aeropass provides members with exclusive discounts, early boarding, extra baggage, and access to airport lounges.");
        knowledgeBase.put("how to join", "You can join Aeropass by visiting our website and signing up through the registration page.");
        knowledgeBase.put("sign up", "You can join Aeropass by visiting our website and signing up through the registration page.");
        knowledgeBase.put("cost", "The Aeropass program has multiple membership tiers, each with different benefits. Please check our website for pricing details.");
        knowledgeBase.put("price", "The Aeropass program has multiple membership tiers, each with different benefits. Please check our website for pricing details.");
        knowledgeBase.put("who created aeropass", "Aeropass was created by Arun.");
        knowledgeBase.put("About Laguage assistence","When You book parking slot along with laguage assistence freely provided by aeropass");
        knowledgeBase.put("About Parking slot","Aeropass has a unique feature when compare to other websites and that unique feature is to book parking slot along with the flight");
        // Add more key phrases and answers as needed
    }

    public static void main(String[] args) {
        new AIChatAssistancePage();
    }
}
