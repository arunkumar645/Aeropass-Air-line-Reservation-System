import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfettiPanel extends JPanel {
    private List<Confetti> confettiList;
    private Timer timer;

    public ConfettiPanel() {
        setOpaque(false);
        confettiList = new ArrayList<>();
        timer = new Timer(30, e -> {
            updateConfetti();
            repaint();
        });
        timer.start();
    }

    private void updateConfetti() {
        Random random = new Random();
        if (confettiList.size() < 200) {
            confettiList.add(new Confetti(random.nextInt(getWidth()), -10));
        }
        for (Confetti confetti : confettiList) {
            confetti.update();
        }
        confettiList.removeIf(confetti -> confetti.y > getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Confetti confetti : confettiList) {
            g2d.setColor(confetti.color);
            g2d.fillRect(confetti.x, confetti.y, confetti.size, confetti.size);
        }
    }

    private static class Confetti {
        int x, y, size;
        Color color;

        Confetti(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = new Random().nextInt(10) + 5;
            this.color = new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat());
        }

        void update() {
            y += new Random().nextInt(5) + 2;
        }
    }
}
