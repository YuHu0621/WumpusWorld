import java.util.Stack;

public abstract class agent {
	 // Variable representing the Wumpus world
	protected int size;
    protected WumpusMaze wumpusWorld;
    protected Cell[][] maze;
    // Aspects of the current world
    protected int currX;
    protected int currY;
    protected int facingAngle;
    protected boolean hasArrow;
    
    protected wumpusKnowledgeBase kb;
    //Knowledge base
    
    protected int score = 0;
    protected Stack<Cell> dfsStack = new Stack<Cell>();
    protected boolean start = false;
    public agent(WumpusMaze w){
    	wumpusWorld = w;
    	maze = wumpusWorld.getMaze();
    	kb = new wumpusKnowledgeBase(3);
    	size = 3;
    	//place agent
		placeAgent(0,0, 0, true);
    }
    
   
    
    public void placeAgent(int i, int j, int angle, boolean hasArrow){
    	currX = i;
    	currY = j;
    	facingAngle = angle;
    	this.hasArrow = hasArrow;
    }

   

    // Show a representation of the maze percepts
    private void showBoard() {
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                System.out.print("|");
                if ((x == currX) && (y == currY)) {
                	if(facingAngle == 0){
                		System.out.print(">");
                	}else if(facingAngle == 90){
                		System.out.print("^"); 
                	}else if(facingAngle == 180){
                		System.out.print("<"); 
                	}else if(facingAngle == 270){
                		System.out.print("v");
                	}

                }
                else
                    System.out.print(" ");
                System.out.print(kb.isSafe(x, y) ? "!" : " ");
                System.out.print((!maze[x][y].isWhite() ? "." : " "));
                System.out.print((kb.hasGlitter(x, y) ? "g" : " "));
                System.out.print((kb.hasPit(x,y) ? "P" : kb.hasBreeze(x,y) ? "b" : " "));
                System.out.print((kb.hasWumpus(x,y) ? "W" : kb.hasSmell(x,y) ? "s" : " "));
               
            }
            System.out.println("|");
        }
        System.out.println("");
    }
    
 
 
    
    public void swap(int d1, int d2, Cell[] cells){
    	Cell tmp = cells[d1];
		cells[d1] = cells[d2];
		cells[d2] = tmp;
    }
    protected boolean legalPosition(int x, int y) {
        if (x < 0) {
        	return false;
        }
        if (y < 0) {
        	return false;
        }
        if (x > (size - 1)) {
        	return false;
        }
        if (y > (size - 1)) {
        	return false;
        }
        return true;
    }
    
    
    public boolean nextToVisitedCell(Cell c){
    	int x = c.getX();
    	int y = c.getY();
    	if(legalPosition(x-1,y)){
    		if(!maze[x-1][y].isWhite()){
    			return true;
    		}
    	}
    	if(legalPosition(x+1,y)){
    		if(!maze[x+1][y].isWhite()){
    			return true;
    		}
    	}
    	if(legalPosition(x,y-1)){
    		if(!maze[x][y-1].isWhite() ){
    			return true;
    		}
    	}
    	if(legalPosition(x,y+1)){
    		if(!maze[x][y+1].isWhite() ){
    			return true;
    		}
    	}
    	return false;
    }
    public boolean reachGoal(Cell c){
    	int x = c.getX();
    	int y = c.getY();
    	if(x == size -1 && y == size -1){
    		System.out.println("You reach the goal!");
    		return true;
    	}
    	return false;
    }

    private Stack<Cell> moveStack = new Stack<Cell>();
    //TODO
    public void getPath(Cell c){
    	//a stack that store a path to the goal
    	moveStack.push(c);
		
		if(c.equals(maze[0][0]))
		{
			move();
			//base case
		}
		else
		{
			getPath(c.getParent());
		}
    }
   public void action(Cell c){
	   		start = true;
			showBoard();
		
			if(c.hasGlitter()){
	   			c.grabGold();
	   			score += 1000;
			}
			
			int goalX = c.getX();
			int goalY = c.getY();
			// Figures out the direction the robot needs to move from the goal state 
					// and the current orientation of the robot.
					if(facingAngle == 0 ){
						if(goalX<currX){
							facingAngle = 90;
							score --;
							showBoard();
							facingAngle = 180;
							score --;
							showBoard();
							currX = goalX;
							score --;
							showBoard();
						}else if(goalX>currX){
							currX = goalX;
							score --;
							showBoard();
						}
						else
						{
							if(goalY<currY){
								facingAngle = 270;
								score --;
								showBoard();
								currY = goalY;
								score --;
								showBoard();
							}else{
								facingAngle = 90;
								score --;
								showBoard();
								currY = goalY;
								score --;
								showBoard();
							}
								
						}
					}else if(facingAngle == 90 ){
						if(goalY>currY){
							currY = goalY;
							score --;
							showBoard();
						}
						else if(goalY<currY){
							facingAngle = 0;
							score --;
							showBoard();
							facingAngle = 270;
							score --;
							showBoard();
							currY = goalY;
							score --;
							showBoard();
						}
						else if(goalX<currX){
								facingAngle = 180;
								score --;
								showBoard();
								currX = goalX;
								score --;
								showBoard();
							}else{
								facingAngle = 0;
								score --;
								showBoard();
								currX = goalX;
								score --;
								showBoard();
							}		
					}else if(facingAngle == 180 ){
						if(goalX>currX){
							facingAngle = 270;
							score --;
							showBoard();
							facingAngle = 0;
							score --;
							showBoard();
							currX = goalX;
							score --;
							showBoard();
						}else if(goalX<currX){
							currX = goalX;
							score --;
							showBoard();
						}else if(goalY>currY){
								facingAngle= 90;
								score --;
								showBoard();
								currY = goalY;
								score --;
								showBoard();
							}else{
								facingAngle= 270;
								score --;
								showBoard();
								currY = goalY;
								score --;
								showBoard();
							}	
					}
					else if(facingAngle == 270 )
					{
						if(goalY<currY){
							currY = goalY;
							score --;
							showBoard();
						}else if(goalY>currY){
							facingAngle = 0;
							score --;
							showBoard();
							facingAngle = 90;
							score --;
							showBoard();
							currY = goalY;
							score --;
							showBoard();
						}else if(goalX>currX){
								facingAngle = 0;
								score --;
								showBoard();
								currX = goalX;
								score --;
								showBoard();
							}else{
								facingAngle = 180;
								score --;
								showBoard();
								currX = goalX;
								score --;
								showBoard();
							}
								
						}
					if(c.hasPit()){
						score -= 1000;
						System.out.println("Yikes, you fall into the pit");
					}else if(c.hasWumpus()){
						score -= 1000;
						System.out.println("Yikes, you are eaten by the wumpus");
					}
					System.out.println("score: " + score);
					System.out.println("");
		}
		
   
    /**
	 * Pops new cells of the stack moveStack and moves the robot to those locations.
	 **/
	public void move()
	{
		System.out.println("This is the safe path! ");
		Cell currentCell = moveStack.pop();
		currX = currentCell.getX();
		currY = currentCell.getY();
		showBoard();
		while(!moveStack.isEmpty())
		{
			Cell c = moveStack.pop();
			if(c.hasGlitter()){
    			c.grabGold();
    			score += 1000;
    		}
			int goalX = c.getX();
			int goalY = c.getY();
			// Figures out the direction the robot needs to move from the goal state 
					// and the current orientation of the robot.
					if(facingAngle == 0 ){
						if(goalX<currentCell.getX()){
							facingAngle = 90;
							score --;
							showBoard();
							facingAngle = 180;
							score --;
							showBoard();
							currX = goalX;
							score --;
							showBoard();
						}else if(goalX>currentCell.getX()){
							currX = goalX;
							score --;
							showBoard();
						}
						else
						{
							if(goalY<currentCell.getY()){
								facingAngle = 270;
								score --;
								showBoard();
								currY = goalY;
								score --;
								showBoard();
							}else{
								facingAngle = 90;
								score --;
								showBoard();
								currY = goalY;
								score --;
								showBoard();
							}
								
						}
					}else if(facingAngle == 90 ){
						if(goalY>currentCell.getY()){
							currY = goalY;
							score --;
							showBoard();
						}
						else if(goalY<currentCell.getY()){
							facingAngle = 0;
							score --;
							showBoard();
							facingAngle = 270;
							score --;
							showBoard();
							currY = goalY;
							score --;
							showBoard();
						}
						else if(goalX<currentCell.getX()){
								facingAngle = 180;
								score --;
								showBoard();
								currX = goalX;
								score --;
								showBoard();
							}else{
								facingAngle = 0;
								score --;
								showBoard();
								currX = goalX;
								score --;
								showBoard();
							}		
					}else if(facingAngle == 180 ){
						if(goalX>currentCell.getX()){
							facingAngle = 270;
							score --;
							showBoard();
							facingAngle = 0;
							score --;
							showBoard();
							currX = goalX;
							score --;
							showBoard();
						}else if(goalX<currentCell.getX()){
							currX = goalX;
							score --;
							showBoard();
						}else if(goalY>currentCell.getY()){
								facingAngle= 90;
								score --;
								showBoard();
								currY = goalY;
								score --;
								showBoard();
							}else{
								facingAngle= 270;
								score --;
								showBoard();
								currY = goalY;
								score --;
								showBoard();
							}	
					}
					else if(facingAngle == 270 )
					{
						if(goalY<currentCell.getY()){
							currY = goalY;
							score --;
							showBoard();
						}else if(goalY>currentCell.getY()){
							facingAngle = 0;
							score --;
							showBoard();
							facingAngle = 90;
							score --;
							showBoard();
							currY = goalY;
							score --;
							showBoard();
						}else if(goalX>currentCell.getX()){
								facingAngle = 0;
								score --;
								showBoard();
								currX = goalX;
								score --;
								showBoard();
							}else{
								facingAngle = 180;
								score --;
								showBoard();
								currX = goalX;
								score --;
								showBoard();
							}
								
						}
					currentCell = c;
		}
		
	}

    public void updateWumpusWorld(Cell c){
    	int x= c.getX();
    	int y = c.getY();
    	kb.updateKB(x, y, c.hasBreeze(), c.hasSmell(), c.hasGlitter(), c.hasPit(), c.hasWumpus());
    }
    
   
    
    public boolean wellFormed(Cell[] c){
    	if(c.length == 0){
    		return false;
    	}
    	return true;
    }
    
    public boolean dfs(){
    	 dfsStack.push(maze[0][0]);
    	maze[0][0].setColorGray();
   	 return dfsRecursive();
    }
    
    
   
    public abstract boolean dfsRecursive();
}
