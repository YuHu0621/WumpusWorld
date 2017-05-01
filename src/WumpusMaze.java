/*
 * WumpusMaze class captures the relevant aspects of the Wumpus world
 */



/**
 * Class to represent a wumpus world maze. 
 */
public class WumpusMaze {
  


    // Important aspects of the maze
    private Cell maze[][] = null;
    private int mazeSize = 0;

    private boolean gameOver = false;
    private int totalReward = 0;

    // Possible actions the user may execute
    public static final int FORWARD_ACTION = 1;
    public static final int LEFT_ACTION = 2;
    public static final int RIGHT_ACTION = 3;
    public static final int GRAB_ACTION = 4;
    public static final int SHOOT_ACTION = 5;

    // Is this possible legal?
    private boolean legalPosition(int x, int y) {
        if (x < 0) return false;
        if (y < 0) return false;
        if (x > (mazeSize - 1)) return false;
        if (y > (mazeSize - 1)) return false;
        return true;
    }

    // Does this location have gold?
    private boolean hasGold(int x, int y) {
        return maze[x][y].hasGlitter;
    }

    // Does this location have a pit?
    private boolean hasPit(int x, int y) {
        return maze[x][y].hasPit();
    }
    
    


	// Does this location have a wumpus?
    private boolean hasWumpus(int x, int y) {
        return maze[x][y].hasWumpus() ;
    }

  
    // Execute remove wumpus (after successfully shooting it) at this position
    public boolean removeWumpus(int x, int y) {
        return maze[x][y].killWumpus();
    }

    // Show the actual board (used for debugging purposes)
    private void showBoard() {
        for (int y = mazeSize-1; y >= 0; y--) {
            for (int x = 0; x < mazeSize; x++) {
                System.out.print("|");
                if (hasGold(x,y))
                    System.out.print("G");
                else
                    System.out.print(" ");
                if (hasPit(x,y))
                    System.out.print("P");
                else
                    System.out.print(" ");
                if (hasWumpus(x,y))
                    System.out.print("W");
                else
                    System.out.print(" ");
               
            }
            System.out.println("|");
        }
    }


    
    private void initialize(int size) {
        mazeSize = size;
        gameOver = false;
        totalReward = 0;
        maze = new Cell[mazeSize][mazeSize];
        for (int i = 0; i < mazeSize; i++){
            for (int j = 0; j < mazeSize; j++){
                maze[i][j] = new Cell(i,j);
            }
        }
        // Make sure there is some path from the initial spot to the goal
        int[][] planMaze = new int[mazeSize][mazeSize];
        planMaze[0][0] = -1;
        planMaze[mazeSize-1][mazeSize-1] = -1;
        
        int x = 0;
        int y = 0;
        while ((x < (mazeSize - 1)) && (y < (mazeSize - 1))) {
            if (Math.random() < 0.5) {
                x = x + 1;
            }
            else {
                y = y + 1;
            }
           // maze[x][y].safe();
        }
        if (x < (mazeSize - 1)) {
            for (int i = x; i < (mazeSize - 1); i++)
               planMaze[i][mazeSize-1]= -1;
        }
        else if (y < (mazeSize - 1)) {
            for (int i = y; i < (mazeSize - 1); i++)
            	planMaze[mazeSize-1][i]= -1;
        }

       
        // Place 1 wumpus randomly
     
            x = (int) Math.floor(Math.random() * mazeSize);
            y = (int) Math.floor(Math.random() * mazeSize);
            if (planMaze[x][y]!= -1) {
                maze[x][y].perceiveWumpus();
                
            }
        

        // Place mazeSize - 1 pits randomly
            
            int pitNum = 0;
        while (pitNum < mazeSize - 1) {
                x = (int) Math.floor(Math.random() * mazeSize);
                y = (int) Math.floor(Math.random() * mazeSize);
                if (planMaze[x][y]!= -1) {
                    if (!maze[x][y].hasPit()) {
                        maze[x][y].perceivePit();
                        pitNum++;
                    }
                } 
        }

        // Place an item of gold randomly
       
       
            x = (int) Math.floor(Math.random() * mazeSize);
            y = (int) Math.floor(Math.random() * mazeSize);
            if (planMaze[x][y] != -1 && !maze[x][y].hasPit() && !maze[x][y].hasWumpus()) {
                maze[x][y].perceiveGlitter();
               
            }
        
        setSmell();
        setBreeze();

        // uncomment for debugging purposes
        showBoard();
    }

    /**
     * Create an initial maze of size 3 if no argument is passed
     */
    public WumpusMaze() {
       initialize(3);
    	
    }

    public void setSmell(){
    	for(int i = 0; i < mazeSize; i++){
    		for(int j = 0; j < mazeSize; j++){
    			  if (legalPosition(i-1,j)) {
    	            if (hasWumpus(i-1,j)) {
    	            	maze[i][j].perceiveSmell();
    	            }
    			  }
    	        if (legalPosition(i+1,j)) {
    	            if (hasWumpus(i+1,j)) {
    	            	maze[i][j].perceiveSmell();
    	            }
    	        }
    	        if (legalPosition(i,j-1)) {
    	            if (hasWumpus(i,j-1)) {
    	            	maze[i][j].perceiveSmell();
    	            }
    	        }
    	        if (legalPosition(i,j+1)) {
    	            if (hasWumpus(i,j+1)) {
    	            	maze[i][j].perceiveSmell();
    	            }
    	        }
    		}
    	}
    }
    
    public void setBreeze(){
    	for(int i = 0; i < mazeSize; i++){
    		for(int j = 0; j < mazeSize; j++){
    			  if (legalPosition(i-1,j)) {
    	            if (hasPit(i-1,j)) {
    	            	maze[i][j].perceiveBreeze();
    	            }
    			  }
    	        if (legalPosition(i+1,j)) {
    	            if (hasPit(i+1,j)) {
    	            	maze[i][j].perceiveBreeze();
    	            }
    	        }
    	        if (legalPosition(i,j-1)) {
    	            if (hasPit(i,j-1)) {
    	            	maze[i][j].perceiveBreeze();
    	            }
    	        }
    	        if (legalPosition(i,j+1)) {
    	            if (hasPit(i,j+1)) {
    	            	maze[i][j].perceiveBreeze();
    	            }
    	        }
    		}
    	}
    }
    /**
     * Return the value of the gameOver flag
     * @return
     */
    public boolean gameOver() {
        return gameOver;
    }

    /**
     * Return the value of totalReward
     *
     * @return
     */
    public int totalReward() {
        return totalReward;
    }

    public Cell[][] getMaze(){
    	return maze;
    }
}
