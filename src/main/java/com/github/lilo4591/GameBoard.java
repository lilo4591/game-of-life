package com.github.lilo4591;

import java.util.Arrays;
import java.util.logging.*;

public class GameBoard {

    private static final Logger LOGGER = Logger.getLogger(GameBoard.class.getName());
    private final boolean[][] board;

    private final int rows;
    private final int cols;
    public GameBoard(int rows, int cols) {
        this.board = new boolean[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    boolean getGridValue(int row, int column) {
        return board[row][column];
    }

    //TODO rename better
    void setGridValue(int row, int column, boolean gridVal) {
        board[row][column] = gridVal;
    }
    public void printBoard() {
        Arrays.stream(board)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    /*
    *                           0   1   2
    * [(0,0),(0,1),(0,2)]   0   A   B   C
    * [(1,0),(1,1),(1,2)]   1   D   E   F
    * [(2,0),(2,1),(2,2)]   2   G   H   I
    *
    * */
}
