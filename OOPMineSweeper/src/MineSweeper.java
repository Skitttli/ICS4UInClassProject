import java.util.Scanner;

public class MineSweeper {
    private Grid playingGrid;
    private Difficulty difficulty;
    private long startTime;
    public static boolean gameOver,happyPath,firstTurn;

    public MineSweeper(String diff) {
        difficulty = new Difficulty(diff);
			firstTurn=true;
        playingGrid = new Grid(difficulty.getHeight(), difficulty.getWidth());
        playingGrid.setNumMines(difficulty.getNumMines());
        playingGrid.placeMines();
        
        startTime = java.lang.System.currentTimeMillis();
    }

    public static void main(String[] args) throws Exception {
        clearScreen();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a difficulty:");
        MineSweeper game = new MineSweeper(scan.nextLine());
        clearScreen();
        System.out.println(game.playingGrid);

        while(!gameOver)
        playTurn(scan,game);
        
        if(happyPath){
            System.out.println("Congratulations! You won the game!");
        }else{
            System.out.println("You Clicked a Mine! \nGame Over!");
        }

        System.out.println("Time Played: "+((java.lang.System.currentTimeMillis() - game.startTime) / 1000)+" seconds");
        scan.close();
    }

    public static void playTurn(Scanner scan, MineSweeper game){

        int f;
        do {
					System.out.println("Choose a tile:");
					String chosenTile = scan.nextLine();
					int chosenHeight = Integer.parseInt(chosenTile.replaceAll("\\D",""))-1;
					int chosenWidth = chosenTile.replaceAll("\\d","").charAt(0)-'a';
					if(chosenHeight>game.playingGrid.getHeight()||chosenHeight<=0||chosenWidth>game.playingGrid.getWidth()||chosenWidth<=0){
						System.out.println("That tile doesn't exist! Try Picking a new one");
						f=1;
						continue;
					}
            System.out.println("Click or Flag?");
            f=0;
            switch(scan.nextLine()){

            case "Click":
            case "click":
            case "c":
            case "C":
            game.playingGrid.getPlayingCells()[chosenHeight][chosenWidth].setIsVisible(true);
            if(game.playingGrid.getGameOver(chosenHeight,chosenWidth)){
                gameOver=true;
            }
						if(game.playingGrid.getPlayingCells()[chosenHeight][chosenWidth].getNearbyMines()==0||firstTurn)
            checkAdjacent(game,chosenHeight,chosenWidth);
            
            break;

            case "Flag":
            case "flag":
            case "F":
            case "f":
            if(game.playingGrid.getNumFlagsRemaining()==0){
                System.out.println("You have no flags left! Please remove a flag or click a tile!");
            }else{
							if(game.playingGrid.getPlayingCells()[chosenHeight][chosenWidth].getIsFlagged()){
								game.playingGrid.getPlayingCells()[chosenHeight][chosenWidth].setIsFlagged(false);
                game.playingGrid.setNumFlagsRemaining(game.playingGrid.getNumFlagsRemaining()+1);
							}else{
                game.playingGrid.getPlayingCells()[chosenHeight][chosenWidth].setIsFlagged(true);
                game.playingGrid.setNumFlagsRemaining(game.playingGrid.getNumFlagsRemaining()-1);
							}
            }
            break;

						case "Back":
						case "back":
						case "B":
						case "b":
								clearScreen();
								System.out.println(game.playingGrid);
								System.out.println("Try typing correctly this time");
								f=1;
						break;

            default:
            System.out.println("That's not an option! Try Again");
            f=1;
        }
        }while(f!=0);
				
        clearScreen();
        System.out.println(game.playingGrid);
        System.out.println("Flags Remaining: "+game.playingGrid.getNumFlagsRemaining());

				if (game.playingGrid.getNumFlagsRemaining()==0) {
					String [] bombArray=game.playingGrid.getBombArray();
					for(int i=0;i<bombArray.length;i++){
						String checkingTile = bombArray[i];
						int checkingHeight = Integer.parseInt(checkingTile.replaceAll("\\D",""))-1;
						int checkingWidth = checkingTile.replaceAll("\\d","").charAt(0)-'a';
						if(game.playingGrid.getPlayingCells()[checkingHeight][checkingWidth].getIsFlagged()==false){
							break;
						}
					}		
					happyPath=true;		
					gameOver=true;
				}
				firstTurn=false;
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    public static void checkAdjacent(MineSweeper game,int heightLoc,int widthLoc){
        for(int height=-1;height<=1;height++){
            for(int width=-1;width<=1;width++){
                int curH = heightLoc+height,curW=widthLoc+width;
                if(height==0&&width==0){
                    continue;
                } else if(curH>=game.playingGrid.getHeight()||curH<0){
                    continue;
                } else if(curW>=game.playingGrid.getWidth()||curW<0){
                    continue;
                }
                Cell currentCell = game.playingGrid.getPlayingCells()[curH][curW];
                if(!currentCell.getIsVisible()&&!currentCell.getIsMine()){
                    currentCell.setIsVisible(true);
                    if(currentCell.getNearbyMines()==0){
                        checkAdjacent(game, curH, curW);
                    }
                }
            }
        }
    }

    
}
