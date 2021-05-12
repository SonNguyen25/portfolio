import java.awt.Color;
import javalib.worldimages.*;

class IncompleteGuess implements IGuess {
  ILoColor guessSequence;

  IncompleteGuess(ILoColor guessSequence) {
    this.guessSequence = guessSequence;
  }

  //draw the guesses sequence
  public WorldImage drawGuess() {
    return this.guessSequence.reverseOrder().drawSequence();
  }

  // update a newly guessed color in the sequence
  public IGuess updateGuess(Color color) {
    return new IncompleteGuess(this.guessSequence.addColor(color));
  }

  //update an incomplete guess into a complete guess
  public IGuess updateCompleteGuess(ILoColor given) {
    return new CompleteGuess(this.guessSequence, 
        this.guessSequence.reverseOrder().exactSequence(given),
        this.guessSequence.reverseOrder().inExactSequence(given));
  }

  //count the number of colors inside a guess
  public int countGuess() {
    return this.guessSequence.countColors();
  }

  //display the inexact and exact color in a sequence compare to the hidden sequence
  public WorldImage drawExactAndInExact() {
    return new EmptyImage();
  }

  //get the number of exact colors compared to the answer 
  public int exact(ILoColor given) {
    return this.guessSequence.reverseOrder().exactSequence(given);
  }

  //remove the first element of the list
  public IGuess removeFirstColor() {
    return new IncompleteGuess(this.guessSequence.removeFirst());
  }


}