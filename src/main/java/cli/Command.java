package cli;

/**
 * The definition for MovieTests command for the command line interface.
 *
 * @author ypark29
 *
 */
public interface Command {

  /**
   * The method that is called when MovieTests command is entered.
   *
   * @param args The arguments the user provides for MovieTests specific command
   */
  void execute(String args);
}
