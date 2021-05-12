import java.awt.Color;
import java.util.Random;

import javalib.worldimages.AboveImage;
import javalib.worldimages.BesideImage;
import javalib.worldimages.CircleImage;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.WorldImage;

// represents a list of colors
class ConsLoColor implements ILoColor {
  Color first;
  ILoColor rest;

  ConsLoColor(Color first, ILoColor rest) {
    this.first = first;
    this.rest = rest;
  }

  //counts the number of colors
  public int countColors() {
    return 1 + this.rest.countColors();
  }

  //reverses the order of the colors in the list
  public ILoColor reverseOrder() {
    return this.rest.reverseOrder().reverseOrderHelper(this.first);
  }

  //helper method for reverseOrder method
  public ILoColor reverseOrderHelper(Color given) {
    return new ConsLoColor(this.first, this.rest.reverseOrderHelper(given));
  }

  //draw the sequence of colors
  public WorldImage drawSequence() {
    return new BesideImage(new CircleImage(10, OutlineMode.SOLID, this.first),
        this.rest.drawSequence());
  }

  //add a color to the list of colors
  public ILoColor addColor(Color given) {
    return new ConsLoColor(given, this);
  }

  //get the number of exact matches between the two lists of colors with same size
  public int exactSequence(ILoColor given) {
    return given.exactSequenceHelper(this);
  }

  // helper method for exactSquence
  public int exactSequenceHelper(ConsLoColor given) {
    if (this.first.equals(given.first)) {
      return 1 + given.rest.exactSequence(this.rest);
    } else {
      return given.rest.exactSequence(this.rest);
    }
  }

  //remove the given color from the first element in the list of colors that had that Color
  public ILoColor removeSameFirst(Color given) {
    if (given.equals(this.first)) {
      return this.rest;
    } else {
      return new ConsLoColor(this.first, this.rest.removeSameFirst(given));
    }
  }

  // does the first element of the list have the same color as the given one
  public boolean haveColor(Color given) {
    return given == this.first;
  }


  //get the number of inexact matches between the two lists of colors of the same size
  public int inExactSequence(ILoColor given) {
    return this.countColors() - this.removeAllSame(given).countColors() 
        - this.exactSequence(given);       
  }

  //return the given list with all same elements from both of the list removed
  public ILoColor removeAllSame(ILoColor given) {
    return this.rest.removeAllSame(given.removeSameFirst(this.first));

  }

  //draw the outlines that had not been filled by user
  public WorldImage drawOutline() {
    return new BesideImage(new CircleImage(10, OutlineMode.OUTLINE, Color.black),
        this.rest.drawOutline());

  }

  //draw all the outlines that had not been filled by user
  public WorldImage drawAllOutline(int num) {
    if (0 > num) {
      return new EmptyImage();
    } else {
      return new AboveImage(this.drawOutline(), this.drawAllOutline(num - 1));
    }
  }

  // with the input position given, get the corresponding color in the list of colors
  public Color correspondingColorPosition(int pos, int counter) {
    if (pos != counter) {
      return this.rest.correspondingColorPosition(pos, counter + 1);
    } else {
      return this.first;
    }
    
  }
  
  // check if the first color in a list are unique
  public boolean firstUnique(int length) {
    if (length == 1) {
      return true;
    } else if (this.first == this.correspondingColorPosition(length, 1)) {
      return false;
    } else { 
      return this.firstUnique(length - 1);
    }
  }

  // check if the colors in the list are unique
  public boolean unique() {
    return this.firstUnique(this.countColors()) 
        && this.rest.unique();
  }

  // randomize the list of colors
  public ILoColor randomize(Random rand) {
    return new ConsLoColor(this.correspondingColorPosition(
        rand.nextInt(this.countColors()), 0), this.rest.randomize(rand));
  }

  //remove the first color
  public ILoColor removeFirst() {
    return this.rest;
  }


}