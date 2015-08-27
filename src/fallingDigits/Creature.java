package fallingDigits;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Created by ivan on 11.04.2015.
 */
public class Creature {
    public int value;
    public Image image;
    static String[] png = {"1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png", "9.png"};
    public Creature() {
        value = getValue();
        image = getImage(png[value - 1]);
    }

    public int getValue() {
        return new Random().nextInt(9) + 1;
    }

    public Image getImage(String path) {
        BufferedImage sourceImage = null;

        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
    }
}
