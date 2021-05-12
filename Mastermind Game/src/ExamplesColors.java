import java.awt.Color;
import javalib.worldimages.BesideImage;
import javalib.worldimages.CircleImage;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.OutlineMode;
import tester.Tester;

// test for colors, lists of colors and related methods
class ExamplesColors {
  ExamplesColors() {
  }

  ILoColor empty = new MtLoColor();
  ILoColor s1 = new ConsLoColor(Color.cyan,
      new ConsLoColor(Color.magenta, 
          new ConsLoColor(Color.red, 
              new ConsLoColor(Color.green,
                  new ConsLoColor(Color.pink, 
                      new ConsLoColor(Color.pink, empty))))));
  ILoColor s2 = new ConsLoColor(Color.lightGray, 
      new ConsLoColor(Color.red,
          new ConsLoColor(Color.magenta, 
              new ConsLoColor(Color.blue, empty))));
  ILoColor s3 = new ConsLoColor(Color.cyan,
      new ConsLoColor(Color.magenta, 
          new ConsLoColor(Color.pink, empty)));
  ILoColor s4 = new ConsLoColor(Color.orange, 
      new ConsLoColor(Color.red,
          new ConsLoColor(Color.green, 
              new ConsLoColor(Color.blue, empty))));
  ILoColor s5 = new ConsLoColor(Color.cyan,
      new ConsLoColor(Color.magenta, 
          new ConsLoColor(Color.red, 
              new ConsLoColor(Color.green,
                  new ConsLoColor(Color.pink, 
                      new ConsLoColor(Color.pink, empty))))));
  ILoColor s6 = new ConsLoColor(Color.green, 
      new ConsLoColor(Color.green,
          new ConsLoColor(Color.green, 
              new ConsLoColor(Color.green, empty))));
  ILoColor s7 = new ConsLoColor(Color.orange,
      new ConsLoColor(Color.green, 
          new ConsLoColor(Color.green,
              new ConsLoColor(Color.orange, 
                  new ConsLoColor(Color.magenta, empty)))));
  ILoColor s8 = new ConsLoColor(Color.lightGray, 
      new ConsLoColor(Color.BLACK,
          new ConsLoColor(Color.green, 
              new ConsLoColor(Color.green, empty))));
  ILoColor s9 = new ConsLoColor(Color.red, 
      new ConsLoColor(Color.orange,
          new ConsLoColor(Color.blue, 
              new ConsLoColor(Color.green, empty))));

  //test countColors method
  boolean testCountColors(Tester t) {
    return t.checkExpect(empty.countColors(), 0)
        && t.checkExpect(s1.countColors(), 6)
        && t.checkExpect(s2.countColors(), 4);
  }

  //test reverseOrder method and its helper
  boolean testReverseOrder(Tester t) {
    return t.checkExpect(this.s3.reverseOrder(), new ConsLoColor(Color.pink,
        new ConsLoColor(Color.magenta, new ConsLoColor(Color.cyan, new MtLoColor()))))
        && t.checkExpect(this.empty.reverseOrder(), this.empty)

        && t.checkExpect(s3.reverseOrderHelper(Color.red), new ConsLoColor(Color.cyan,
            new ConsLoColor(Color.magenta, 
                new ConsLoColor(Color.pink,
                    new ConsLoColor(Color.red, empty)))))
        && t.checkExpect(empty.reverseOrderHelper(Color.red), new ConsLoColor(Color.red, empty));
  }

  //test removeFirst method
  boolean testRemoveFirst(Tester t) {
    return t.checkExpect(s8.removeFirst(), new ConsLoColor(Color.BLACK,
        new ConsLoColor(Color.green, 
            new ConsLoColor(Color.green, empty))))
        && t.checkExpect(empty.removeFirst(), empty);
  }

  //test addColor method
  boolean testAddColor(Tester t) {
    return t.checkExpect(s4.addColor(Color.lightGray), new ConsLoColor(Color.lightGray, 
        new ConsLoColor(Color.orange, 
            new ConsLoColor(Color.red,
                new ConsLoColor(Color.green, 
                    new ConsLoColor(Color.blue, empty))))))
        && t.checkExpect(empty.addColor(Color.BLACK), new ConsLoColor(Color.BLACK, empty));
  }

  //test drawSequence method
  boolean testDrawSequence(Tester t) {
    return t.checkExpect(s3.drawSequence(), 
        new BesideImage(new CircleImage(10, OutlineMode.SOLID, Color.cyan), 
            new CircleImage(10, OutlineMode.SOLID, Color.magenta), 
            new CircleImage(10, OutlineMode.SOLID, Color.pink), 
            new EmptyImage()))
        && t.checkExpect(empty.drawSequence(), new EmptyImage());
  }

