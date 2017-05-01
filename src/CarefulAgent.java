
public class CarefulAgent extends agent {

	public CarefulAgent(WumpusMaze w) {
		super(w);
		// TODO Auto-generated constructor stub
	}

	
	
	
	public boolean dfsRecursive() {
		
		Cell frontier = dfsStack.pop();
		
		if (frontier == null) {
			return false;
		}
		if(frontier != maze[0][0] || start == true)
			//action(frontier);
		
		if (reachGoal(frontier)) {
			// base case
			
			return true;
		} else {
			
			updateWumpusWorld(frontier);
			killWumpus(frontier);
			
			int x = frontier.getX();
			int y = frontier.getY();
			if(frontier.hasPit() && frontier.hasWumpus()){
    			frontier.setColorWhite();
    			kb.findPit(x, y);
    			kb.findWumpus(x,y);
    			return false;
    		} else if(frontier.hasWumpus()){
    			frontier.setColorWhite();
    			kb.findWumpus(x,y);
    		//	return false;
    		}else if (frontier.hasPit()){
    			frontier.setColorWhite();
    			kb.findPit(x,y);
    			return false;
    		}
			kb.isSafe(x, y);
			//only certain safe cell
			killWumpus(frontier);
			if (legalPosition(x - 1, y)) {
				if (maze[x - 1][y].isWhite()) {
					if( kb.isACell(x, y) || (!kb.noPit(x-1, y) && !kb.noWumpus(x-1, y))){
						maze[x - 1][y].setColorGray();
						dfsStack.push(maze[x - 1][y]);
						maze[x - 1][y].setParent(frontier);
						//if it's not a right move, it won't choose the cell
						if(dfsRecursive()){
	        				return true;
	        			}
					}
				}
			}
			if (legalPosition(x + 1, y)) {
				if (maze[x + 1][y].isWhite() ) {
					if( kb.isACell(x, y) || (!kb.noPit(x+1, y) && !kb.noWumpus(x+1, y))){
						maze[x + 1][y].setColorGray();
						dfsStack.push(maze[x + 1][y]);
						maze[x + 1][y].setParent(frontier);
						if(dfsRecursive()){
	        				return true;
	        			}
					}
				}
			}
			if (legalPosition(x, y - 1)) {
				if (maze[x][y - 1].isWhite() ) {
					if(kb.isACell(x, y) || (!kb.noPit(x, y-1) && !kb.noWumpus(x, y-1))){
						maze[x][y - 1].setColorGray();
						dfsStack.push(maze[x][y - 1]);
						maze[x][y - 1].setParent(frontier);
						if(dfsRecursive()){
	        				return true;
	        			}
					}
				}
			}
			if (legalPosition(x, y + 1)) {
				if (maze[x][y + 1].isWhite() ) {
					if( kb.isACell(x, y) || (!kb.noPit(x, y+1) && !kb.noWumpus(x, y+1))){
						maze[x][y + 1].setColorGray();
						dfsStack.push(maze[x][y + 1]);
						maze[x][y + 1].setParent(frontier);
						if(dfsRecursive()){
	        				
	        				return true;
	        			}
					}
				}
			}
		}
		
			return false;
		}

	public boolean killWumpus(Cell c){
    	int x = c.getX();
    	int y = c.getY();
    	if(legalPosition(x-1,y)){
    		if(kb.hasWumpus(x-1, y)){
    			if(maze[x-1][y].hasWumpus()){
    				wumpusWorld.removeWumpus(x-1, y);
	    			kb.kill(x-1, y);
	    			hasArrow = false;
	    			score += 50;
	    			dfsStack.push(maze[x-1][y]);
	    			maze[x-1][y].setColorGray();
	    			maze[x-1][y].setParent(c);
    			}
    		}
    	}
    	if(legalPosition(x+1,y)){
    		if(kb.hasWumpus(x+1, y) && maze[x+1][y].hasWumpus()){
    			wumpusWorld.removeWumpus(x+1, y);
    			kb.kill(x+1, y);
    			hasArrow = false;
    			score += 50;
    			dfsStack.push(maze[x+1][y]);
    			maze[x+1][y].setColorGray();
    			maze[x+1][y].setParent(c);
    		}
    	}
    	if(legalPosition(x,y-1) && maze[x][y-1].hasWumpus()){
    		if(kb.hasWumpus(x, y-1)){
    			wumpusWorld.removeWumpus(x, y-1);
    			kb.kill(x, y-1);
    			hasArrow = false;
    			score += 50;
    			dfsStack.push(maze[x][y-1]);
    			maze[x][y-1].setColorGray();
    			maze[x][y-1].setParent(c);
    		}
    	}
    	if(legalPosition(x,y+1) && maze[x][y+1].hasWumpus()){
    		if(kb.hasWumpus(x, y+1)){
    			wumpusWorld.removeWumpus(x, y+1);
    			kb.kill(x, y+1);
    			hasArrow = false;
    			score += 50;
    			dfsStack.push(maze[x-1][y]);
    			maze[x][y+1].setColorGray();
    			maze[x][y+1].setParent(c);
    		}
    	}
    	if(!hasArrow){
    		System.out.println("You shoot the wumpus!");
    		return true;
    	}
    	return false;
    }
	
	}


