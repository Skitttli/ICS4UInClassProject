public class Difficulty {
	private int width, height, numMines;

	public Difficulty (String difficulty) {
		int[] tempValues = diffNums(difficulty);
		setHeight(tempValues[0]);
		setWidth(tempValues[1]);
		setNumMines(tempValues[2]);
	}

	public int getHeight() {
		return height;
	}

	public int getNumMines() {
		return numMines;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public static int[] diffNums(String difficulty){
		//First Value Height, Second Width, Third Num of Mines
		switch (difficulty) {
			case "Easy":
			case "easy":
			case "E":
			case "e":
				return new int[] {10,8,10};
			case "Hard":
			case "hard":
			case "H":
			case "h":
				return new int[] {24,20,99};
			case "Expert":
			case "expert":
			case "Ex":
			case "ex":
				return new int[] {30,24,120};
			case "Medium":
			case "medium":
			case "Med":
			case "med":
			case "M":
			case "m":
			default:
				return new int[] {18,14,40};
		}
	}
}
