package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Game;

public class KeyboardInputs implements KeyListener {
    private Game game;

    public KeyboardInputs(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
