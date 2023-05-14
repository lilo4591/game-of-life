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

    boolean getCellValue(int row, int column) {
        return board[row][column];
    }

    void setCellValue(int row, int column, boolean cellValue) {
        board[row][column] = cellValue;
    }

    public void printBoard() {
        Arrays.stream(board)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

}
