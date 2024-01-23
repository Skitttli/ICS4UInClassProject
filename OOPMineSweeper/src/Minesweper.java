import java.util.Random;
import java.util.Scanner;

public class Minesweper {
    private static final int SIZE = 9;
    private static final int MINES = 10;
    private static final char HIDDEN_CELL = '-';
    private static final char MINE = '*';

    private char[][] board;
    private boolean[][] revealed;
    private boolean gameOver;

    public Minesweper() {
        board = new char[SIZE][SIZE];
        revealed = new boolean[SIZE][SIZE];
        gameOver = false;
        initializeBoard();
        placeMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = HIDDEN_CELL;
                revealed[i][j] = false;
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < MINES) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if (board[row][col] != MINE) {
                board[row][col] = MINE;
                minesPlaced++;
            }
        }
    }

    private void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                char cell = revealed[i][j] ? getCellContent(i, j) : HIDDEN_CELL;
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
    private char getCellContent(int row, int col) {
        if (board[row][col] == MINE) {
            return MINE;
        } else {
            int count = countAdjacentMines(row, col);
            return count == 0 ? ' ' : (char) (count + '0');
        }
    }
    
    
    
    private int countAdjacentMines(int row, int col) {
        int count = 0;
    
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < SIZE && j >= 0 && j < SIZE && board[i][j] == MINE) {
                    count++;
                }
            }
        }
    
        return count;
    }
    

    private void revealCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || revealed[row][col]) {
            return;
        }

        revealed[row][col] = true;

        if (board[row][col] == MINE) {
            gameOver = true;
        } else if (board[row][col] == '0') {
            // If the cell has no adjacent mines, recursively reveal adjacent cells
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    revealCell(i, j);
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!revealed[i][j] && board[i][j] != MINE) {
                    return false;
                }
            }
        }
        return true;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printBoard();

            System.out.print("Enter row and column (e.g., 0 1): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            if (!revealed[row][col]) {
                revealCell(row, col);

                if (gameOver) {
                    System.out.println("Game Over! You hit a mine.");
                } else if (checkWin()) {
                    printBoard();
                    System.out.println("Congratulations! You won!");
                }
            } else {
                System.out.println("Cell already revealed. Please try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Minesweper minesweeper = new Minesweper();
        minesweeper.play();
    }
}

