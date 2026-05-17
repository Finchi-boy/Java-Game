package UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;


import utilz.Constants;

public class LevelButton {
    private int levelId;
    private int arch = 20;
    private int x, y;
    private RoundRectangle2D bounds;
    private Color color = Constants.BUTTON_COLOR;
    private Color boundColor = Constants.BUTTON_BOUND_COLOR;
    private boolean isHovered = false;
    private boolean isMousePressed = false;

    public LevelButton(int levelId, int x, int y) {
        this.levelId = levelId;
        this.x = x;
        this.y = y;
        bounds = new RoundRectangle2D.Double(x, y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT, arch, arch);




    }

    public void render(Graphics g) {

        if(isHovered)
        {
            color = Constants.BUTTON_HOVER_COLOR;
            boundColor = Constants.BUTTON_HOVER_BOUND_COLOR;
        }else {
            color = Constants.BUTTON_COLOR;
            boundColor = Constants.BUTTON_BOUND_COLOR;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fill(bounds);
        g2d.setColor(boundColor);
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(bounds);

        g2d.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 70);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics(font);
        int textWidth = metrics.stringWidth(String.valueOf(levelId));
        int textHeight = metrics.getHeight();

        int x = (int) (bounds.getX() + (bounds.getWidth() - textWidth) / 2);
        int y = (int) (bounds.getY() + (bounds.getHeight() - textHeight) / 2 + metrics.getAscent());
        g2d.drawString(String.valueOf(levelId), x, y);

    }

    public boolean isIn(MouseEvent e) {
        if( bounds.contains(e.getX(), e.getY())) {
            return  true;
        } else {
            return false;
        }
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }


    public void setMousePressed(boolean mousePressed) {
        isMousePressed = mousePressed;
    }

    public boolean isMousePressed() {
        return isMousePressed;
    }

    public int getLevelId() {
        return levelId;
    }

    public void resetBools() {
        isHovered = false;
        isMousePressed = false;
    }
}
