package main;

import UI.LevelButton;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utilz.Constants;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;
    public GamePanel(Game game) {

        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(game));
        addMouseListener(new MouseInputs(game));
        addMouseMotionListener(new MouseInputs(game));
        this.requestFocus();
        this.setFocusable(true);
        setLayout(null);




    }


    private void setPanelSize() {
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT));
        this.setMaximumSize(new Dimension(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT));
        this.setMinimumSize(new Dimension(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT));
        System.out.println("Panel size set to: " + Constants.WINDOW_WIDTH + "x" + Constants.WINDOW_HEIGHT);


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);

    }

    public Game getGame() {
        return this.game;
    }
}
