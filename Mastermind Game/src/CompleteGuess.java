import java.awt.Color;
import javalib.worldimages.*;

class CompleteGuess implements IGuess {
  ILoColor guessSequence;
  int exact;
  int inExact;

  CompleteGuess(ILoColor guessSequence, int exact, int inExact) {
    this.guessSequence = guessSequence;
    this.exact = exact;
    this.inExact = inExact;
  }

  //draw the guesses sequence
  public WorldImage drawGuess() {
    return this.guessSequence.reverseOrder().drawSequence();
  }

  //update a newly guessed color in the sequence
  public IGuess updateGuess(Color color) {
    return this;

  }

  //update an incomplete guess into a complete guess
  public IGuess updateCompleteGuess(ILoColor given) {
    return this;
  } 

  //count the number of colors inside a guess
  public int countGuess() {
    return this.guessSequence.countColors();
  }

  //get the number of exact colors compared to the answer 
  public int exact(ILoColor given) {
    return this.guessSequence.reverseOrder().exactSequence(given);
  }


  //display the inexact and exact color in a sequence compare to the hidden sequence
  public WorldImage drawExactAndInExact() {
    return new BesideImage(
        new OverlayImage(new RectangleImage(45, 15, OutlineMode.OUTLINE, Color.red),
            new TextImage(Integer.toString(this.exact), 20, Color.black)),
        new OverlayImage(new RectangleImage(45, 15, OutlineMode.OUTLINE, Color.red),
            new TextImage(Integer.toString(this.inExact), 20, Color.black)));
  }

  //remove the first element of the list
  public IGuess removeFirstColor() {
    return new CompleteGuess(this.guessSequence.removeFirst(), this.exact, this.inExact);
  }
}