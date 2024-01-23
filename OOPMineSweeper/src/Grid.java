import java.util.Arrays;

public class Grid {
	private int numMines, numFlagsRemaining, numUserFlags;
	private Cell[][] playingCells;

	public Grid (int height, int width) {
		playingCells = new Cell[height][width];
		for (int i = 0; i < playingCells.length; i++) {
			for (int k = 0; k < playingCells[i].length; k++) {
				playingCells[i][k] = new Cell();
			}
		}
		// populate with cells
	}

	public int getHeight(){
		return playingCells.length;
	}
	public int getWidth(){
		return playingCells[0].length;
	}

	public int getNumFlagsRemaining() {
		return numFlagsRemaining;
	}

	public int getNumMines() {
		return numMines;
	}

	public int getNumUserFlags() {
		return numUserFlags;
	}

	public Cell[][] getPlayingCells() {
		return playingCells;
	}
	
	public void setNumFlagsRemaining(int numFlagsRemaining) {
		this.numFlagsRemaining = numFlagsRemaining;
	}
	
	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}

	public void setNumUserFlags(int numUserFlags) {
		this.numUserFlags = numUserFlags;
	}

	public void setPlayingCells(Cell[][] playingCells) {
		this.playingCells = playingCells;
	}
	public boolean getGameOver(int height, int width){
		return playingCells[height][width].getGameOver();
	}

	public void placeMines(){
		for(int i=getNumMines();i>0;i--){
			int heightLoc = (int)(Math.random() * (playingCells.length));
			int widthLoc = (int)(Math.random() * (playingCells[0].length));
			if(playingCells[heightLoc][widthLoc].isMine){
				i++;
			}else{
				playingCells[heightLoc][widthLoc].setIsMine(true);
				for(int height=-1;height<=1;height++){
					for(int width=-1;width<=1;width++){
						int curH = heightLoc+height,curW=widthLoc+width;
						if(height==0&&width==0){
							continue;
						} else if(curH>=playingCells.length||curH<0){
							continue;
						} else if(curW>=playingCells[0].length||curW<0){
							continue;
						}else if(playingCells[curH][curW].getIsMine()){
							continue;
						}else{
							playingCells[curH][curW].setNearbyMines((byte)(playingCells[curH][curW].getNearbyMines()+1));
						}


					}
				}
			}
		}
		setNumFlagsRemaining(getNumMines());
	}

	public String toString() {
		int row =1;
		String builder = "";
		System.out.print("    ");
		for(char i='a';i<=playingCells[0].length+96;i++){
			System.out.print("["+i+"]");
		}
		System.out.println();
		for (Cell[] c : playingCells) {
			if(row>9)
			builder +="["+row+"]"+ Arrays.toString(c).replaceAll("\\[|\\s|,|]"," ") + "\n";
			else
			builder +="["+row+"] "+ Arrays.toString(c).replaceAll("\\[|\\s|,|]"," ") + "\n";
			row++;
		}
		return builder;
	}
}
