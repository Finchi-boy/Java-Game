package levelElements;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import commands.Command;
import levels.LevelManager;
import utilz.Constants;
import utilz.ShapeProperties;

public class Cell {
   protected int x;
    protected int y;
    protected int desX;
    protected int desY;
    protected int width;
    protected int height;
    protected int value;
    protected final static int archSize = 16; // Example arc size for rounded corners
    protected Color color;
    protected RoundRectangle2D bounds;
    protected LevelManager levelManager;
    protected boolean isMoving = false; // Flag to indicate if the cell is currently moving

    public void update()
    {
        if(isMoving) {
            moveToDestination();
        }
    }

    public void setDestination(int x, int y) {
        this.desX = x;
        this.desY = y;
        this.isMoving = true; // Set the moving flag to true
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

        if(x == desX && y == desY) {
            System.out.println("Cell arrived at destination: " + x + ", " + y);
            isMoving = false; // Stop moving after reaching the destination
            levelManager.onCellArrived(null);


        }
    }

    public Cell(int x, int y, ShapeProperties sp, int value, LevelManager levelManager) {
        this.levelManager = levelManager;
        this.x = x;
        this.y = y;
        this.width = sp.width;
        this.height = sp.height;
        this.value = value;
        this.color = sp.color;

        this.bounds = new RoundRectangle2D.Double(x, y, width, height, archSize, archSize); // Example arc size
    }
    {

    }



    public Cell(int x, int y, int width, int height, int value, Color color, LevelManager levelManager) {
        this.levelManager = levelManager;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = value;
        this.color = color;

        this.bounds = new RoundRectangle2D.Double(x, y, width, height, archSize,   archSize); // Example arc size
    }


    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fill(bounds);

        g2d.setColor(new Color(0, 0, 0, 128)); // Semi-transparent black for the border
        g2d.draw(bounds);

        // Draw the value inside the cell
        g2d.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics(font);
        String valueStr = String.valueOf(value);
        int textWidth = metrics.stringWidth(valueStr);
        int textHeight = metrics.getHeight();
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height + textHeight) / 2 - metrics.getDescent();
        g2d.drawString(valueStr, textX, textY);
    }



    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    protected LevelManager getLevelManager() {
        return levelManager;
    }
}
