package com.github.lilo4591.integration;

import java.util.List;

public class GameOfLifeRequest {
    private List<int[]> coordinates;

    public GameOfLifeRequest() {
    }

    public GameOfLifeRequest(List<int[]> coordinates) {
        this.coordinates = coordinates;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }
}
