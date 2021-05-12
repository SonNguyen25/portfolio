import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;
import java.util.Arrays;
import java.util.Random;


//Represents a single square of the game area
class Cell {
  // In logical coordinates, with the origin at the top-left corner of the screen
  int x;
  int y;
  Color color;
  boolean flooded;
  // the four adjacent cells to this one
  Cell left;
  Cell right;
  Cell top;
  Cell bottom;

  //constructor for Cell
  Cell(int x, int y, Color color, boolean flooded) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.flooded = flooded;
    this.left = this;
    this.right = this;
    this.top = this;
    this.bottom = this;
  }


  //draw the cell
  WorldImage drawCell() {
    return new OverlayImage(new RectangleImage(FloodItWorld.cellSize, 
        FloodItWorld.cellSize, "outline", Color.WHITE),
        new RectangleImage(FloodItWorld.cellSize, FloodItWorld.cellSize, "solid", this.color));
  }

  // updates the cell on the left of this cell with the given cell
  void setLeft(Cell given) {
    this.left = given;
    given.right = this;
  }

  //updates the cell on the right of this cell with the given cell
  void setRight(Cell given) {
    this.right = given;
    given.left = this;
  }

  //updates the cell on the top of this cell with the given cell
  void setTop(Cell given) {
    this.top = given;
    given.bottom = this;
  }

  //updates the cell on the bottom of this cell with the given cell
  void setBottom(Cell given) {
    this.bottom = given;
    given.top = this;
  }

  //checks neighbors of a Cell to see if it should be flooded
  ArrayList<Cell> checkFlood(Color prev) {
    ArrayList<Cell> flooding = new ArrayList<Cell>();
    //need to create a new arrayList to store since my memory ran out if
    //I keep reuse the arraylist
    this.left.readyToFlood(prev, flooding);
    this.right.readyToFlood(prev, flooding);
    this.top.readyToFlood(prev, flooding);
    this.bottom.readyToFlood(prev, flooding);
    return flooding;
  }

  // get a cell that is ready to be flooded and store it in an array list
  void readyToFlood(Color now, ArrayList<Cell> given) {
    if (this.color.equals(now)) {
      if (!given.contains(this)) {
        given.add(this);
      }
      else {
        return;
      }
    }
  }

}







class FloodItWorld extends World {
  // All the cells of the game
  ArrayList<ArrayList<Cell>> board;
  int numColor;
  int size; //dimension of the board, user inputted
  static final int cellSize = 20;
  static final ArrayList<Color> defaultColors = new ArrayList<Color>(Arrays.asList(Color.magenta,
      Color.pink, Color.blue, Color.red, Color.orange, Color.green, Color.yellow, Color.cyan));
  Random rand = new Random();
  int clicks;
  double ticks;
  int defaultClicks; //max number of clicks (tries)
  ArrayList<Cell> floods = new ArrayList<Cell>();
  Color prev;
  Color now;


  FloodItWorld(int size, int numColor) {
    if (size < 2 || size > 26) { //according to the online given version
      throw new IllegalArgumentException("Only allowed from 2x2 to 26x26 for size, you inputted: " 
          + Integer.toString(size));
    } else {
      this.size = size;
    }
    this.ticks = 0.0;
    if (numColor < 3 || numColor > 8) { //according to the online given version
      throw new IllegalArgumentException("Only allowed from 3 to 8 colors, you inputted: " +
          Integer.toString(numColor));
    } else {
      this.numColor = numColor; 
    }
    if (numColor < 8 && this.size < 8) {
      this.defaultClicks = (int) Math.floor(25*
          ((this.size + this.size) * 8) / ((14 + 14) * 6));
    } else {
      this.defaultClicks = (int) Math.floor(25*
          ((this.size + this.size) * this.numColor) / ((14 + 14) * 6)); 
    } // make max number of tries appropriately
    //change number of max tries according to the online game
    this.board = makeBoard();
    setAdjacent();
  }

