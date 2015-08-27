package fallingDigits;

import java.awt.*;

/**
 * Created by ivan on 20.06.2015.
 */
public class Cell {
    int x;
    int y;
    public Image cellImage;
    public int cellValue = 0;
    public Cell(int x, int y, Image cellImage, int cellValue) {
        this.cellImage = cellImage;
        this.cellValue = cellValue;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g,int x,int y) {
        g.drawImage(cellImage,x,y,null);
    }
}
