package commandsHandling.commandsUtilz;

import commands.Command;
import levels.LevelManager;
import commands.Command.*;

import java.util.HashMap;

public class CommandsHashmap {
    private HashMap<String, Command> commandsMap;

    public CommandsHashmap(LevelManager levelManager) {
        commandsMap = new HashMap<>();
        commandsMap.put("add", new Add());
        commandsMap.put("inbox", new Inbox());
        commandsMap.put("jump", new Jump());
    }

    public Command getCommand(String commandName) {
        if(!commandsMap.containsKey(commandName.toLowerCase())) {
            return null; // or throw an exception if you prefer
        }
        return commandsMap.get(commandName.toLowerCase());
    }

}
