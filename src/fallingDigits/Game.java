package fallingDigits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by ivan on 20.06.2015.
 */
public class Game implements Runnable {
    Field field;
    GameWindow gameWindow;
    Cell currentCell;
    boolean leftButtonPressed, rightButtonPressed;
    int targetSum = 10;

    public Game() {
        this.field = new Field();
        this.gameWindow = new GameWindow(field);
    }

    @Override
    public void run() {
        init();
        startGame();
    }

    void init() {
        gameWindow.init();
    }

    void startGame() {
        while (true) {
            Creature creature = new Creature();
            field.cells[5][0].cellImage = creature.image;
            field.cells[5][0].cellValue = creature.value;
            currentCell = field.cells[5][0];
            gameWindow.repaint();
            gameWindow.left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    leftButtonPressed = true;

                }
            });
            gameWindow.right.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rightButtonPressed = true;
                }
            });
            move();
            checkTargetSumAndErase();
        }
    }

    public void move() {
        while (isDownFree(currentCell)) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isLeftFree(currentCell) && leftButtonPressed) {
                moveLeft();
                leftButtonPressed = false;
            }
            if (isRightFree(currentCell) && rightButtonPressed) {
                moveRight();
                rightButtonPressed = false;
            }
            if (isDownFree(currentCell)) moveDown();
            gameWindow.repaint();
        }
    }

    boolean isDownFree(Cell cell) {
        if (field.cells[cell.x][cell.y + 1].cellValue == 0) return true;
        else return false;
    }

    boolean isRightFree(Cell cell) {
        if (field.cells[cell.x + 1][cell.y].cellValue == 0) return true;
        else return false;
    }

    boolean isLeftFree(Cell cell) {
        if (field.cells[cell.x - 1][cell.y].cellValue == 0) return true;
        else return false;
    }

    void moveDown() {
        field.cells[currentCell.x][currentCell.y + 1].cellImage = currentCell.cellImage;
        field.cells[currentCell.x][currentCell.y + 1].cellValue = currentCell.cellValue;
        field.cells[currentCell.x][currentCell.y].cellImage = null;
        field.cells[currentCell.x][currentCell.y].cellValue = 0;
        currentCell = field.cells[currentCell.x][currentCell.y + 1];
    }

    void moveRight() {
        field.cells[currentCell.x + 1][currentCell.y].cellImage = currentCell.cellImage;
        field.cells[currentCell.x + 1][currentCell.y].cellValue = currentCell.cellValue;
        field.cells[currentCell.x][currentCell.y].cellImage = null;
        field.cells[currentCell.x][currentCell.y].cellValue = 0;
        currentCell = field.cells[currentCell.x + 1][currentCell.y];
    }

    void moveLeft() {
        field.cells[currentCell.x - 1][currentCell.y].cellImage = currentCell.cellImage;
        field.cells[currentCell.x - 1][currentCell.y].cellValue = currentCell.cellValue;
        field.cells[currentCell.x][currentCell.y].cellImage = null;
        field.cells[currentCell.x][currentCell.y].cellValue = 0;
        currentCell = field.cells[currentCell.x - 1][currentCell.y];
    }

    void checkTargetSumAndErase() {
        int sum = 0, beginBlock = 0, endBlock = 0;
        for (int i = currentCell.x; i > 0 ; i--) {
            sum = 0;
                for (int j = i; j < 11; j++) {
                    if (field.cells[j][currentCell.y].cellValue != 0) {
                        sum += field.cells[j][currentCell.y].cellValue;
                        if (sum == targetSum) {
                            beginBlock = i;
                            endBlock = j;
                            break;
                        }
                    } else {
                        break;
                    }
                }
        }
        for (int i = currentCell.x; i < 11 ; i++) {
            sum = 0;
                for (int j = i; j > 0; j--) {
                    if (field.cells[j][currentCell.y].cellValue != 0) {
                        sum += field.cells[j][currentCell.y].cellValue;
                        if (sum == targetSum) {
                            beginBlock = j;
                            endBlock = i;
                            break;
                        }
                    } else {
                        break;
                    }
                }
        }
        if (beginBlock != 0 && endBlock != 0) {
            for (int i = currentCell.y; i > 0; i--) {
                for (int j = beginBlock; j < endBlock + 1; j++) {
                    field.cells[j][i].cellImage = field.cells[j][i - 1].cellImage;
                    field.cells[j][i].cellValue = field.cells[j][i - 1].cellValue;
                }
            }
        }
    }

}
