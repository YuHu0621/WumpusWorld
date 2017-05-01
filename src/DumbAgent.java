
public class DumbAgent extends agent{

	public DumbAgent(WumpusMaze w) {
		super(w);
		// TODO Auto-generated constructor stub
	}

	Cell lastCell = maze[0][0];
	@Override
	public boolean dfsRecursive() {

		Cell frontier = dfsStack.pop();
		if(frontier != maze[0][0] || start == true)
		//	action(frontier);
		
		if(frontier==null){
			return false;
		}
    	if(reachGoal(frontier)){
    		//base case
    		return true;
    	}else{
    		//current location
    		int x = frontier.getX();
    		int y = frontier.getY();
    		//check pit and wumpus
    		//die if there's one pit or wumpus
    		if(frontier.hasPit() && frontier.hasWumpus()){
    			frontier.setColorWhite();
    			kb.findPit(x, y);
    			kb.findWumpus(x,y);
    			return false;
    		} else if(frontier.hasWumpus()){
    			frontier.setColorWhite();
    			kb.findWumpus(x,y);
    			//return false;
    		}else if (frontier.hasPit()){
    			frontier.setColorWhite();
    			kb.findPit(x,y);
    			return false;
    		}
    	
    		//otherwise, the cell is safe
    		kb.isSafe(x, y);
    		
    		//perceive the world
    		updateWumpusWorld(frontier);
    		killWumpus(frontier);
    		//get fringe cell
    		//uncertain safe cell 
			if (legalPosition(x - 1, y)) {
				if (maze[x-1][y].isWhite()) {
					//if current cell has no breeze or smell, it's neighbor is definitely safe
					//if there's no pit in that cell, you can also push it in
					if( kb.isACell(x, y) || (!kb.hasPit(x-1, y) )){
						maze[x - 1][y].setColorGray();
						dfsStack.push(maze[x - 1][y]);
						maze[x - 1][y].setParent(frontier);
						return dfsRecursive();
	        			
					}
				}
			}
			if (legalPosition(x + 1, y)) {
				if (maze[x + 1][y].isWhite() ) {
					if( kb.isACell(x, y) || (!kb.hasPit(x+1, y))){
						maze[x + 1][y].setColorGray();
						dfsStack.push(maze[x + 1][y]);
						maze[x + 1][y].setParent(frontier);
						return dfsRecursive();
	        			
					}
				}
			}
			if (legalPosition(x, y - 1)) {
				if (maze[x][y - 1].isWhite() ) {
					if(kb.isACell(x, y) || (!kb.hasPit(x, y-1) )){
						maze[x][y - 1].setColorGray();
						dfsStack.push(maze[x][y - 1]);
						maze[x][y - 1].setParent(frontier);
						return dfsRecursive();
	        			
					}
				}
			}
			if (legalPosition(x, y + 1)) {
				if (maze[x][y + 1].isWhite() ) {
					if( kb.isACell(x, y) || (!kb.hasPit(x, y+1))){
						maze[x][y + 1].setColorGray();
						dfsStack.push(maze[x][y + 1]);
						maze[x][y + 1].setParent(frontier);	
						return dfsRecursive();
	        			
					}
				}
			}
			//definitely safe cell
			if (legalPosition(x - 1, y)) {
				if (maze[x - 1][y].isWhite()) {
					if( kb.isACell(x, y) || (!kb.noPit(x-1, y) && !kb.noWumpus(x-1, y))){
						maze[x - 1][y].setColorGray();
						dfsStack.push(maze[x - 1][y]);
						maze[x - 1][y].setParent(frontier);
						//if it's not a right move, it won't choose the cell
						return dfsRecursive();
					}
				}
			}
			if (legalPosition(x + 1, y)) {
				if (maze[x + 1][y].isWhite() ) {
					if( kb.isACell(x, y) || (!kb.noPit(x+1, y) && !kb.noWumpus(x+1, y))){
						maze[x + 1][y].setColorGray();
						dfsStack.push(maze[x + 1][y]);
						maze[x + 1][y].setParent(frontier);
						return dfsRecursive();
					}
				}
			}
			if (legalPosition(x, y - 1)) {
				if (maze[x][y - 1].isWhite() ) {
					if(kb.isACell(x, y) || (!kb.noPit(x, y-1) && !kb.noWumpus(x, y-1))){
						maze[x][y - 1].setColorGray();
						dfsStack.push(maze[x][y - 1]);
						maze[x][y - 1].setParent(frontier);
						return dfsRecursive();
					}
				}
			}
			if (legalPosition(x, y + 1)) {
				if (maze[x][y + 1].isWhite() ) {
					if( kb.isACell(x, y) || (!kb.noPit(x, y+1) && !kb.noWumpus(x, y+1))){
						maze[x][y + 1].setColorGray();
						dfsStack.push(maze[x][y + 1]);
						maze[x][y + 1].setParent(frontier);
						return dfsRecursive();
					}
				}
			}

	}
        	return false;	
	}

	public void printPath(){
		getPath(lastCell);
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
