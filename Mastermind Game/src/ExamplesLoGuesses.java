import tester.*; 
import java.awt.Color;
import javalib.worldimages.*;

class ExamplesLoGuesses {

  ILoColor one = new ConsLoColor(Color.red, new MtLoColor());
  ILoColor four = new ConsLoColor(Color.blue, 
      new ConsLoColor(Color.magenta, 
          new ConsLoColor(Color.pink, 
              new ConsLoColor(Color.orange, 
                  new MtLoColor()))));
  ILoColor five = new ConsLoColor(Color.blue, 
      new ConsLoColor(Color.magenta, 
          new ConsLoColor(Color.yellow, 
              new ConsLoColor(Color.orange, 
                  new MtLoColor()))));

  IGuess incomplete = new IncompleteGuess(one);
  IGuess incomplete2 = new IncompleteGuess(five);
  IGuess complete = new CompleteGuess(four, 1, 3);

  ILoGuess empty = new MtLoGuess();
  ILoGuess LoGuess = new ConsLoGuess(complete, new MtLoGuess());
  ILoGuess LoGuess2 = new ConsLoGuess(complete, new ConsLoGuess(complete, empty));

  // test rows method
  boolean testRows(Tester t) {
    return t.checkExpect(this.empty.rows(), 0)
        && t.checkExpect(this.LoGuess2.rows(), 2);
  }

  // test updateCompleteGuesses method
  boolean testUpdateCompleteGuesses(Tester t) {
    return t.checkExpect(this.LoGuess.updateCompleteGuesses(complete),
        new ConsLoGuess(complete, this.LoGuess))
        && t.checkExpect(this.LoGuess2.updateCompleteGuesses(complete),
            new ConsLoGuess(complete, this.LoGuess2));

  }

  //test drawLoGuess method 
  boolean testDrawLoGuess(Tester t) {
    return t.checkExpect(this.LoGuess.drawLoGuess(), 
        new AboveImage(this.complete.drawGuess(), new EmptyImage())) 
        && t.checkExpect(this.empty.drawLoGuess(), new EmptyImage());
  }
  
  //test drawExactInExactLoGuess method
  boolean testDrawExactInExactLoGuess(Tester t) {
    return t.checkExpect(empty.drawExactInExactLoGuess(), new EmptyImage())
        && t.checkExpect(LoGuess.drawExactInExactLoGuess(), new AboveImage(
        complete.drawExactAndInExact().movePinhole(0, 20),
        new EmptyImage())); 
  }
  
  //test countGuesses method
  boolean testcountguesses(Tester t) {
    return t.checkExpect(empty.countGuesses(), 0)
        && t.checkExpect(LoGuess2.countGuesses(), 2);
  }
  
  //test getExact method
  boolean testgetExact(Tester t) {
    return t.checkExpect(this.LoGuess.getExact(five.reverseOrder()), 3)
        && t.checkExpect(empty.getExact(four), 0);
  }
  
}