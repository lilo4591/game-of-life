package com.github.lilo4591.integration.mapper;

import com.github.lilo4591.GameImpl;
import com.github.lilo4591.integration.GameOfLifeInitBoardRequest;
import com.github.lilo4591.integration.GameOfLifeInitBoardResponse;
import com.github.lilo4591.integration.GameOfLifeRequest;
import com.github.lilo4591.integration.GameOfLifeResponse;

import java.util.List;

public class Mapper {

    GameImpl game = new GameImpl();
    private static int ROWS = 40;
    private static int COLUMNS= 40;

    public GameOfLifeResponse handleRequest(GameOfLifeRequest gameOfLifeRequest) {
        List<int[]> coordinates = gameOfLifeRequest.getCoordinates();
        game.initializeSimulation(ROWS, COLUMNS, coordinates);
        List<int[]> updatedCoordinates = game.update();
        return mapResponse(updatedCoordinates);
    }

    private GameOfLifeResponse mapResponse(List<int[]> updatedCoordinates) {
        return new GameOfLifeResponse(updatedCoordinates);
    }

    public  GameOfLifeInitBoardResponse mapInitBoardResponse() {
        return new GameOfLifeInitBoardResponse(ROWS, COLUMNS);
    }


}
