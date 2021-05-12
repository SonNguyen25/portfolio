import java.awt.Color;

import javalib.worldimages.WorldImage;

//represents the user's guesses
interface IGuess {

  //draw the guesses sequence
  WorldImage drawGuess();

  //update a newly guessed color in the sequence
  IGuess updateGuess(Color color);

  //update an incomplete guess into a complete guess
  IGuess updateCompleteGuess(ILoColor given);

  //display the inexact and exact color in a sequence compare to the hidden sequence
  WorldImage drawExactAndInExact();

  //count the number of colors inside a guess
  int countGuess();

  //get the number of exact colors compared to the answer 
  int exact(ILoColor given);

  // remove the first element of the list
  IGuess removeFirstColor();


}