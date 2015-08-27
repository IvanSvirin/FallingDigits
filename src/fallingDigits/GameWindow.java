package fallingDigits;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ivan on 20.06.2015.
 */
public class GameWindow extends JFrame {
    GameFieldPanel gameFieldPanel;
    GameWindow(Field field) {
        gameFieldPanel = new GameFieldPanel(field);
    }
    JPanel bottomPanel = new JPanel();
    JButton left = new JButton("left");
    JButton right = new JButton("right");

    public void init() {
        setSize(517, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Falling Digits");
        setLayout(new BorderLayout());
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(left);
        bottomPanel.add(right);
        add(gameFieldPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}

class GameFieldPanel extends JPanel {
    Field field;

    GameFieldPanel(Field field) {
        this.field = field;
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < 13; i++) {

            g.drawLine(0, i * 50, 500, i * 50);

        }
        for (int i = 0; i < 11; i++) {
            g.drawLine(i * 50, 0, i * 50, 600);
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 12; j++) {
                if (field.cells[i][j].cellImage != null) field.cells[i][j].draw(g, (i - 1) * 50, j * 50);
            }
        }

    }
}
