package commandsHandling.commandsUtilz;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }

    public static class UnknownCommandException extends CommandException {
        public UnknownCommandException(String command, int lineNumber) {
            super("At line " + lineNumber+": Unknown command: " + command + ".");
        }
    }

    public static class InvalidArgumentException extends CommandException {
        public InvalidArgumentException(String command, String argument, int lineNumber) {
            super("At line " + lineNumber+": Invalid argument '" + argument + ".");
        }
    }

    public static class MissingArgumentException extends CommandException {
        public MissingArgumentException(String command, int lineNumber) {
            super("At line " + lineNumber+": Missing argument for command: " + command + ".");
        }
    }

    public static class CommandNotAvailableException extends CommandException {
        public CommandNotAvailableException(String command, int lineNumber) {
            super("At line " + lineNumber+": Command '" + command + "' is not available in this level.");
        }
    }

    public static class SyntaxErrorException extends CommandException {
        public SyntaxErrorException(String command, int lineNumber) {
            super("At line: "+lineNumber+": Syntax error in command: " + command+".");
        }
    }
}
