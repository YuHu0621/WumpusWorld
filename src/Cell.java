

public class Cell implements Comparable<Cell>{
	int x;
	int y;
	int color;
	Cell parent;
	boolean hasGlitter;
	boolean hasSmell;
	boolean hasBreeze;
	boolean hasWumpus;
	boolean hasPit;
	boolean safe;
	// 0 means white and unchecked, 1 means gray and looked at, 2 means black and expanded.
	Cell(int x, int y) {
		this.x = x;
		this.y = y;
		//initialize the cell as unvisited and no parent
		color = 0;
		parent = null;
		
		//initialize the boolean to false
		hasGlitter = false;
		hasSmell = false;
		hasBreeze = false;
		hasWumpus = false;
		hasPit = false;
		safe = false;
	}

	public void perceiveBreeze (){
		this.hasBreeze = true;
	}
	
	public void perceiveGlitter(){
		this.hasGlitter = true;
	}
	
	public void perceiveSmell(){
		this.hasSmell = true;
	}
	
	public boolean hasGlitter(){
		return hasGlitter;
	}
	
	public boolean hasSmell(){
		return hasSmell;
	}
	
	public boolean hasBreeze(){
		return hasBreeze;
	}
	public void perceiveWumpus(){
		this.hasWumpus = true;
	}
	
	public void perceivePit(){
		this.hasPit = true;
	}
	
	public boolean hasWumpus(){
		return hasWumpus;
	}
	
	public boolean hasPit(){
		return hasPit;
	}
	
	public boolean grabGold(){
		if(hasGlitter()){
			hasGlitter = false;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean killWumpus(){
		if(hasWumpus){
			hasWumpus = false;
			return true;
		}else{
			return false;
		}
	}
	/**
	 * get x pos
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * get y pos
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * set color gray
	 */
	public void setColorGray(){
		color = 1;
	}

	/**
	 * set color black
	 */
	public void setColorBlack(){
		color = 2;
	}
	public void setColorWhite(){
		color = 0;
	}
	public boolean isBlack(){
		if(color == 2){
			return true;
		}
		return false;
	}
	
	public boolean isGray(){
		if(color == 1){
			return true;
		}
		return false;
	}
	public boolean isWhite(){
		if(color == 0){
			return true;
		}
		return false;
	}
	public void setParent(Cell c){
		parent = c;
	}
	
	public Cell getParent(){
		return parent;
	}
	
	

	@Override
	public int compareTo(Cell o) {
		return Math.abs(getX() - o.getX() + getY() - o.getY());
	}
}
