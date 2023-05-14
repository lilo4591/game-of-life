package com.github.lilo4591;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GameImpl {

    private GameBoard gameBoard;

    private static final Logger LOGGER = Logger.getLogger(GameImpl.class.getName());

    public GameImpl(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void initializeActiveCellsOnBoard(List<int[]> aliveCells) {
        aliveCells.forEach(cord -> {
            int aliveRow = cord[0];
            int aliveColumn = cord[1];
            if (checkCellIsInsideBoard(gameBoard.getRows(), gameBoard.getCols(), aliveRow, aliveColumn)) {
                gameBoard.setCellValue(aliveRow, aliveColumn, true);
            }
            else {
                LOGGER.severe(String.format("Alive cell (%s,%s) provided is outside board! Skipping this cell..", aliveRow, aliveColumn));
            }
        });
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    private boolean checkIsInside(int current, int num) {
        return current >= 0 && current < num;
    }

    private boolean checkCellIsInsideBoard(int numOfRows, int numOfCols, int currentRow, int currentCol) {
        return checkIsInside(currentRow, numOfRows) && checkIsInside(currentCol, numOfCols);
    }

    /*
     *
     * [(0,0),(0,1),(0,2)]
     * [(1,0),(1,1),(1,2)]
     * [(2,0),(2,1),(2,2)]
     *
     * */

    protected int countLiveNeighbours(int row, int column) {
        int count = 0;
        int numOfRows = gameBoard.getRows();
        int numOfCols = gameBoard.getCols();
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c =  column - 1; c <= column + 1; c++) {
                if (isNotCurrentCell(row, column, r, c) && checkCellIsInsideBoard(numOfRows, numOfCols, r, c)
                        && gameBoard.getCellValue(r, c)) {
                        count++;
                }
            }
        }
        return count;
    }

    private boolean checkRuleFour(int count) {
        return count == 3;
    }

    private boolean checkRuleOneTwoAndThree(int count) {
        return count >= 2 && count <= 3;
    }

    public List<int[]> update() {
        int rows = gameBoard.getRows();
        int columns = gameBoard.getCols();
        List<int[]> aliveCoordinates = new ArrayList<>();

        GameBoard updatedGameBoard = new GameBoard(rows, columns);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int count = countLiveNeighbours(r, c);
                if (gameBoard.getCellValue(r, c)) {
                    boolean isAlive = checkRuleOneTwoAndThree(count);
                    updatedGameBoard.setCellValue(r, c, isAlive);
                    if (isAlive) {
                        int[] cell = {r, c};
                        aliveCoordinates.add(cell);
                    }
                } else {
                    boolean isAlive = checkRuleFour(count);
                    updatedGameBoard.setCellValue(r, c, isAlive);
                    if (isAlive) {
                        int[] cell = {r, c};
                        aliveCoordinates.add(cell);
                    }
                }
            }
        }
        gameBoard = updatedGameBoard;
        return aliveCoordinates;
    }

    private static boolean isNotCurrentCell(int row, int column, int r, int c) {
        return !(r == row && c == column);
    }
}
