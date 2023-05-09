package com.github.lilo4591.integration;

import java.util.List;

public class GameOfLifeRequest {
    private long id;
    List<int[]> coordinates;

    private int height;
    private int width;

    public GameOfLifeRequest() {
    }

    public GameOfLifeRequest(long id, List<int[]> coordinates, int height, int width) {
        this.id = id;
        this.coordinates = coordinates;
        this.height = height;
        this.width = width;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
