import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;

public class AboutUsPage extends JFrame {

    public AboutUsPage() {
        setTitle("About Us");
        setSize(400, 300); // Set size of the About Us window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // HTML description about Aeropass
        JLabel aboutLabel = new JLabel("<html><center><h2>About Aeropass</h2>"
                + "<p>Aeropass is a premium loyalty program offering exclusive travel benefits.</p>"
                + "<p>Basically, Aeropass offers two flights: Domestic and Business flights, and it offers parking booking as well.</p>"
                + "<p>Members enjoy priority boarding, discounted fares, and more.</p></center></html>");
        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(aboutLabel, BorderLayout.NORTH);

        // Social media panel
        JPanel socialMediaPanel = new JPanel();

        // Instagram icon and link
        JLabel instagramLabel = createSocialMediaLabel("https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Instagram_logo_2022.svg/1200px-Instagram_logo_2022.svg.png", "https://www.instagram.com/codinghub713/");
        socialMediaPanel.add(instagramLabel);

        // Twitter icon and link
        JLabel twitterLabel = createSocialMediaLabel("https://cdn.punchng.com/wp-content/uploads/2023/07/24084806/Twitter-new-logo.jpeg", "https://www.twitter.com");
        socialMediaPanel.add(twitterLabel);

        // Facebook icon and link
        JLabel facebookLabel = createSocialMediaLabel("https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Facebook_logo_36x36.svg/1200px-Facebook_logo_36x36.svg.png", "https://www.facebook.com");
        socialMediaPanel.add(facebookLabel);

        // Snapchat icon and link
        JLabel snapchatLabel = createSocialMediaLabel("https://th.bing.com/th/id/OIP.ZXD0bFXkQ0zescoxr-Vj1AAAAA?rs=1&pid=ImgDetMain", "https://accounts.snapchat.com/v2/welcome");
        socialMediaPanel.add(snapchatLabel);

        panel.add(socialMediaPanel, BorderLayout.CENTER);

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

    private JLabel createSocialMediaLabel(String imageUrl, String link) {
        // Create image icon from URL with larger size
        JLabel label = new JLabel();
        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)); // Increased size to 50x50
            label.setIcon(icon);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Add click event to open link
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(link));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return label;
    }

    public static void main(String[] args) {
        new AboutUsPage();
    }
}
