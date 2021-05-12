import javalib.worldimages.*; 
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;

class Game extends World {
  boolean duplicate;
  int lengthSequence;
  int guessRows;
  ILoColor defaultColor;
  ILoColor answer;
  ILoGuess guess;
  IGuess incompleteGuess;
  Random rand;

  Game(boolean duplicate, int lengthSequence, 
      int guessRows, ILoColor defaultColor, ILoColor answer, 
      ILoGuess guess, IGuess incompleteGuess) {
    this.duplicate = duplicate;
    if ((!duplicate && lengthSequence > defaultColor.countColors()) || lengthSequence <= 0
        || lengthSequence >= 9) {
      throw new IllegalArgumentException("Invalid Length of Sequence (or Bigger than Length"
          + " of Default Color List: " 
          + Integer.toString(lengthSequence));
    } else {
      this.lengthSequence = lengthSequence;
    }
    if (guessRows <= 0) {
      throw new IllegalArgumentException("Invalid Number of Guesses: "
          + Integer.toString(guessRows));
    } else {
      this.guessRows = guessRows;
    }
    if (!(defaultColor.unique())) {
      throw new IllegalArgumentException("Invalid Given Color Options, not unique!");
    } else if (defaultColor.countColors() >= 10 || defaultColor.countColors() <= 0) {
      throw new IllegalArgumentException("Invalid length of color options: "
          + Integer.toString(defaultColor.countColors()));
    } else {
      this.defaultColor = defaultColor;
    }
    this.rand = new Random();

    //    this.answer = answer;
    this.answer = defaultColor.randomize(rand);
    this.guess = guess;
    this.incompleteGuess = incompleteGuess;
  }



  // draw the set of default colors
  WorldImage drawDefaultColor() {
    return this.defaultColor.drawSequence();
  }

  //draws the background with the covered answer 
  public WorldScene makeScene() {
    return this.background().placeImageXY(new RectangleImage((this.lengthSequence * 3 * 
        (this.lengthSequence + 2)) + 98, 40, OutlineMode.SOLID, Color.GRAY.darker())
        , 300, this.coverPos(this.guessRows));

  }

  //get y-coordinate for covering the answer
  public int coverPos(int guesses) {
    if (guesses % 2 == 0) {
      return (380 - (40 * (Math.round(this.guessRows / 2))));
    } else {
      return (380 - (42 * (Math.round(this.guessRows / 2))));
    }
  }

  //background of the world
  WorldScene background() {
    return new WorldScene(1000, 1000).placeImageXY(new BesideImage(
        new OverlayImage(new AboveAlignImage(AlignModeX.LEFT, this.answer.drawSequence(), 
            new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.BOTTOM, 
                new AboveAlignImage("left", this.incompleteGuess.drawGuess(), 
                    this.guess.drawLoGuess()),
                0, 0, this.answer.drawAllOutline(guessRows - 1)), this.drawDefaultColor()),
            new RectangleImage((this.lengthSequence * 40 + 10 * (this.lengthSequence + 2)) + 100,
                60 + (70 * guessRows), OutlineMode.SOLID,
                Color.red.darker())),
        new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.BOTTOM,
            this.guess.drawExactInExactLoGuess(), 0, this.guessRows * 40 / 2,
            new RectangleImage(100, 60 + (70 * guessRows), 
                OutlineMode.SOLID, Color.pink.darker()))), 300, 300);
  }

  //react depends on user's pressed keys
  public World onKeyEvent(String key) {
    if ((key.equals("1") || key.equals("2") || key.equals("3") || key.equals("4") 
        || key.equals("5") ||
        key.equals("6") || key.equals("7") || key.equals("8") || key.equals("9")) &&
        this.incompleteGuess.countGuess() < this.lengthSequence) {
      return new Game(this.duplicate, this.lengthSequence, this.guessRows, this.defaultColor,
          this.answer, this.guess,
          this.incompleteGuess.updateGuess(
              this.defaultColor.correspondingColorPosition(Integer.parseInt(key), 1)));
    } 
    else if (key.equals("enter") 
        && this.incompleteGuess.exact(answer) == this.lengthSequence) {
      return endOfWorld("Nice job! You beat the game!");
    } else if (key.equals("enter") && this.guessRows == (1 + guess.countGuesses())) {
      return endOfWorld("Too bad, you lost the game buddy!");
    } 
    else if (key.equals("enter") && this.guessRows > guess.countGuesses()
        && this.incompleteGuess.countGuess() == this.lengthSequence) {
      return new Game(this.duplicate, this.lengthSequence, this.guessRows, this.defaultColor,
          this.answer,
          this.guess.updateCompleteGuesses(this.incompleteGuess.updateCompleteGuess(this.answer)),
          new IncompleteGuess(new MtLoColor()));
    } else if (key.equals("backspace")) {
      return new Game(this.duplicate, this.lengthSequence, this.guessRows, this.defaultColor,
          this.answer, this.guess, this.incompleteGuess.removeFirstColor());
    } else {
      return this;
    }
  }

  //  public WorldEnd worldEnds() {
  //    if (this.incompleteGuess.exact(answer) == this.lengthSequence) {
  //      return new WorldEnd(true, this.lastScene("Nice job! You beat the game!"));
  //    } else if (this.guessRows == (1 + guess.countGuesses())) {
  //      return new WorldEnd(true, this.lastScene("Too bad, you lost the game buddy!"));
  //    } else {
  //      return new WorldEnd(false, this.makeScene());
  //    }
  //  }


  //make ending scene
  public WorldScene lastScene(String text) {
    return this.background().placeImageXY(new OverlayImage(new TextImage(text, 20, Color.pink),
        new RectangleImage((this.lengthSequence * 50 + 15 * this.lengthSequence)
            + 95 + 95, 40, OutlineMode.SOLID, Color.BLACK)), 300, 300);
  }

}




