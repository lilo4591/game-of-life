package com.github.lilo4591.integration;

public class GameOfLifeInitBoardResponse {

    private int rows;
    private int columns;

    public GameOfLifeInitBoardResponse() {
    }

    public GameOfLifeInitBoardResponse(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}
