package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the TTTModel implementation.
 * Tests gameplay logic including moves, player switching, game over detection,
 * win/draw outcomes, and game reset functionality.
 */
class TTTModelTest {
  private TTTModel game1;
  private TTTModel game2;

  /**
   * Initializes two Tic Tac Toe games before each test.
   */
  @BeforeEach
  void setUp() {
    game1 = new TTTModelImpl(3);
    game2 = new TTTModelImpl(3);
  }

  /**
   * Tests that the board size is correctly returned.
   */
  @Test
  void getBoardSize() {
    assertEquals(3, game1.getBoardSize());
    assertEquals(3, game2.getBoardSize());
  }

  /**
   * Tests that the current player alternates correctly
   * and becomes null after the game ends.
   */
  @Test
  void getCurrentPlayer() {
    assertEquals(Player.X, game1.getCurrentPlayer()); // Initial player should be X
    game1.makeMove(1, 1); // X moves
    assertEquals(Player.O, game1.getCurrentPlayer()); // After X moves, it should be O
    game1.makeMove(1, 2);// O moves
    assertEquals(Player.X, game1.getCurrentPlayer()); // After O moves, it should be X

    // Simulate a winning move
    game2.makeMove(0, 0);
    assertEquals(Player.O, game2.getCurrentPlayer());
    game2.makeMove(2, 1);
    assertEquals(Player.X, game2.getCurrentPlayer());
    game2.makeMove(1, 1);
    game2.makeMove(1, 2);
    game2.makeMove(2, 2); // X wins
    assertNull(game2.getCurrentPlayer()); // After the game ends, getCurrentPlayer() should return null
  }

  /**
   * Tests making valid moves, marking the board, handling occupied cells,
   * and disallowing moves after game over.
   */
  @Test
  void testMakeMoveAndGetMark() {
    //Ensure the board is initially empty
    assertNull(game1.getMark(0, 0));

    // test:
    //  X   X   X
    //  ..  O   ..
    //  ..  ..  O

    // Player X makes a move
    game1.makeMove(0, 0);
    assertEquals(Player.X, game1.getMark(0, 0));
    assertEquals(Player.O, game1.getCurrentPlayer());

    //Player O makes a move
    game1.makeMove(1, 1);
    assertEquals(Player.O, game1.getMark(1, 1));
    assertEquals(Player.X, game1.getCurrentPlayer());

    // Attempt to make a move in an occupied cell
    assertThrows(IllegalStateException.class, () -> game1.makeMove(1, 1));

    // Attempt to make a move after the game is won
    game1.makeMove(0, 1); // X
    game1.makeMove(2, 2); // O
    game1.makeMove(0, 2); // X wins

    assertTrue(game1.isGameOver());
    assertEquals(Player.X, game1.getWinner());
    assertThrows(IllegalStateException.class, () -> game1.makeMove(2, 0)); // No further moves allowed

  }

  /**
   * Tests detection of game over due to a win by X and O.
   */
  @Test
  void isGameOver() {
    //Ensure the board is initially empty
    assertNull(game1.getMark(0, 0));

    // test:
    //  ..  ..  X
    //  ..  X   ..
    //  X  ..  ..

    game1.makeMove(0, 2); // X moves
    game1.makeMove(0, 1); // O moves
    game1.makeMove(1, 1); // X moves
    game1.makeMove(2, 2); // O moves
    game1.makeMove(2, 0); // X wins

    assertTrue(game1.isGameOver());
    assertEquals(Player.X, game1.getWinner());


    // test:
    //  O  ..  ..
    //  O  ..  ..
    //  O  ..  ..

    //Ensure the board is initially empty
    assertNull(game2.getMark(0, 0));
    game2.makeMove(0, 2); // X moves
    game2.makeMove(0, 0); // O moves
    game2.makeMove(0, 1); // X moves
    game2.makeMove(1, 0); // O moves
    game2.makeMove(2, 1); // X moves
    game2.makeMove(2, 0); // O wins

    assertTrue(game2.isGameOver());
    assertEquals(Player.O, game2.getWinner());

  }

  /**
   * Tests winner detection.
   */
  @Test
  void getWinner() {
    // In the previous tests, I have tested the situation when X wins and O wins.
    // Here, I would like test it when it is a draw

    // test:
    // X  O   X
    // X  O   O
    // O  X   X

    // initially, no winners
    assertNull(game1.getWinner());
    // Play a full game leading to a draw
    game1.makeMove(0, 0); // X
    game1.makeMove(0, 1); // O
    game1.makeMove(0, 2); // X
    game1.makeMove(1, 1); // O
    game1.makeMove(1, 0); // X
    game1.makeMove(1, 2); // O
    game1.makeMove(2, 1); // X
    game1.makeMove(2, 0); // O
    game1.makeMove(2, 2); // X (last move, no winner)

    // After a draw, the game should be over
    assertTrue(game1.isGameOver());
    assertNull(game1.getWinner()); // No winner in a draw
  }

  /**
   * Tests resetting the game board after a win,
   * ensuring state and player reset correctly.
   */
  @Test
  void reset() {
    // test1:
    //  ..  ..  ..
    //  X   X   X
    //  ..  ..  ..

    // initially, no winners
    assertNull(game1.getWinner());

    // Make a few moves
    game1.makeMove(1, 1); // X
    game1.makeMove(0, 1); // O
    game1.makeMove(1, 2); // X
    game1.makeMove(2, 0); // O
    game1.makeMove(1, 0); // X wins

    assertTrue(game1.isGameOver());
    assertEquals(Player.X, game1.getWinner());

    game1.reset();

    // The board should be empty
    for (int r = 0; r < game1.getBoardSize(); r++) {
      for (int c = 0; c < game1.getBoardSize(); c++) {
        assertNull(game1.getMark(r, c));
      }
    }

    // Game state should be reset
    assertFalse(game1.isGameOver());
    assertNull(game1.getWinner());
    assertEquals(Player.X, game1.getCurrentPlayer());


    // test2:
    //  O   ..  ..
    //  ..  O   ..
    //  ..  ..  O

    // initially, no winners
    assertNull(game2.getWinner());

    // Make a few moves
    game2.makeMove(0, 1); // X
    game2.makeMove(1, 1); // O
    game2.makeMove(0, 2); // X
    game2.makeMove(0, 0); // O
    game2.makeMove(1, 2); // X
    game2.makeMove(2, 2); // O wins

    assertTrue(game2.isGameOver());
    assertEquals(Player.O, game2.getWinner());

    game2.reset();

    // The board should be empty
    for (int r = 0; r < game2.getBoardSize(); r++) {
      for (int c = 0; c < game2.getBoardSize(); c++) {
        assertNull(game2.getMark(r, c));
      }
    }

    // Game state should be reset
    assertFalse(game2.isGameOver());
    assertNull(game2.getWinner());
    assertEquals(Player.X, game2.getCurrentPlayer());

  }
}