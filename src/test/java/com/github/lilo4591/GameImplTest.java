package com.github.lilo4591;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class GameImplTest {
    private GameImpl game;

    private static final Logger LOGGER = Logger.getLogger(GameImplTest.class.getName());


    @BeforeEach
    public void setUp() {
        game = new GameImpl();
    }

    /**
     * Any live cell with fewer than two live neighbours dies, as if by underpopulation
     * Example
     * [true, false,false]       [false,false,false]
     * [false,false,false]   --> [false,false,false]
     * [false,false,false]       [false,false,false]
     * ---
     * [false,true, false]       [false,false,false]
     * [false,false,false]   --> [false,false,false]
     * [false,false,false]       [false,false,false]
     * ---
     * [false,false, true]       [false,false,false]
     * [false,false,false]   --> [false,false,false]
     * [false,false,false]       [false,false,false]
     * ---
     * [false,false,false]       [false,false,false]
     * [true, false,false]   --> [false,false,false]
     * [false,false,false]       [false,false,false]
     * ---
     * And so on..
     * */
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void testOneCoordinateAlive(int column) {
        for (int row = 0; row < 3 ; row++) {
            check(row, column);
        }
    }

    private void check(int row, int column) {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        int[] coordinate = {row,column};
        aliveGrids.add(coordinate);

        int rows = 3;
        int columns = 3;
        game.initializeSimulation(rows, columns, aliveGrids);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info(String.format("Test of coordinate (%s , %s)", row, column));
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                assertFalse(gameBoard.getGridValue(r, c));
            }
        }
    }

    /**
     * Any live cell with fewer than two live neighbours dies, as if by underpopulation
     * [true, true, false]       [false,false,false]
     * [false,false,false]   --> [false,false,false]
     * [false,false,false]       [false,false,false]
     */
    @Test
    void testOfGridWithTwoAliveCells() {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        aliveGrids.add(new int[]{0, 0});
        aliveGrids.add(new int[]{0, 1});

        int rows = 3;
        int columns = 3;
        game.initializeSimulation(rows, columns, aliveGrids);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0) and (0, 1)");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                assertFalse(gameBoard.getGridValue(r, c));
            }
        }
    }

    /**
     * Any live cell with fewer than two live neighbours dies, as if by underpopulation
     * [true, false, false]       [false,false,false]
     * [false,true,false]   --> [false,false,false]
     * [false,false,false]       [false,false,false]
     */
    @Test
    void testOfGridWithTwoAliveCellsDiagonally() {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        aliveGrids.add(new int[]{0, 0});
        aliveGrids.add(new int[]{1, 1});

        int rows = 3;
        int columns = 3;
        game.initializeSimulation(rows, columns, aliveGrids);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0) and (1, 1)");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                assertFalse(gameBoard.getGridValue(r, c));
            }
        }
    }

    /**
     * Any live cell with fewer than two live neighbours dies, as if by underpopulation
     * [true, false, false]       [false,false,false]
     * [true, false, false]   --> [false,false,false]
     * [false,false,false]       [false,false,false]
     */
    @Test
    void testOfGridWithTwoAliveCellsVertically() {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        aliveGrids.add(new int[]{0, 0});
        aliveGrids.add(new int[]{1, 0});

        int rows = 3;
        int columns = 3;
        game.initializeSimulation(rows, columns, aliveGrids);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0) and (1, 0)");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                assertFalse(gameBoard.getGridValue(r, c));
            }
        }
    }

    /**
     * Any live cell with fewer than two live neighbours dies, as if by underpopulation
     * [false,false,false]       [false,false,false]
     * [false,true, false]   --> [false,false,false]
     * [false,false, true]       [false,false,false]
     */
    @Test
    void testOfGridWithTwoAliveCellsVerticallyInTheMiddle() {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        aliveGrids.add(new int[]{1, 1});
        aliveGrids.add(new int[]{2, 2});

        int rows = 3;
        int columns = 3;
        game.initializeSimulation(rows, columns, aliveGrids);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (1, 1) and (2, 2)");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                assertFalse(gameBoard.getGridValue(r, c));
            }
        }
    }

    /**
     * Any live cell with fewer than two live neighbours dies, as if by underpopulation
     * [true,false,false]       [false,false,false]
     * [false,true, false]   --> [false,true,false]
     * [false,false, true]       [false,false,false]
     */
    @Test
    void testOfGridWithThreeAliveCellsDiagonallyShouldReturnOnlyMiddleAlive() {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        aliveGrids.add(new int[]{0, 0});
        aliveGrids.add(new int[]{1, 1});
        aliveGrids.add(new int[]{2, 2});

        int rows = 3;
        int columns = 3;
        game.initializeSimulation(rows, columns, aliveGrids);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0), (1,1) and (2, 2)");
        //first row
        assertFalse(gameBoard.getGridValue(0,0));
        assertFalse(gameBoard.getGridValue(0,1));
        assertFalse(gameBoard.getGridValue(0,2));
        //second row
        assertFalse(gameBoard.getGridValue(1,0));
        assertTrue(gameBoard.getGridValue(1,1));
        assertFalse(gameBoard.getGridValue(1,2));
        //last row
        assertFalse(gameBoard.getGridValue(2,0));
        assertFalse(gameBoard.getGridValue(2,1));
        assertFalse(gameBoard.getGridValue(2,2));

    }

    /**
     * Rule 1. Any live cell with fewer than two live neighbours dies, as if by underpopulation
     * Rule 2. Any live cell with two or three live neighbours lives on to the next generation.
     * Rule 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     * [true,false,false]       [true,true,false]
     * [true,true, false]   --> [true,true,false]
     * [false,false, true]      [false,true,false]
     */
    @Test
    void testOfGridWithFourAliveCellsVerticallyShouldReturnFiveAlive() {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        aliveGrids.add(new int[]{0, 0});
        aliveGrids.add(new int[]{1, 0});
        aliveGrids.add(new int[]{1, 1});
        aliveGrids.add(new int[]{2, 2});

        int rows = 3;
        int columns = 3;
        game.initializeSimulation(rows, columns, aliveGrids);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0), (1,0), (1,1) and (2, 2)");
        //first row
        assertTrue(gameBoard.getGridValue(0,0));
        assertTrue(gameBoard.getGridValue(0,1));
        assertFalse(gameBoard.getGridValue(0,2));
        //second row
        assertTrue(gameBoard.getGridValue(1,0));
        assertTrue(gameBoard.getGridValue(1,1));
        assertFalse(gameBoard.getGridValue(1,2));
        //last row
        assertFalse(gameBoard.getGridValue(2,0));
        assertTrue(gameBoard.getGridValue(2,1));
        assertFalse(gameBoard.getGridValue(2,2));

    }

    /**
     * Rule 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
     * [true,false,false]      [true,true,false]
     * [true,true,false]   --> [false,false,true]
     * [true,true, true]       [true,false,true]
     */
    @Test
    void testOfGridWithSixAliveCellsVerticallyShouldReturnFiveAlive() {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        aliveGrids.add(new int[]{0, 0});
        aliveGrids.add(new int[]{1, 0});
        aliveGrids.add(new int[]{1, 1});
        aliveGrids.add(new int[]{2, 0});
        aliveGrids.add(new int[]{2, 1});
        aliveGrids.add(new int[]{2, 2});

        int rows = 3;
        int columns = 3;
        game.initializeSimulation(rows, columns, aliveGrids);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0,0), (1,0), (1,1) (2,0) (2,1) and (2, 2)");
        //first row
        assertTrue(gameBoard.getGridValue(0,0));
        assertTrue(gameBoard.getGridValue(0,1));
        assertFalse(gameBoard.getGridValue(0,2));
        //second row
        assertFalse(gameBoard.getGridValue(1,0));
        assertFalse(gameBoard.getGridValue(1,1));
        assertTrue(gameBoard.getGridValue(1,2));
        //last row
        assertTrue(gameBoard.getGridValue(2,0));
        assertFalse(gameBoard.getGridValue(2,1));
        assertTrue(gameBoard.getGridValue(2,2));
    }


    /**
     *
     * [true,false,false]
     * [true,true,false]   --> (1,1) = 5 etc..
     * [true,true, true]
     */
    @Test
    void testCountLiveNeighbours() {
        ArrayList<int[]> aliveGrids = new ArrayList<>();
        aliveGrids.add(new int[]{0, 0});
        aliveGrids.add(new int[]{1, 0});
        aliveGrids.add(new int[]{1, 1});
        aliveGrids.add(new int[]{2, 0});
        aliveGrids.add(new int[]{2, 1});
        aliveGrids.add(new int[]{2, 2});

        game.initializeSimulation(3, 3, aliveGrids);

        int twoNeighbours = game.countLiveNeighbours(0,0);
        assertEquals(2, twoNeighbours);
        int threeNeighbours = game.countLiveNeighbours(0,1);
        assertEquals(3, threeNeighbours);
        int oneNeighbours = game.countLiveNeighbours(0,2);
        assertEquals(1, oneNeighbours);

        int fourNeighbours = game.countLiveNeighbours(1,0);
        assertEquals(4, fourNeighbours);
        int fiveNeighbours = game.countLiveNeighbours(1,1);
        assertEquals(5, fiveNeighbours);
        int threeNeighboursRowOne = game.countLiveNeighbours(1,2);
        assertEquals(3, threeNeighboursRowOne);

        int threeNeighboursRowTwo = game.countLiveNeighbours(2,0);
        assertEquals(3, threeNeighboursRowTwo);
        int fourNeighboursRowTwo = game.countLiveNeighbours(2,1);
        assertEquals(4, fourNeighboursRowTwo);
        int twoNeighboursRowTwo = game.countLiveNeighbours(2,2);
        assertEquals(2, twoNeighboursRowTwo);

    }




}
