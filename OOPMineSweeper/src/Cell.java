public class Cell {
	int nearbyMines; // maximum 8
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
		setIsVisible(isFlagged);
		logoHide="f";
		logoShow=logoHide;
	}

	public void setIsMine(boolean isMine){
		this.isMine = isMine;
		if(this.isMine)logoHide="x";
	}

	public void setIsVisible(boolean isVisible){
		this.isVisible = isVisible;
		logoShow=logoHide;
		if(this.isMine&&this.isFlagged==false){
			gameOver=true;
		}
	}

	public boolean getGameOver(){
		return gameOver;
	}

	public void setNearbyMines(int nearbyMines){
		this.nearbyMines = nearbyMines;
		if(isMine==false)
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

	public int getNearbyMines(){
		return nearbyMines;
	}

	public String toString() {
		return logoShow;
	}
}