  //constructor for testing purposes
  FloodItWorld(ArrayList<ArrayList<Cell>> board) {
    this.size = board.size();
    this.board = board;
    this.clicks = 0;
    this.ticks = 0.0;
    this.numColor = this.size;
  }


  //make the board
  ArrayList<ArrayList<Cell>> makeBoard() {
    ArrayList<ArrayList<Cell>> newBoard = new ArrayList<ArrayList<Cell>>();
    for (int row = 0; row < this.size; row = row + 1) {
      newBoard.add(new ArrayList<Cell>());
      for (int columns = 0; columns < this.size; columns = columns + 1) {
        newBoard.get(row).add(new Cell(row, columns, 
            FloodItWorld.defaultColors.get(rand.nextInt(this.numColor)), false));
      }
    }
    
    newBoard.get(0).get(0).flooded = true;
    this.now = newBoard.get(0).get(0).color;
    this.floods.add(newBoard.get(0).get(0));
    return newBoard;
  }

  //make the scene
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(1200, 800);
    scene.placeImageXY(new FromFileImage("./flooditbackground.jpg"), 600, 400);
    // image free to use on https://wallpapercave.com/ocean-wallpaper-backgrounds
    WorldImage score = new TextImage("Score: " + 
        Integer.toString(this.displayScore()) + "/" +
        Integer.toString(this.defaultClicks) + "              " +
        "Time: " + Double.toString(this.ticks / 10) + "s",
        FloodItWorld.cellSize, FontStyle.BOLD, new Color(51, 102, 153));
    WorldImage title = new TextImage("FLOOD-IT! - SON NGUYEN",
        50, FontStyle.BOLD_ITALIC, new Color(153, 255, 187));
    scene.placeImageXY(score, 900, 109);
    scene.placeImageXY(title, 591, 696);
    WorldImage sizes = new TextImage("Size: " + Integer.toString(this.size) + "x"
        + Integer.toString(this.size), FloodItWorld.cellSize, FontStyle.BOLD, 
        new Color(255, 204, 0));
    WorldImage colors = new TextImage("Colors :" + Integer.toString(this.numColor), 
        FloodItWorld.cellSize, FontStyle.BOLD, new Color(255, 204, 0));
    scene.placeImageXY(sizes, 746, 22);
    scene.placeImageXY(colors, 974, 21);
    WorldImage border = 
        new RectangleImage((this.size + 2) * FloodItWorld.cellSize,(this.size + 2) *
            FloodItWorld.cellSize,
            OutlineMode.SOLID, new Color(0, 153, 153));
    scene.placeImageXY(border, ((this.size + 2) * 10) + 50, ((this.size + 2)* 10) + 50);
    int rows = 0;
    while (rows < this.size) {
      int columns = 0;
      while (columns < this.size) {
        Cell c = this.board.get(rows).get(columns);
        scene.placeImageXY(c.drawCell(), (c.x * FloodItWorld.cellSize + FloodItWorld.cellSize / 2) +
            70, (c.y * FloodItWorld.cellSize + FloodItWorld.cellSize / 2) + 70);
        columns = columns + 1; 
      }
      rows = rows + 1;
    }

