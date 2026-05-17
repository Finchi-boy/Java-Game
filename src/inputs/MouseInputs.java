package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
public class MouseInputs implements MouseListener, MouseMotionListener {
    private Game game;

    public MouseInputs(Game game) {
        this.game = game;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        game.getState().mouseMoved(e);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.getState().mousePressed(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.getState().mouseReleased(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
