import java.awt.Color;
import java.util.Random;
import javalib.worldimages.*;


// represents a list of colors
interface ILoColor {

  //counts the number of colors
  int countColors();

  //reverses the order of the colors in the list
  ILoColor reverseOrder();

  //helper method for reverseOrder method
  ILoColor reverseOrderHelper(Color given);

  //draw the sequence of colors
  WorldImage drawSequence();

  //add a color to the list of colors
  ILoColor addColor(Color given);

  //get the number of exact matches between the two lists of colors
  int exactSequence(ILoColor given);

  //helper method for exactSquence
  int exactSequenceHelper(ConsLoColor given);

  //get the number of inexact matches between the two lists of colors
  int inExactSequence(ILoColor given);

  //remove the first color
  ILoColor removeFirst();

  //remove the given color from the first element in the list of colors that had that Color
  ILoColor removeSameFirst(Color given);

  // does the first element of the list have the same color as the given one
  boolean haveColor(Color given);

  //return the given list with all same elements from both of the list removed
  ILoColor removeAllSame(ILoColor given);

  // draw the outlines that had not been filled by user
  WorldImage drawOutline();

  // draw all the outlines that had not been filled by user
  WorldImage drawAllOutline(int num);

  //with the input position given, get the corresponding color in the list of colors
  Color correspondingColorPosition(int pos, int counter);

  //check if the first color in a list are unique
  boolean firstUnique(int length);

  //check if the colors in the list are unique
  public boolean unique();

  //randomize the list of colors
  ILoColor randomize(Random rand);


}

