package fallingDigits;

/**
 * Created by ivan on 20.06.2015.
 */
public class Field {
    public Cell[][] cells = new Cell[12][13];

    Field() {
        init();
    }

    void init() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 13; j++) {
                cells[i][j] = new Cell(i, j, null, 0);
                if (i == 0 || i == 11 || j == 12) cells[i][j].cellValue = 1;
            }
        }
    }

}
