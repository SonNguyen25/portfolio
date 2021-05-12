import java.awt.Color;
import tester.Tester;

class ExamplesGame {
  ILoColor empty = new MtLoColor();

  ILoColor s1 = new ConsLoColor(Color.red, new MtLoColor());
  ILoColor s2 = new ConsLoColor(Color.blue,
      new ConsLoColor(Color.orange, new ConsLoColor(Color.magenta, new ConsLoColor(Color.green,
          new ConsLoColor(Color.pink, new ConsLoColor(Color.red, new MtLoColor()))))));
  ILoColor s3 = new ConsLoColor(Color.red, new ConsLoColor(Color.blue,
      new ConsLoColor(Color.yellow, new ConsLoColor(Color.green, new MtLoColor()))));
  ILoColor s4 = new ConsLoColor(Color.gray,
      new ConsLoColor(Color.yellow, new ConsLoColor(Color.magenta, new MtLoColor())));
  ILoColor s10 = new ConsLoColor(Color.red,
      new ConsLoColor(Color.red, new ConsLoColor(Color.red,
          new ConsLoColor(Color.red, new ConsLoColor(Color.red,
              new ConsLoColor(Color.red, new ConsLoColor(Color.red,
                  new ConsLoColor(Color.red, new ConsLoColor(Color.red,
                      new ConsLoColor(Color.red, new MtLoColor()))))))))));
  ILoColor s6 = new ConsLoColor(Color.green, 
      new ConsLoColor(Color.green,
          new ConsLoColor(Color.green, 
              new ConsLoColor(Color.green, empty))));

  IGuess incomplete = new IncompleteGuess(new MtLoColor());
  IncompleteGuess incomplete2 = new IncompleteGuess(s2);

  ILoGuess emptyGuess = new MtLoGuess();

  //tests if the constructor value is valid
  boolean testcheckConstructorException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Invalid Length of Sequence (or Bigger than Length"
            + " of Default Color List: 10"), "Game", 
        false, 10, 10, s1, s1, 
        emptyGuess, incomplete)
        && t.checkConstructorException(
            new IllegalArgumentException("Invalid Length of Sequence (or Bigger than Length"
                + " of Default Color List: 10"), "Game", 
            true, 10, 10, s10 , s1, 
            emptyGuess, incomplete)
        && t.checkConstructorException(
            new IllegalArgumentException("Invalid Length of Sequence (or Bigger than Length"
                + " of Default Color List: -1"), "Game", 
            true, -1, 1, s1, s1, 
            emptyGuess, incomplete)
        && t.checkConstructorException(
            new IllegalArgumentException("Invalid Number of Guesses: -3"),
            "Game", true, 2, -3, s1, s1, emptyGuess, incomplete)
        && t.checkConstructorException(
            new IllegalArgumentException("Invalid Given Color Options, not unique!"), 
            "Game", false, 1,
            1, s6, s1, emptyGuess, incomplete)
        && t.checkConstructorException(
            new IllegalArgumentException("Invalid Length of Sequence "
                + "(or Bigger than Length of Default Color List: 9"), "Game", 
            false, 9,
            9, s10, s6, emptyGuess, incomplete);
  }


  boolean testBigBang3(Tester t) {
    Game w = new Game(true, 6, 10, s2, s2, emptyGuess, incomplete);
    int worldWidth = 800;
    int worldHeight = 800;
    return w.bigBang(worldWidth, worldHeight);
  }

}