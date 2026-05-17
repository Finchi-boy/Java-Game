package commandsHandling.commandsUtilz;

import commands.Command;

public class CommandEntry {
    private Command command;
    private int argument;
    private int lineNumber;

    public CommandEntry(Command command, int argument, int lineNumber) {
        this.command = command;
        this.argument = argument;
        this.lineNumber = lineNumber;
    }
    public Command getCommand() {
        return command;
    }
    public int getArgument() {
        return argument;
    }
    public int getLineNumber() {
        return lineNumber;
    }
}
