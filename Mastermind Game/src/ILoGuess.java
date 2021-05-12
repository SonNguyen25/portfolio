import javalib.worldimages.WorldImage;

interface ILoGuess {
  // draw the guesses above each other
  WorldImage drawLoGuess();

  // add a confirmed completed guess to the list of guesses
  ILoGuess updateCompleteGuesses(IGuess given);

  // draw the exact/inexact values
  WorldImage drawExactInExactLoGuess();

  //get rows of guesses
  int rows();

  // count number of guesses
  int countGuesses();

  //get the number of exact colors compared to the answer 
  int getExact(ILoColor given);




}

