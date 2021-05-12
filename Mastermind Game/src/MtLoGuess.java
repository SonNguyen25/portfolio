import javalib.worldimages.*;

class MtLoGuess implements ILoGuess {

  //draw the guesses above each other
  public WorldImage drawLoGuess() {
    return new EmptyImage();
  }

  // add a confirmed completed guess to the list of guesses
  public ILoGuess updateCompleteGuesses(IGuess given) {
    return new ConsLoGuess(given, this);
  }

  // draw the exact/inexact values
  public WorldImage drawExactInExactLoGuess() {
    return new EmptyImage();
  }

  //get rows of guesses
  public int rows() {
    return 0;
  }

  // count number of guesses
  public int countGuesses() {
    return 0;
  }

  //get the number of exact colors compared to the answer 
  public int getExact(ILoColor given) {
    return 0;
  }

}