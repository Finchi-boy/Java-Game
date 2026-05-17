package levels;

import java.util.ArrayList;

public class LevelData {
    private int levelId;
    private int inputSize;
    private ArrayList<Integer> input;
    private ArrayList<Integer> output;
    private String goal;
    private ArrayList<String> availableCommands;
    private String description;
    private int arraySize;

    public LevelData(int levelId, int inputSize, int arraySize, String goal, ArrayList<String> availableCommands, String description) {
        this.levelId = levelId;
        this.inputSize= inputSize;
        this.arraySize = arraySize;
        this.goal = goal;
        this.availableCommands = availableCommands;
        this.description = description;
    }

    public int getLevelId() {
        return levelId;
    }

    public ArrayList<Integer> getInput() {
        return input;
    }

    public String getGoal() {
        return goal;
    }

    public ArrayList<String> getAvailableCommands() {
        return availableCommands;
    }

    public String getDescription() {
        return description;
    }

    public int getInputSize() {
        return inputSize;
    }

    public ArrayList<Integer> getOutput() {
        return output;
    }

    public int getArraySize() {
        return arraySize;
    }
}
