package main;

import utilz.Constants;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    JFrame frame;

    public GameWindow(GamePanel gamePanel) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }

}
