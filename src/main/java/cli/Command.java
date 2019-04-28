package cli;

/**
 * The definition for a command for the command line interface.
 *
 * @author ypark29
 *
 */
public interface Command {

  /**
   * The method that is called when a command is entered.
   *
   * @param args The arguments the user provides for a specific command
   */
  void execute(String args);
}
