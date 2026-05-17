package utilz;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Constants {
    public static final int FPS = 100;
    public static final int UPS = 100;

    public static final int TILE_SIZE = 40;
    public static final int TILES_IN_WIDTH = 31;
    public static final int TILES_IN_HEIGHT =21;
    public static final int TILES_IN_BOARD = 21;


    public static final int SIZE_OF_EDITOR = (TILES_IN_WIDTH-TILES_IN_BOARD)*TILE_SIZE;
    public static final int WINDOW_WIDTH = TILES_IN_WIDTH*TILE_SIZE;
    public static final int WINDOW_HEIGHT = TILES_IN_HEIGHT*TILE_SIZE;
    public static final int BOARD_WIDTH = TILES_IN_BOARD*TILE_SIZE;

    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 100;
/*    public static final Color BUTTON_COLOR = new Color(0x2368FB);
    public static final Color BUTTON_HOVER_COLOR = new Color(0x0419d8);
    public static final Color BUTTON_PRESSED_COLOR = new Color(0x0059B3);
    public static final Color BUTTON_BOUND_COLOR = new Color(0x5A32FC);
    public static final Color BUTTON_HOVER_BOUND_COLOR = new Color(0x0113BA);
    public static final Color BUTTON_PRESSED_BOUND_COLOR = new Color(0x3D0080);*/

    public static final Color BACKGROUND_COLOR = new Color(0x3A1D01);
    public static final Color BUTTON_COLOR = new Color(0xa97d39);
    public static final Color BUTTON_HOVER_COLOR = new Color(0xbf8c40);
//    public static final Color BUTTON_PRESSED_COLOR = new Color(0x0059B3);
    public static final Color BUTTON_BOUND_COLOR = new Color(0xDCD065);
    public static final Color BUTTON_HOVER_BOUND_COLOR = new Color(0xF7EF8A);
//    public static final Color BUTTON_PRESSED_BOUND_COLOR = new Color(0x3D0080);


    public static final int CELLS_MOVE_SPEED = 2;
    public static final Color[] FLOOR_TILES_COLORS = {new Color(0x9B744E),
            new Color(0xC19A6B)};

    public static final ShapeProperties ARRAY_CELL = new ShapeProperties(60,new Color(0x38E1FF), 8);

    public static final ShapeProperties INPUT_CELL = new ShapeProperties(60,new Color(0x00FF33), 10);
    public static final ShapeProperties MAIN_CELL = new ShapeProperties(100 ,new Color(0xFBFFAA), 16);


}
