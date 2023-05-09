package com.github.lilo4591.integration;

//TODO remove, this is now in getrequest
public class GameOfLifeInitBoardRequest {

    private int height;
    private int width;

    public GameOfLifeInitBoardRequest(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public GameOfLifeInitBoardRequest() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
