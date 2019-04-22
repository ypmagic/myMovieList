package edu.brown.cs.ap99dwang66ekang5ypark29.cli;

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
