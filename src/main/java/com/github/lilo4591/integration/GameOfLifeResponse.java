package com.github.lilo4591.integration;

import java.util.List;

public class GameOfLifeResponse {

    List<int[]> coordinates;

    public GameOfLifeResponse(List<int[]> coordinates) {
        this.coordinates = coordinates;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }
}
