package levels;

import commandsHandling.CommandsHandler;
import commandsHandling.ProgrammingWindow;
import levelElements.InputCell;
import levelElements.MovingCell;
import main.Game;
import utilz.Constants;
import levelElements.Cell;
import utilz.RunListener;


import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;



public class LevelManager implements RunListener {

    private int levelId;
    private Game game;
    private ProgrammingWindow programmingWindow;
    private ArrayList<Cell> inputCells;
    private ArrayList<Cell> outputCells;
    private ArrayList<Cell> arrayCells;
    private Cell mainCell;
    private LevelData levelData;
    private boolean isRunning = false;
    private Cell selectedCell;
    private Cell movingCell;
    private RoundRectangle2D arrayBackground;


    private CommandsHandler commandsHandler;

    public LevelManager(Game game, int levelId) {
        this.game = game;
        this.levelId = levelId;
        levelData=  LoadLevelData.LoadLevelData(levelId);
        System.out.println("Level Data: " + levelData.getAvailableCommands());

        programmingWindow = new ProgrammingWindow();
        programmingWindow.setRunListener(this);
        game.getGamePanel().add(programmingWindow);

        initInputCells();
        initArrayCells();
        mainCell = new Cell(Constants., 400,  Constants.MAIN_CELL,0, this); // Example main cell

        commandsHandler = new CommandsHandler(this);




    }

    private void initArrayCells() {
        arrayCells = new ArrayList<>();
        //arrayBackground
        int bgWidth = Constants.ARRAY_CELL.width * levelData.getArraySize();//miejsce na same komorki
        bgWidth += (Constants.ARRAY_CELL.width * (levelData.getArraySize() +1)/4); // Add space between cells
        int bgHeight = (int)(Constants.ARRAY_CELL.height*1.5);
        arrayBackground = new RoundRectangle2D.Double((Constants.BOARD_WIDTH-bgWidth)/2, 0, bgWidth, bgHeight, 20, 20);

        // Adjust height as needed

        for(int i=0;i<levelData.getArraySize();i++) {
            arrayCells.add(new Cell((Constants.BOARD_WIDTH-bgWidth)/2+Constants.ARRAY_CELL.width*(5*i+1)/4, Constants.ARRAY_CELL.height/4, Constants.ARRAY_CELL ,i, this)); // Example array cells

        }
    }

    private void initInputCells() {
        inputCells = new ArrayList<>();
        for(int i=0;i<levelData.getInputSize();i++) {
            inputCells.add(new Cell(0, (Constants.WINDOW_HEIGHT-Constants.INPUT_CELL.height)/2-(int)(Constants.INPUT_CELL.height*1.5)*i , Constants.INPUT_CELL, i, this));
        }

    }


    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        drawFloor(g);
        for(Cell cell : inputCells) {
            cell.render(g);
        }

        g2d.setColor(new Color(0x005DA6FF));
        g2d.fill(arrayBackground);// Set color for the array background
        ((Graphics2D) g).fill(arrayBackground); // Fill the background for the array cells

        for(Cell cell : arrayCells) {
            cell.render(g);
        }
        if(movingCell != null) {
            movingCell.render(g); // Render the moving cell if it exists
        }

        mainCell.render(g); // Render the main cell

    }




    public void drawFloor(Graphics g) {

        for(int x=0;x< Constants.TILES_IN_BOARD;x++) {
            for(int y=0;y< Constants.TILES_IN_HEIGHT;y++) {
                g.setColor(Constants.FLOOR_TILES_COLORS[(x + y) % Constants.FLOOR_TILES_COLORS.length]);
                g.fillRect(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }

    }

    public Game getGame() {
        return game;
    }

    public LevelData getLevelData() {
        return levelData;
    }

    @Override
    public void onRun(String code) {

    System.out.println("Running code: " + code);
    commandsHandler.parseCode(code);
        // Here you would parse the code and execute the commands
        // For now, we just print the code to the console
        // You can implement a command parser and executor here
    }


    public void update() {
        for(Cell cell : inputCells) {
            cell.update();
        }
        if(movingCell != null) {
            movingCell.update(); // Update the moving cell if it exists
        }
    }

    public ArrayList<Cell> getInputCells() {
        return inputCells;
    }

    public void onCellArrived(Cell arivingCell) {
        if(arivingCell !=movingCell) {
            return;
        }

        selectedCell.setValue(movingCell.getValue()); // Set the value of the cell to the temporary value

        isRunning = commandsHandler.executeNextCommand();
        if(!isRunning)
        {
            System.out.println("All commands executed or no more commands to execute.");
//            programmingWindow.setRunButtonEnabled(true); // Enable the run button again
        }
    }


    public void copyToArray(int cellId) {
        selectedCell = arrayCells.get(cellId); // Set the selected cell to the array cell
        movingCell = new MovingCell(mainCell.getX(), mainCell.getY(), selectedCell.getWidth(), selectedCell.getHeight(), mainCell.getValue(), selectedCell.getColor(), this, 0, 0); // Create a moving cell from the main cell


    }

    public void copyFromArray(int cellId) {
        selectedCell=mainCell; // Set the selected cell to the main cell
        movingCell = new MovingCell(arrayCells.get(cellId), 400, 400); // Create a moving cell from the array cell

    }

    public void getInput()
    {
        if(inputCells.isEmpty()) {
            System.out.println("No input cells available.");
            return; // No input cells to move
        }
        selectedCell=mainCell; // Set the selected cell to the main cell
        movingCell = new MovingCell(inputCells.get(0), 400, 400);

        for(int i=1;i<inputCells.size();i++) {
            inputCells.get(i).setDestination(inputCells.get(i-1).getX(),inputCells.get(i-1).getY()); // Set the destination of each input cell
        }
        inputCells.remove(0); // Remove the first input cell after moving it to the main cell

    }

    public void putOutput() {

    }


}
