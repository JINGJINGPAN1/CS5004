package view;

public interface screenListener {
  void onStartClicked();
  // Called when the user clicks to restart the game.
  void onRestartClicked();
  // Called when the user chooses to return to the start menu.
  void onReturnToMenuClicked();
}
