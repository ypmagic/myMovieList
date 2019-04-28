package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * The class that handles commands and uses a REPL for the command line.
 *
 * @author ypark29
 *
 */
public class CommandProcessor {

  private Map<String, Command> commands;

  /**
   * This constructor creates a map to associate a string with a command.
   */
  public CommandProcessor() {
    this.commands = new HashMap<>();
  }

  /**
   * This method adds a command into the map.
   *
   * @param name    The name of the command
   * @param command The Command
   */
  public void addCommand(String name, Command command) {
    this.commands.put(name, command);
  }

  /**
   * This method calls the execute function of a command.
   *
   * @param name   The name of the command
   * @param args   The arguments provided by the user for a command
   */
  public void executeCommand(String name, String args) {
    if (this.commands.containsKey(name)) {
      this.commands.get(name).execute(args);
    } else {
      System.out.println("ERROR: Command does not exist.");
    }
  }

  /**
   * REPL for a command line interface.
   */
  public void loop() {
    BufferedReader systemIn = new BufferedReader(
        new InputStreamReader(System.in));
    String line;
    try {
      while ((line = systemIn.readLine()) != null) {
        // read and split the line into an array of strings

        try {
          String[] args = line.split(" ", 2);
          // execute the command, then print
          executeCommand(args[0], args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
          executeCommand(line, "");
        }
        // keep looping until end of file is reached
      }
      systemIn.close();
    } catch (IOException e) {
      System.out.println("ERROR: File not found.");
    }
  }

}
