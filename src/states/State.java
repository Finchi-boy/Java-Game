package states;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class State {
    public abstract void render(Graphics g);
    public abstract void update();
    public abstract void mouseMoved(MouseEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);


}
