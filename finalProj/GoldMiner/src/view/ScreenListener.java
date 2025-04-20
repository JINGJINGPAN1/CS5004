package view;

/**
 * The {@code screenListener} interface defines callback methods
 * for handling user interactions across different screens in the game,
 * such as the start screen, game over screen, and level complete dialog.
 */
public interface ScreenListener {

  /**
   * Called when the user clicks the "Start" button to begin the game.
   */
  void onStartClicked();

  /**
   * Called when the user clicks the "Restart" button to restart the game.
   */
  void onRestartClicked();

  /**
   * Called when the user chooses to return to the start menu.
   */
  void onReturnToMenuClicked();

  /**
   * Called when the user clicks the "Next Level" button after completing a level.
   */
  void onNextLevelClicked();

  /**
   * Called when the user chooses to exit the game from the game over screen.
   */
  void onExitClicked();
}
