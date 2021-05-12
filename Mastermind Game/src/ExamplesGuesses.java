import tester.*; 
import java.awt.Color;
import javalib.worldimages.*;

// represents tests and example variables of Guesses
class ExamplesGuesses {

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

  //test drawGuess method
  boolean testDrawGuess(Tester t) {
    return t.checkExpect(incomplete.drawGuess(), 
        new BesideImage(new CircleImage(10, OutlineMode.SOLID, Color.red), new EmptyImage()))
        && t.checkExpect(complete.drawGuess(), 
            new BesideImage(new CircleImage(10, OutlineMode.SOLID, Color.orange), 
                new CircleImage(10, OutlineMode.SOLID, Color.pink), 
                new CircleImage(10, OutlineMode.SOLID, Color.magenta), 
                new CircleImage(10, OutlineMode.SOLID, Color.blue), 
                new EmptyImage()));
  }

  //test updateGuess method
  boolean testUpdateGuess(Tester t) {
    return t.checkExpect(this.incomplete.updateGuess(Color.red),
        new IncompleteGuess(new ConsLoColor(Color.red, one)))
        && t.checkExpect(this.complete.updateGuess(Color.BLACK), complete);
  }

  //test updateCompleteGuess method
  boolean testUpdateCompleteGuess(Tester t) {
    return t.checkExpect(this.incomplete.updateCompleteGuess(one), new CompleteGuess(one, 1, 0))
        && t.checkExpect(this.incomplete2.updateCompleteGuess(four.reverseOrder()), 
            new CompleteGuess(five, 3, 0));
  }

  //test drawExactAndInExact method
  boolean testDrawExactAndInExact(Tester t) {
    return t.checkExpect(incomplete2.drawExactAndInExact(), new EmptyImage())
        && t.checkExpect(complete.drawExactAndInExact(), new BesideImage(
            new OverlayImage(new RectangleImage(45, 15, OutlineMode.OUTLINE, Color.red),
                new TextImage(Integer.toString(1), 20, Color.black)),
            new OverlayImage(new RectangleImage(45, 15, OutlineMode.OUTLINE, Color.red),
                new TextImage(Integer.toString(3), 20, Color.black))));
  }

  //test countGuess method
  boolean testCountGuess(Tester t) {
    return t.checkExpect(incomplete.countGuess(), 1)
        && t.checkExpect(complete.countGuess(), 4);
  }

  //test Exact method
  boolean testExact(Tester t) {
    return t.checkExpect(incomplete.exact(one), 1)
        && t.checkExpect(incomplete.exact(four), 0)
        && t.checkExpect(this.complete.exact(five.reverseOrder()), 3);
  }

  //test removeFirstColor method
  boolean testremoveFirstColor(Tester t) {
    return t.checkExpect(incomplete.removeFirstColor(), new IncompleteGuess(new MtLoColor()))
        && t.checkExpect(complete.removeFirstColor(), 
            new CompleteGuess(new ConsLoColor(Color.magenta, 
                new ConsLoColor(Color.pink, 
                    new ConsLoColor(Color.orange, 
                        new MtLoColor()))), 1, 3));
  }
}