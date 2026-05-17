package levelElements;

import utilz.Constants;
import levels.LevelManager;

import java.awt.*;


public class MovingCell extends Cell {

    private int targetX;
    private int targetY;
    private int speed;

    public MovingCell(Cell cell, int targetX, int targetY) {
        super(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight(), cell.getValue(), cell.getColor(), cell.getLevelManager());
        setDestination(targetX, targetY);

    }

    public MovingCell(int x, int y, int width, int height, int value, Color color, LevelManager levelManager, int targetX,int targetY) {
        super(x, y, width, height, value, color, levelManager);
        setDestination(targetX, targetY);
    }

    protected void moveToDestination() {


        if (x < desX) {
            x += Constants.CELLS_MOVE_SPEED; // Use a constant for movement speed
            if (x > desX) x = desX; // Prevent overshooting
        } else if (x > desX) {
            x -= Constants.CELLS_MOVE_SPEED;
            if (x < desX) x = desX; // Prevent overshooting
        }

        if (y < desY) {
            y += Constants.CELLS_MOVE_SPEED;
            if (y > desY) y = desY; // Prevent overshooting
        } else if (y > desY) {
            y -= Constants.CELLS_MOVE_SPEED;
            if (y < desY) y = desY; // Prevent overshooting
        }

        bounds.setRoundRect(x, y, width, height, archSize, archSize); // Update bounds after moving

        if (x == desX && y == desY) {
            System.out.println("Cell arrived at destination: " + x + ", " + y);
            isMoving = false; // Stop moving after reaching the destination
            levelManager.onCellArrived(this);


        }
    }

}