    if (this.clicks > this.defaultClicks) {
      scene.placeImageXY(new TextImage("YOU LOSE", 50, FontStyle.BOLD, Color.RED), 909, 412);
      scene.placeImageXY(new TextImage("Press R to try again!", 25, FontStyle.BOLD,
          Color.RED), 909, 500);
    }
    return scene;
  }


  //display score
  int displayScore() {
    if (this.clicks > 0) {
      return this.clicks;
    } else {
      return 0;
    }
  }

  //press r to reset the game
  public void onKeyEvent(String k) {
    if (k.equals("r")) {
      this.board = makeBoard();
      this.clicks = 0;
      this.ticks = 0.0;
      setAdjacent();
    } else {
      return;
    }
  }

  //  updates the board when a player clicks
  public void onMouseClicked(Posn p) {
    if ((p.x < 70 || p.x > (this.size + 3.5) * FloodItWorld.cellSize)
        || (p.y < 70 || p.y > (this.size + 3.5) * FloodItWorld.cellSize)) {
      return;
    } else if (this.getCell(p).color.equals(this.board.get(0).get(0).color)) {
      return;
    } else {
      this.floods.clear();
      this.floods.add(board.get(0).get(0));
      this.prev = this.board.get(0).get(0).color;
      this.now = this.getCell(p).color;
      this.clicks = clicks + 1;
    }

  }



  // set top right bottom left for cells
  void setAdjacent() {
    for (int row = 0; row < this.size; row = row + 1) {
      for (int columns = 0; columns < this.size; columns = columns + 1) {
        Cell orgin = board.get(row).get(columns);
        if (orgin.y != 0) {
          orgin.setLeft(board.get(orgin.x).get(orgin.y - 1));
        }
        if (orgin.y != this.size - 1) {
          orgin.setRight(board.get(orgin.x).get(orgin.y + 1));
        }
        if (orgin.x != 0) {
          orgin.setTop(board.get(orgin.x - 1).get(orgin.y));
        }
        if (orgin.x != this.size - 1) {
          orgin.setBottom(board.get(orgin.x + 1).get(orgin.y));
        }
      }
    }
  }





  // get the cell clicked
  Cell getCell(Posn p) {
    int px = (p.x - 70 - (FloodItWorld.cellSize / 2)) / FloodItWorld.cellSize;
    int py = (p.y - 70 - (FloodItWorld.cellSize / 2)) / FloodItWorld.cellSize;
    return this.board.get(px).get(py);
  }


  // updates the world with each tick
  public void onTick() {
    this.ticks += 1;
    ArrayList<Cell> floodedSquares = new ArrayList<Cell>();
    for (Cell c : this.floods) {
      this.changeColor(c, floodedSquares);
    }
    this.floods.clear();
    this.floods.addAll(floodedSquares);
    floodedSquares.clear();
  }






  //changes the color of next flood cell and updates flooding
  public void changeColor(Cell target, ArrayList<Cell> flooding) {
    target.color = this.now;
    target.flooded = true;
    flooding.addAll(target.checkFlood(this.prev));
  }


  //check if every cell is flooded
  public boolean allFlooded() {
    boolean result = true;
    for (int row = 0; row < this.size; row = row + 1) {
      for (int columns = 0; columns < this.size; columns = columns + 1) {
        if (this.board.get(row).get(columns).color == this.board.get(0).get(0).color) {
          result = result && true;
        } else {
          result = result && false;
        }
      }
    }
    return result;

  }

  //make the last scene
  public WorldScene lastScene(String str) {
    WorldScene last = this.makeScene();
    WorldImage endMsg = new TextImage(str, 50, FontStyle.BOLD, 
        new Color(230, 255, 153));
    last.placeImageXY(endMsg, 909, 412);
    return last;
  }

  //  end the game if user win
  public WorldEnd worldEnds() {
    if (this.clicks <= this.defaultClicks && allFlooded() ) {
      return new WorldEnd(true, this.lastScene("You Win, Nice Try!"));
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

}

//all the tests and run the game
class ExampleFloodIt {

  Cell c;
  Cell c1;
  Cell c2;
  Cell c3;
  FloodItWorld a;
  Random rand = new Random();
  ArrayList<Cell> row1;
  ArrayList<Cell> row2;

  //initialization
  void init() {
    a = new FloodItWorld(2,6);
    row1 = new ArrayList<Cell>(Arrays.asList(c, c1));
    row2 = new ArrayList<Cell>(Arrays.asList(c2, c3));
    c = new Cell(0, 0, Color.blue, false);
    c1 = new Cell(0, 20, Color.red, false);
    c2 = new Cell(20, 0, Color.magenta, false);
    c3 = new Cell(20, 20, Color.pink, false);


  }

  // test getCell method
  void testgetcell(Tester t) {

    Cell x = new Cell(0, 0, Color.blue, false);
    Cell x1 = new Cell(0, 20, Color.red, false);
    Cell x2 = new Cell(20, 0, Color.magenta, false);
    Cell x3 = new Cell(20, 20, Color.pink, false);
    ArrayList<Cell> rowx = new ArrayList<Cell>(Arrays.asList(x, x1));
    ArrayList<Cell> rowx1 = new ArrayList<Cell>(Arrays.asList(x2, x3));
    FloodItWorld cworld = new FloodItWorld(new ArrayList<ArrayList<Cell>>(Arrays.asList(rowx, 
        rowx1)));
    t.checkExpect(cworld.getCell(new Posn(71,71)), x);
    t.checkExpect(cworld.getCell(new Posn(71,111)), x1);
    t.checkExpect(cworld.getCell(new Posn(111,71)), x2);
    t.checkExpect(cworld.getCell(new Posn(111,111)), x3);
  }

  //test constructor for class FloodItWorld
  void testconstructor(Tester t) {
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 3 to 8 colors, you inputted: " +
            "9"), 
        "FloodItWorld", 20, 9);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 3 to 8 colors, you inputted: " +
            "2"), 
        "FloodItWorld", 20, 2);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 3 to 8 colors, you inputted: " +
            "100"), 
        "FloodItWorld", 20, 100);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 3 to 8 colors, you inputted: " +
            "-100"), 
        "FloodItWorld", 20, -100);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 3 to 8 colors, you inputted: " +
            "0"), 
        "FloodItWorld", 20, 0);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 2x2 to 26x26 for size, you inputted: " +
            "27"), 
        "FloodItWorld", 27, 3);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 2x2 to 26x26 for size, you inputted: " +
            "1"), 
        "FloodItWorld", 1, 3);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 2x2 to 26x26 for size, you inputted: " +
            "0"), 
        "FloodItWorld", 0, 3);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 2x2 to 26x26 for size, you inputted: " +
            "100"), 
        "FloodItWorld", 100, 3);
    t.checkConstructorException(
        new IllegalArgumentException("Only allowed from 2x2 to 26x26 for size, you inputted: " +
            "-1"), 
        "FloodItWorld", -1, 3);

  }

  //test random
  void testrandom(Tester t) {
    init();
    ArrayList<Color> colorExample = new ArrayList<Color>();
    t.checkExpect(colorExample.size(), 0);
    colorExample.add(FloodItWorld.defaultColors.get(rand.nextInt(a.numColor)));
    t.checkExpect(colorExample.size(), 1);
    colorExample.add(FloodItWorld.defaultColors.get(rand.nextInt(a.numColor)));
    t.checkExpect(colorExample.size(), 2);
    colorExample.add(FloodItWorld.defaultColors.get(rand.nextInt(a.numColor)));
    t.checkExpect(colorExample.size(), 3);
    colorExample.add(FloodItWorld.defaultColors.get(rand.nextInt(a.numColor)));
    colorExample.add(FloodItWorld.defaultColors.get(rand.nextInt(a.numColor)));
    colorExample.add(FloodItWorld.defaultColors.get(rand.nextInt(a.numColor)));
    t.checkExpect(colorExample.size(), 6);
  }

  //test makeboard method
  void testmakeboard(Tester t) {
    init(); 
    t.checkExpect(a.makeBoard().size(), a.board.size());
    t.checkExpect(a.board.size(), 2);
    t.checkExpect(a.makeBoard().size(), 2);
  }

  //test drawCell method
  void testdrawcell(Tester t) {
    init();
    t.checkExpect(c.drawCell(), new OverlayImage(new RectangleImage(20, 
        20, "outline", Color.WHITE), new RectangleImage(20, 20, "solid", Color.BLUE)));
    t.checkExpect(c1.drawCell(), new OverlayImage(new RectangleImage(20, 
        20, "outline", Color.WHITE), new RectangleImage(20, 20, "solid", Color.RED)));
    t.checkExpect(c2.drawCell(), new OverlayImage(new RectangleImage(20, 
        20, "outline", Color.WHITE), new RectangleImage(20, 20, "solid", Color.MAGENTA)));
  }

  //test displayScore method
  void testdisplacescore(Tester t) {
    init();
    t.checkExpect(a.displayScore(), 0);
    a.clicks = 1;
    t.checkExpect(a.displayScore(), 1);
    a.clicks = 25;
    t.checkExpect(a.displayScore(), 25);
  }

  //test all the setters
  void testsetters(Tester t) {
    init();
    t.checkExpect(c.top, c);
    t.checkExpect(c1.bottom, c1);
    t.checkExpect(c1.top, c1);
    t.checkExpect(c.bottom, c);
    t.checkExpect(c2.left, c2);
    t.checkExpect(c3.right, c3);

    c1.setBottom(c);
    t.checkExpect(c.top, c1);
    t.checkExpect(c1.bottom, c);

    c1.setTop(c);
    t.checkExpect(c1.top, c);
    t.checkExpect(c.bottom, c1);

    c2.setLeft(c3);
    t.checkExpect(c2.left, c3);
    t.checkExpect(c3.right, c2);

    c2.setRight(c3);
    t.checkExpect(c2.right, c3);
    t.checkExpect(c3.left, c2);
  }

  //test allFlooded method
  void testallflooded(Tester t) {
    Cell ca = new Cell(0, 0, Color.blue, false);
    Cell cb = new Cell(0, 1, Color.red, false);
    Cell cc = new Cell(1, 0, Color.orange, true);
    Cell cd = new Cell(1, 1, Color.pink, true);
    ArrayList<Cell> rowa = new ArrayList<Cell>(Arrays.asList(ca, cb));
    ArrayList<Cell> rowb = new ArrayList<Cell>(Arrays.asList(cc, cd));
    FloodItWorld b = new FloodItWorld(new ArrayList<ArrayList<Cell>>(Arrays.asList(rowa, 
        rowb)));
    t.checkExpect(b.allFlooded(), false);
    t.checkExpect(ca.color, Color.blue);
    t.checkExpect(cb.color, Color.red);
    t.checkExpect(cc.color, Color.orange);
    t.checkExpect(cd.color, Color.pink);
    cb.color = Color.blue;
    t.checkExpect(b.allFlooded(), false);
    cc.color = Color.blue;
    t.checkExpect(b.allFlooded(), false);
    cd.color = Color.blue;
    t.checkExpect(b.allFlooded(), true);


  }

  //test setAdjacent method
  void testsetAdjacent(Tester t) {
    Cell ca = new Cell(0, 0, Color.blue, false);
    Cell cb = new Cell(0, 1, Color.red, false);
    Cell cc = new Cell(1, 0, Color.magenta, true);
    Cell cd = new Cell(1, 1, Color.pink, true);
    ArrayList<Cell> rowa = new ArrayList<Cell>(Arrays.asList(ca, cb));
    ArrayList<Cell> rowb = new ArrayList<Cell>(Arrays.asList(cc, cd));
    FloodItWorld b = new FloodItWorld(new ArrayList<ArrayList<Cell>>(Arrays.asList(rowa, 
        rowb)));

    b.setAdjacent();
    t.checkExpect(ca.right, cb);
    t.checkExpect(cb.left, ca);
    t.checkExpect(cc.top, ca);
    t.checkExpect(ca.bottom, cc);
    t.checkExpect(ca.left, ca);
    t.checkExpect(ca.top, ca);
    t.checkExpect(cb.right, cb);
    t.checkExpect(cb.top, cb);
    t.checkExpect(cc.left, cc);
    t.checkExpect(cc.bottom, cc);
    t.checkExpect(cd.top, cb);
    t.checkExpect(cb.bottom, cd);
    t.checkExpect(cd.left, cc);
    t.checkExpect(cd.right, cd);  
  }

  //test onKeyEvent method
  void testOnKeyEventMethod(Tester t) {
    Cell ca = new Cell(0, 0, Color.blue, false);
    Cell cb = new Cell(0, 1, Color.red, false);
    Cell cc = new Cell(1, 0, Color.orange, true);
    Cell cd = new Cell(1, 1, Color.pink, true);
    ArrayList<Cell> rowa = new ArrayList<Cell>(Arrays.asList(ca, cb));
    ArrayList<Cell> rowb = new ArrayList<Cell>(Arrays.asList(cc, cd));
    ArrayList<ArrayList<Cell>> testBoard = new ArrayList<ArrayList<Cell>>(Arrays.asList(rowa, 
        rowb));
    FloodItWorld b = new FloodItWorld(new ArrayList<ArrayList<Cell>>(Arrays.asList(rowa, 
        rowb)));

    b.clicks = 3;
    b.ticks = 100.5;
    t.checkExpect(b.clicks, 3);
    t.checkExpect(b.ticks, 100.5);
    b.onKeyEvent("c");
    t.checkExpect(b.clicks, 3);
    t.checkExpect(b.ticks, 100.5);
    t.checkExpect(b.board.get(0).get(0).equals(ca), true);
    t.checkExpect(b.board.equals(testBoard), true);
    b.onKeyEvent("r");
    t.checkExpect(b.clicks, 0);
    t.checkExpect(b.ticks, 0.0);
    t.checkExpect(b.board.get(0).get(0).equals(ca), false);
    t.checkExpect(b.board.equals(testBoard), false);

  }

  //test onMouseClicked method
  void testOnMouseClicked(Tester t) {
    Cell ca = new Cell(0, 0, Color.blue, true);
    Cell cb = new Cell(0, 1, Color.red, false);
    Cell cc = new Cell(1, 0, Color.orange, false);
    Cell cd = new Cell(1, 1, Color.pink, false);
    ArrayList<Cell> rowa = new ArrayList<Cell>(Arrays.asList(ca, cb));
    ArrayList<Cell> rowb = new ArrayList<Cell>(Arrays.asList(cc, cd));
    ArrayList<ArrayList<Cell>> testBoard = new ArrayList<ArrayList<Cell>>(Arrays.asList(rowa, 
        rowb));
    FloodItWorld b = new FloodItWorld(testBoard);
    t.checkExpect(b.prev, null);
    t.checkExpect(b.clicks, 0);
    t.checkExpect(b.now, null);
    b.onMouseClicked(new Posn(101,101));
    t.checkExpect(b.prev, Color.BLUE);
    t.checkExpect(b.clicks, 1);
    t.checkExpect(b.now, Color.pink);
  }
  
  //test onTick method (Since this is all the methods used in onTick):
  //test changeColor method
  void testChangeColor(Tester t) {
    Cell ca = new Cell(0, 0, Color.blue, true);
    Cell cb = new Cell(0, 1, Color.red, false);
    Cell cc = new Cell(1, 0, Color.orange, false);
    Cell cd = new Cell(1, 1, Color.pink, false);
    ArrayList<Cell> rowa = new ArrayList<Cell>(Arrays.asList(ca, cb));
    ArrayList<Cell> rowb = new ArrayList<Cell>(Arrays.asList(cc, cd));
    ArrayList<ArrayList<Cell>> testBoard = new ArrayList<ArrayList<Cell>>(Arrays.asList(rowa, 
        rowb));
    FloodItWorld b = new FloodItWorld(testBoard);
    b.setAdjacent();
    b.prev = Color.blue;
    b.now = Color.blue;
    t.checkExpect(cb.flooded, false);
    b.changeColor(cb, b.floods);
    t.checkExpect(cb.flooded, true);
  }
  
  //test checkFlood method
  void testCheckFlood(Tester t) {
    Cell ca = new Cell(0, 0, Color.blue, true);
    Cell cb = new Cell(0, 1, Color.red, false);
    Cell cc = new Cell(1, 0, Color.orange, false);
    Cell cd = new Cell(1, 1, Color.pink, false);
    ArrayList<Cell> rowa = new ArrayList<Cell>(Arrays.asList(ca, cb));
    ArrayList<Cell> rowb = new ArrayList<Cell>(Arrays.asList(cc, cd));
    ArrayList<ArrayList<Cell>> testBoard = new ArrayList<ArrayList<Cell>>(Arrays.asList(rowa, 
        rowb));
    FloodItWorld b = new FloodItWorld(testBoard);
    b.setAdjacent();
    t.checkExpect(ca.checkFlood(Color.red), new ArrayList<Cell>(Arrays.asList(cb)));
    t.checkExpect(ca.checkFlood(Color.blue), new ArrayList<Cell>(Arrays.asList(ca)));
    Cell n = new Cell(0, 0, Color.blue, false);
    Cell nn = new Cell(0, 1, Color.blue, false);
    Cell nnn = new Cell(1, 0, Color.red, false);
    Cell nnnn = new Cell(1, 1, Color.orange, false);
    ArrayList<Cell> rown = new ArrayList<Cell>(Arrays.asList(n, nn));
    ArrayList<Cell> rownn = new ArrayList<Cell>(Arrays.asList(nnn, nnnn));
    ArrayList<ArrayList<Cell>> testn = new ArrayList<ArrayList<Cell>>(Arrays.asList(rown, 
        rownn));
    FloodItWorld bn = new FloodItWorld(testn);
    bn.setAdjacent();
    t.checkExpect(n.checkFlood(Color.red), new ArrayList<Cell>(Arrays.asList(nnn)));
    t.checkExpect(n.checkFlood(Color.blue), new ArrayList<Cell>(Arrays.asList(n, nn)));
  }
  
  //test readyToFlood method
  void testreadyToFlood(Tester t) {
    Cell n = new Cell(0, 0, Color.blue, false);
    Cell nn = new Cell(0, 1, Color.blue, false);
    Cell nnn = new Cell(1, 0, Color.red, false);
    Cell nnnn = new Cell(1, 1, Color.orange, false);
    ArrayList<Cell> flooding = new ArrayList<Cell>(Arrays.asList(n, nn));
    n.readyToFlood(Color.blue, flooding);
    t.checkExpect(flooding, new ArrayList<Cell>(Arrays.asList(n, nn)));
    t.checkExpect(flooding.size(), 2);
    nnnn.readyToFlood(Color.orange, flooding);
    t.checkExpect(flooding.size(), 3);
    t.checkExpect(flooding, new ArrayList<Cell>(Arrays.asList(n, nn, nnnn)));
    nnn.readyToFlood(Color.pink, flooding);
    t.checkExpect(flooding.size(), 3);
    t.checkExpect(flooding, new ArrayList<Cell>(Arrays.asList(n, nn, nnnn)));
  }
  
  //test lastScene method
  void testLastScene(Tester t) {
    WorldImage winMsg = new TextImage("You Win, Nice Try!", 50, FontStyle.BOLD, new Color(230, 255, 152));
    init();
    
    WorldScene aScene = a.makeScene();
    aScene.placeImageXY(winMsg, 909, 412);
  }
  
  //test worldEnds method
  void testworldends(Tester t) {
    Cell ca = new Cell(0, 0, Color.blue, true);
    Cell cb = new Cell(0, 1, Color.blue, false);
    Cell cc = new Cell(1, 0, Color.blue, false);
    Cell cd = new Cell(1, 1, Color.pink, false);
    ArrayList<Cell> rowa = new ArrayList<Cell>(Arrays.asList(ca, cb));
    ArrayList<Cell> rowb = new ArrayList<Cell>(Arrays.asList(cc, cd));
    ArrayList<ArrayList<Cell>> testBoard = new ArrayList<ArrayList<Cell>>(Arrays.asList(rowa, 
        rowb));
    FloodItWorld b = new FloodItWorld(testBoard);
    t.checkExpect(b.worldEnds(), new WorldEnd(false, b.makeScene()));
    cd.color = Color.blue;
    t.checkExpect(b.worldEnds(), new WorldEnd(true, b.lastScene("You Win, Nice Try!")));
    b.clicks = 10;
    t.checkExpect(b.worldEnds(), new WorldEnd(false, b.makeScene()));
  }

  //run the game
  //CHANGE YOUR PREFERENCES (GAME SETTINGS) HERE
  void testGame(Tester t) {
    FloodItWorld game = new FloodItWorld(26, 8);
    game.bigBang(1200, 800, 0.1);

  }
}