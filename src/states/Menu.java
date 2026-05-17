package states;

import UI.LevelButton;
import main.Game;
import utilz.Constants;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends State {

    private LevelButton levelButtons[];
    private Game game;
    private Color[][] backgroundColors;

    public Menu(Game game) {
        this.game = game;
        levelButtons = new LevelButton[6];
        for (int i = 0; i < levelButtons.length; i++) {
            levelButtons[i] = new LevelButton(i + 1,  (Constants.WINDOW_WIDTH/3-Constants.BUTTON_WIDTH)/2+(Constants.WINDOW_WIDTH/3)*(i/2),Constants.BUTTON_HEIGHT*(2*(i%2)+1));
        }

    }



    @Override
    public void render(Graphics g) {




        // Render Background
        g.setColor(Constants.BACKGROUND_COLOR);
    g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // Render Level Buttons
for (LevelButton button : levelButtons) {
            button.render(g);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        for(LevelButton b : levelButtons) {
            if(b.isIn(e))  // Check if the mouse is within the button's bounds
            {
                b.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(LevelButton b : levelButtons) {
            if(b.isIn(e))
            {

                if(b.isMousePressed())
                {
                    game.setActuallState(new LevelPlaying(game, b.getLevelId()));
                    System.out.println("Level " + b.getLevelId() + " selected");
                    break;
                }
            }

        }
        resetButtons();

    }

    private void resetButtons() {
        for(LevelButton b : levelButtons) {
            b.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (LevelButton button : levelButtons) {
            if(button.isIn(e)) {
                button.setHovered(true);
            } else {
                button.setHovered(false);
            }

        }

        
    }


}
