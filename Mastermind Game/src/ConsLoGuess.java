import javalib.worldimages.*;

class ConsLoGuess implements ILoGuess {
  IGuess first;
  ILoGuess rest;

  ConsLoGuess(IGuess first, ILoGuess rest) {
    this.first = first;
    this.rest = rest;
  }

  // draw the guesses above each other
  public WorldImage drawLoGuess() {
    return new AboveImage(this.first.drawGuess(), this.rest.drawLoGuess());
  }

  // add a confirmed completed guess to the list of guesses
  public ILoGuess updateCompleteGuesses(IGuess given) {
    return new ConsLoGuess(given, this);
  }


  // draw the exact/inexact values
  public WorldImage drawExactInExactLoGuess() {
    return new AboveImage(
        this.first.drawExactAndInExact().movePinhole(0, 20),
        this.rest.drawExactInExactLoGuess());
  }


  //get rows of guesses
  public int rows() {
    return this.rest.rows() + 1;
  }

  // count number of guesses
  public int countGuesses() {
    return this.rest.countGuesses() + 1;
  }

  //get the number of exact colors compared to the answer 
  public int getExact(ILoColor given) {
    return this.first.exact(given);
  }

}