package commands;

import commandsHandling.commandsUtilz.CommandException;
import commandsHandling.commandsUtilz.CommandType;
import levels.LevelManager;

public abstract class Command {
    public abstract CommandType getCommandType();
    public abstract void execute(int arg, LevelManager levelManager);
    public static class Add extends Command {

        public CommandType getCommandType() {
            return CommandType.OPERATION; // Example implementation, replace with actual logic
        }

        @Override
        public void execute(int arg, LevelManager levelManager) {
            levelManager.copyToArray(1);
        }



    }
    public static class Inbox extends Command {

        public CommandType getCommandType() {
            return CommandType.IO; // Example implementation, replace with actual logic
        }

        @Override
        public void execute(int arg, LevelManager levelManager) {
            levelManager.getInput();
        }


    }

    public static class Jump extends Command {

        public CommandType getCommandType() {
            return CommandType.FLOW_CONTROL; // Example implementation, replace with actual logic
        }

        @Override
        public void execute(int arg, LevelManager levelManager) {

        }


    }
}
