import java.awt.Color;
import java.util.Random;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.WorldImage;

// represents an empty list 
class MtLoColor implements ILoColor {
  MtLoColor() {
    //represents empty list
  }

  //counts the number of colors
  public int countColors() {
    return 0;
  }

  //reverses the order of the colors in the list
  public ILoColor reverseOrder() {
    return new MtLoColor();
  }

  //helper function for reverse order method
  public ILoColor reverseOrderHelper(Color given) {
    return new ConsLoColor(given, this);
  }

  //draw the sequence of colors
  public WorldImage drawSequence() {
    return new EmptyImage();
  }

  //add a color to the list of colors
  public ILoColor addColor(Color given) {
    return new ConsLoColor(given, this);
  }

  //get the number of exact matches between the two lists of colors
  public int exactSequence(ILoColor given) {
    return 0;
  }

  // helper method for exactSquence
  public int exactSequenceHelper(ConsLoColor given) {
    return 0;
  }

  //remove the given color from the first element in the list of colors that had that Color
  public ILoColor removeSameFirst(Color given) {
    return new MtLoColor();
  }

  // does the first element of the list have the same color as the given one
  public boolean haveColor(Color given) {
    return false;
  }

  //get the number of inexact matches between the two lists of colors
  public int inExactSequence(ILoColor given) {
    return 0;
  }

  //return the given list with all same elements from both of the list removed
  public ILoColor removeAllSame(ILoColor given) {
    return given;

  }

  //draw the outlines that had not been filled by user
  public WorldImage drawOutline() {
    return new EmptyImage();

  }

  //draw all the outlines that had not been filled by user
  public WorldImage drawAllOutline(int num) {
    return new EmptyImage();
  }

  // with the input position given, get the corresponding color in the list of colors
  public Color correspondingColorPosition(int pos, int counter) {
    return Color.black;

  }

  //check if the first color in a list are unique
  public boolean firstUnique(int length) {
    return true;
  }

  //check if the colors in the list are unique
  public boolean unique() {
    return true;
  }

  //randomize the list of colors
  public ILoColor randomize(Random rand) {
    return new MtLoColor();
  }


  //remove the first color
  public ILoColor removeFirst() {
    return new MtLoColor();
  }

}