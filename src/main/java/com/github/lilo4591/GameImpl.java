package com.github.lilo4591;


import java.util.ArrayList;
import java.util.List;

public class GameImpl {

    private GameBoard gameBoard;
    boolean active;

    public GameImpl() {
    }

    public void initializeSimulation(int rows, int columns, List<int[]> aliveGrids) {
        gameBoard = new GameBoard(rows, columns);
        aliveGrids.forEach(cord -> gameBoard.setGridValue(cord[0], cord[1], true));
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setActive() {
        this.active = true;
    }

    public boolean isActive() {
        return this.active;
    }



    private boolean checkGridIsInsideBoard(int numOfRows, int numOfCols, int currentRow, int currentCol) {
        return currentRow >= 0 && currentRow < numOfRows && currentCol >= 0 && currentCol < numOfCols; //TODO readablity and tests
    }

    /*
     *                           0   1   2
     * [(0,0),(0,1),(0,2)]   0   A   B   C
     * [(1,0),(1,1),(1,2)]   1   D   E   F
     * [(2,0),(2,1),(2,2)]   2   G   H   I
     *
     * */

    private int countLiveNeighbours(int row, int column) {
        int count = 0;
        int numOfRows = gameBoard.getRows();
        int numOfCols = gameBoard.getCols();
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c =  column - 1; c <= column + 1; c++) {
                if (isNotCurrentGrid(row, column, r, c) && checkGridIsInsideBoard(numOfRows, numOfCols, r, c)
                        && gameBoard.getGridValue(r, c)) {
                        count++;
                }
            }
        }
        return count;
    }

    public List<int[]>  update() {
        int rows = gameBoard.getRows();
        int columns = gameBoard.getCols();
        List<int[]> aliveCoordinates = new ArrayList();

        GameBoard updatedGameBoard = new GameBoard(rows, columns);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int count = countLiveNeighbours(r, c);
                if (gameBoard.getGridValue(r, c)) {
                    // Rule 1, 2 and 3
                    boolean isAlive = count >= 2 && count <= 3;
                    updatedGameBoard.setGridValue(r, c, isAlive);
                    if (isAlive) {
                        int[] tuple = {r, c};
                        aliveCoordinates.add(tuple);
                    }
                } else {
                    // Rule 4
                    boolean isAlive = count == 3;
                    updatedGameBoard.setGridValue(r, c, isAlive);
                    if (isAlive) {
                        int[] tuple = {r, c};
                        aliveCoordinates.add(tuple);
                    }
                }
            }
        }
        gameBoard = updatedGameBoard;
        return aliveCoordinates;
    }

    private static boolean isNotCurrentGrid(int row, int column, int r, int c) {
        return !(r == row && c == column);
    }
}
