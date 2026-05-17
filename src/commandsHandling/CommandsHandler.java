package commandsHandling;

import commands.Command;
import commandsHandling.commandsUtilz.*;
import levels.LevelManager;
import commandsHandling.commandsUtilz.CommandException.*;

import java.util.ArrayList;

public class CommandsHandler {
    private CommandsHashmap commandsHashmap;
    private ArrayList<String> availableCommands;
    private ArrayList<CommandEntry> commandEntries;
    private CodeParser codeParser = new CodeParser();
    private LevelManager levelManager;
    private int currentCommandIndex = 0;



    public CommandsHandler(LevelManager levelManager) {
        this.levelManager = levelManager;

        commandsHashmap = new CommandsHashmap(levelManager);
        availableCommands = levelManager.getLevelData().getAvailableCommands();

        for (int i = 0; i < availableCommands.size(); i++) {
            availableCommands.set(i, availableCommands.get(i).toLowerCase());
        }
        commandEntries = new ArrayList<>();

    }




    public void parseCode(String commands) throws CommandException {
        currentCommandIndex = 0;
        commandEntries.clear(); // Clear previous command entries
        codeParser.parseCode(commands);

    }

    public boolean executeNextCommand() {


        if (currentCommandIndex < commandEntries.size()) {
            CommandEntry entry = commandEntries.get(currentCommandIndex);
            Command command = entry.getCommand();
            int argument = entry.getArgument();
            int lineNumber = entry.getLineNumber();
            if(command.getCommandType() == CommandType.FLOW_CONTROL) {
                if(argument> commandEntries.getLast().getLineNumber()) {
                    System.out.println("Invalid jump to line: " + argument + ". Maximum line number is: " + commandEntries.getLast().getLineNumber());
                    return false; // Invalid jump, stop execution
                }
                if(argument==entry.getLineNumber()) {
                    System.out.println("Jumping to the same command at line: " + argument);
                    return false; // Jump to the same command, continue execution
                }

                for(CommandEntry e : commandEntries) {
                    if (e.getLineNumber() == argument) {
                        currentCommandIndex = commandEntries.indexOf(e);
                        System.out.println("Jumping to command at line: " + argument);
                        executeNextCommand(); // Execute the command at the specified line
                        return true; // Jump to the specified command
                    }
                }
            }


                command.execute(argument, levelManager);
                System.out.println("Executed command: " + command.getClass().getSimpleName() + " with argument: " + argument + " at line: " + lineNumber);
                currentCommandIndex++;
                return true; // Command executed successfully, return true to continue execution




        } else {
            System.out.println("All commands executed.");
            return false; // No more commands to execute
        }

    }

    private class CodeParser {

        public void parseCode(String commands) throws CommandException {
            String[] lines = commands.split("\n"); //dzielenie kodu na linie
            int lineNumber = 0;
            for (String line : lines) {
                line = line.trim(); //usuwanie białych znaków z początku i końca linii
                lineNumber++;
                if (line.isEmpty()) {
                    continue; //pomijanie pustych linii
                }
                if (line.startsWith("#")) {
                    continue; //pomijanie komentarzy
                }

                String[] commandsInLine = line.split(";"); //dzielenie linii na komendy
                for (String commandLine : commandsInLine) {



                    commandLine = commandLine.trim(); //usuwanie białych znaków z początku i końca komendy
                    String[] parts = commandLine.split(":", 2); //dzielenie linii na komendę i argumenty
                    parts[0] = parts[0].trim(); //usuwanie białych znaków z początku i końca komendy
                    parts[0] = parts[0].toLowerCase(); //zamiana komendy na małe litery
                    if (parts.length > 1) {
                        parts[1] = parts[1].trim(); //usuwanie białych znaków z początku i końca argumentów
                    }

                    //brak takiej komendy
                    if (commandsHashmap.getCommand(parts[0]) == null) {
                        throw new UnknownCommandException(parts[0], lineNumber);
                    }
                    //sprawdzenie czy komenda jest dostępna
                    if (!availableCommands.contains(parts[0].toLowerCase())) {
                        throw new CommandNotAvailableException(parts[0], lineNumber);
                    }

                    Command command = commandsHashmap.getCommand(parts[0].toLowerCase());

                    switch (command.getCommandType()) {
                        case OPERATION:
                        case FLOW_CONTROL:
                            if (parts.length == 1) {
                                throw new MissingArgumentException(parts[0], lineNumber);
                            }
                            try {
                                Integer.parseInt(parts[1]);
                            } catch (NumberFormatException e) {
                                throw new InvalidArgumentException(parts[0], parts[1], lineNumber);
                            }
                            break;
                        case IO:
                            if (parts.length != 1) {
                                throw new InvalidArgumentException(parts[0], parts[1], lineNumber);
                            }
                            break;

                        default:
                            throw new UnsupportedOperationException("Unsupported command type");
                    }

                    int arg = (parts.length > 1) ? Integer.parseInt(parts[1]) : 0;

                    commandEntries.add(new CommandEntry(command, arg, lineNumber));


                }

            }

            executeNextCommand(); // Execute the first command immediately after parsing






        }

    }
}
