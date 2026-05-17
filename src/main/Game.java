package main;


import states.State;
import utilz.Constants;

import java.awt.*;


public class Game {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private State actualState;


    public Game() {
        actualState=new states.Menu(this); // Initialize the actual state to Menu
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        actualState=new states.LevelPlaying(this,1);

        gamePanel.revalidate(); // Ensure the panel is validated to reflect changes
        gamePanel.repaint(); // Ensure the panel is repainted to show the button

        run();

    }


    private void run() {
        double timePerFrame = 1000000000.0 / Constants.FPS;
        double timePerUpdate = 1000000000.0 / Constants.UPS;

        long previousTime = System.nanoTime();

        double deltaU = 0;
        double deltaF = 0;

        int updates = 0;
        int frames = 0;
        long lastCheck = System.nanoTime();

        while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;

            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;

            }

            long currentCheckTime = System.nanoTime();
            if (currentCheckTime - lastCheck >= 1000000000) {
                lastCheck = System.nanoTime();
                updates = 0;
                frames = 0;

            }
        }



    }

    public void update() {
        this.actualState.update();

    }

    public void render(Graphics g) {
        actualState.render(g);
    }

    public State getState() {
        return actualState;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setActuallState(State state) {
        this.actualState = state;
    }
}
