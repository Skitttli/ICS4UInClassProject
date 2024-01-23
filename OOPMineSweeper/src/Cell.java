public class Cell {
	byte nearbyMines; // maximum 8
	boolean isMine, isFlagged, isVisible;
	String logoShow,logoHide;
	static boolean gameOver;
	
	public Cell () {
		logoShow="-";
		logoHide="0";
		gameOver=false;
	}
	public String getLogo(){
		return logoShow;
	}

	public void setIsFlagged(boolean isFlagged){
		this.isFlagged = isFlagged;
		setIsVisible(true);
		logoShow="f";
	}

	public void setIsMine(boolean isMine){
		this.isMine = isMine;
		logoHide="o";
	}

	public void setIsVisible(boolean isVisible){
		this.isVisible = isVisible;
		logoShow=logoHide;
		if(this.isMine){
			gameOver=true;
		}
	}

	public boolean getGameOver(){
		return gameOver;
	}

	public void setNearbyMines(byte nearbyMines){
		this.nearbyMines = nearbyMines;
		logoHide=(""+nearbyMines);
	}

	public boolean getIsFlagged(){
		return isFlagged;
	}

	public boolean getIsMine(){
		return isMine;
	}

	public boolean getIsVisible(){
		return isVisible;
	}

	public byte getNearbyMines(){
		return nearbyMines;
	}

	public String toString() {
		return logoShow;
	}
}
