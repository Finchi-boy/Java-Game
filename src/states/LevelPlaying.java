package states;

import UI.LevelButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import levels.LevelManager;
import main.Game;

public class LevelPlaying extends State{

    private LevelManager levelManager;
    private Game game;
    public LevelPlaying(Game game, int level) {
        this.game=game;

        levelManager = new LevelManager(game, level);

    }

    @Override
    public void render(Graphics g) {
        // Render Background


        levelManager.render(g);


    }

    @Override
    public void update() {
        this.levelManager.update();

    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