  //test exactSequence method and its helper method
  boolean testExactSequence(Tester t) {
    return t.checkExpect(this.s1.exactSequence(s1), 6)
        && t.checkExpect(this.s6.exactSequence(s8), 2) 
        && t.checkExpect(this.s2.exactSequence(s8), 1)
        && t.checkExpect(this.s8.exactSequence(s6), 2)
        && t.checkExpect(this.s8.exactSequence(s2), 1)
        && t.checkExpect(this.s2.exactSequence(s6), 0)
        && t.checkExpect(this.s6.exactSequence(s2), 0)

        && t.checkExpect(this.s1.exactSequenceHelper(new ConsLoColor(Color.cyan,
            new ConsLoColor(Color.magenta, 
                new ConsLoColor(Color.red, 
                    new ConsLoColor(Color.green,
                        new ConsLoColor(Color.pink, 
                            new ConsLoColor(Color.pink, empty))))))), 6)
        && t.checkExpect(new ConsLoColor(Color.cyan,
            new ConsLoColor(Color.magenta, 
                new ConsLoColor(Color.red, 
                    new ConsLoColor(Color.green,
                        new ConsLoColor(Color.pink, 
                            new ConsLoColor(Color.pink, 
                                empty)))))).exactSequenceHelper(new ConsLoColor(Color.cyan,
                                    new ConsLoColor(Color.magenta, 
                                        new ConsLoColor(Color.red, 
                                            new ConsLoColor(Color.green,
                                                new ConsLoColor(Color.pink, 
                                                    new ConsLoColor(Color.pink, empty))))))), 6);
  }

  //test removeSameFirst method
  boolean testRemoveSameFirst(Tester t) {
    return t.checkExpect(s6.removeSameFirst(Color.green), 
        new ConsLoColor(Color.green,
            new ConsLoColor(Color.green, 
                new ConsLoColor(Color.green, empty))))
        && t.checkExpect(s6.removeSameFirst(Color.red), s6)
        && t.checkExpect(s8.removeSameFirst(Color.green), new ConsLoColor(Color.lightGray, 
            new ConsLoColor(Color.BLACK, new ConsLoColor(Color.green, empty))));
  }

  //test removeAllSame method
  boolean testRemoveAllSame(Tester t) {
    return t.checkExpect(s1.removeAllSame(empty), empty)
        && t.checkExpect(empty.removeAllSame(s1), s1)
        && t.checkExpect(s6.removeAllSame(s8), new ConsLoColor(Color.lightGray, 
            new ConsLoColor(Color.BLACK, empty)))
        && t.checkExpect(s8.removeAllSame(s6), new ConsLoColor(Color.green, 
            new ConsLoColor(Color.green, empty)));
  }

  //testing inExactSequence method
  boolean testInExactSequence(Tester t) {
    return t.checkExpect(this.s1.inExactSequence(s1), 0)
        && t.checkExpect(this.s6.inExactSequence(s8), 0)
        && t.checkExpect(this.s8.inExactSequence(s6), 0)
        && t.checkExpect(this.s2.inExactSequence(s4), 0)
        && t.checkExpect(this.s4.inExactSequence(s2), 0)
        && t.checkExpect(this.s4.inExactSequence(s9), 4)
        && t.checkExpect(this.s9.inExactSequence(s4), 4); 
  }

  //testing CorrespondingColorPosition method
  boolean testCorrespondingColorPosition(Tester t) {
    return t.checkExpect(this.s9.correspondingColorPosition(1, 1), Color.red)
        && t.checkExpect(this.s9.correspondingColorPosition(4, 1), Color.green)
        && t.checkExpect(this.empty.correspondingColorPosition(0, 0), null);
  }

  // testing firstUnique method
  boolean testFirstUnique(Tester t) {
    return t.checkExpect(s1.firstUnique(s1.countColors()), true)
        && t.checkExpect(s6.firstUnique(s6.countColors()), false)
        && t.checkExpect(s9.firstUnique(s9.countColors()), true)
        && t.checkExpect(empty.firstUnique(0), true);
  }

  //testing unique method
  boolean testUnique(Tester t) {
    return t.checkExpect(s1.unique(), false)
        && t.checkExpect(s6.unique(), false)
        && t.checkExpect(s9.unique(), true)
        && t.checkExpect(empty.unique(), true);
  }

  //test randomize method
  //  boolean testRandomize(Tester t) {
  //    return t.checkExpect(s9.randomize(s9.countColors(), new Random(), s9.countColors()), s9 );
  //  }


}
