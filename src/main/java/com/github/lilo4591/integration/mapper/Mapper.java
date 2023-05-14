package com.github.lilo4591.integration.mapper;

import com.github.lilo4591.GameBoard;
import com.github.lilo4591.GameImpl;
import com.github.lilo4591.integration.GameOfLifeInitBoardResponse;
import com.github.lilo4591.integration.GameOfLifeRequest;
import com.github.lilo4591.integration.GameOfLifeResponse;

import java.util.List;

public class Mapper {

    private static final int ROWS = 20;
    private static final int COLUMNS = 40;

    public GameOfLifeResponse handlePostRequest(GameOfLifeRequest gameOfLifeRequest) {
        List<int[]> coordinates = gameOfLifeRequest.getCoordinates();
        GameBoard gameBoard = new GameBoard(ROWS, COLUMNS);
        GameImpl game = new GameImpl(gameBoard);
        game.initializeActiveCellsOnBoard(coordinates);
        List<int[]> updatedCoordinates = game.update();
        return mapResponse(updatedCoordinates);
    }

    private GameOfLifeResponse mapResponse(List<int[]> updatedCoordinates) {
        return new GameOfLifeResponse(updatedCoordinates);
    }

    public  GameOfLifeInitBoardResponse createGetResponse() {
        return new GameOfLifeInitBoardResponse(ROWS, COLUMNS);
    }


}
