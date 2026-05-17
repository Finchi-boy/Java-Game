package levels;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadLevelData {


    public static LevelData LoadLevelData(int levelId) {
        LevelData levelData = null;
        try {
            String reader = new String(Files.readAllBytes(Paths.get("res/levels_data.json")));
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray levelsArray = jsonObject.getAsJsonArray("levels");
            LevelData data = null;
            for (int i = 0; i < levelsArray.size(); i++) {
                try {

                    levelData = gson.fromJson(levelsArray.get(i), LevelData.class);
                    if(levelData.getLevelId() != levelId) {
                        continue;
                    }

                    data = new LevelData(
                            levelData.getLevelId(),
                            levelData.getInputSize(),
                            levelData.getArraySize(),
                            levelData.getGoal(),
                            levelData.getAvailableCommands(),
                            levelData.getDescription()
                    );
                    System.out.println("Available commands: " + levelData.getAvailableCommands());
                    return data;


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("Loaded level data for level " + levelId + ": " + levelData.getAvailableCommands());
    return levelData;
    }

}