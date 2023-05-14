package com.github.lilo4591;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameImplTest {
    private GameImpl game;

    private static final int ROWS = 3;
    private static final int COLUMNS = 3;
    private static final Logger LOGGER = Logger.getLogger(GameImplTest.class.getName());


    @BeforeEach
    public void setUp() {
        GameBoard gameBoard = new GameBoard(3, 3);
        game = new GameImpl(gameBoard);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(0, 1),
                Arguments.of(0, 2),
                Arguments.of(1, 0),
                Arguments.of(1, 1),
                Arguments.of(1, 2),
                Arguments.of(2, 0),
                Arguments.of(2, 1),
                Arguments.of(2, 2)
        );
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
    @MethodSource("provideParameters")
    void testOneCoordinateAlive(int row, int column) {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        int[] coordinate = {row,column};
        aliveCells.add(coordinate);

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info(String.format("Test of coordinate (%s , %s)", row, column));
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                assertFalse(gameBoard.getCellValue(r, c));
            }
        }
    }

    @Test
    void testAliveCellsOutsideBoard() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{0, 9});
        aliveCells.add(new int[]{8, 1});

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 9) and (8, 1)");
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                assertFalse(gameBoard.getCellValue(r, c));
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
    void testOfGameWithTwoAliveCells() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{0, 0});
        aliveCells.add(new int[]{0, 1});

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0) and (0, 1)");
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                assertFalse(gameBoard.getCellValue(r, c));
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
    void testOfGameWithTwoAliveCellsDiagonally() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{0, 0});
        aliveCells.add(new int[]{1, 1});

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0) and (1, 1)");
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                assertFalse(gameBoard.getCellValue(r, c));
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
    void testOfGameWithTwoAliveCellsVertically() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{0, 0});
        aliveCells.add(new int[]{1, 0});

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0) and (1, 0)");
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                assertFalse(gameBoard.getCellValue(r, c));
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
    void testOfGameWithTwoAliveCellsVerticallyInTheMiddle() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{1, 1});
        aliveCells.add(new int[]{2, 2});

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (1, 1) and (2, 2)");
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                assertFalse(gameBoard.getCellValue(r, c));
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
    void testOfGameWithThreeAliveCellsDiagonallyShouldReturnOnlyMiddleAlive() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{0, 0});
        aliveCells.add(new int[]{1, 1});
        aliveCells.add(new int[]{2, 2});

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0), (1,1) and (2, 2)");
        //first row
        assertFalse(gameBoard.getCellValue(0,0));
        assertFalse(gameBoard.getCellValue(0,1));
        assertFalse(gameBoard.getCellValue(0,2));
        //second row
        assertFalse(gameBoard.getCellValue(1,0));
        assertTrue(gameBoard.getCellValue(1,1));
        assertFalse(gameBoard.getCellValue(1,2));
        //last row
        assertFalse(gameBoard.getCellValue(2,0));
        assertFalse(gameBoard.getCellValue(2,1));
        assertFalse(gameBoard.getCellValue(2,2));

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
    void testOfGameWithFourAliveCellsVerticallyShouldReturnFiveAlive() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{0, 0});
        aliveCells.add(new int[]{1, 0});
        aliveCells.add(new int[]{1, 1});
        aliveCells.add(new int[]{2, 2});

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0, 0), (1,0), (1,1) and (2, 2)");
        //first row
        assertTrue(gameBoard.getCellValue(0,0));
        assertTrue(gameBoard.getCellValue(0,1));
        assertFalse(gameBoard.getCellValue(0,2));
        //second row
        assertTrue(gameBoard.getCellValue(1,0));
        assertTrue(gameBoard.getCellValue(1,1));
        assertFalse(gameBoard.getCellValue(1,2));
        //last row
        assertFalse(gameBoard.getCellValue(2,0));
        assertTrue(gameBoard.getCellValue(2,1));
        assertFalse(gameBoard.getCellValue(2,2));

    }

    /**
     * Rule 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
     * [true,false,false]      [true,true,false]
     * [true,true,false]   --> [false,false,true]
     * [true,true, true]       [true,false,true]
     */
    @Test
    void testOfGameWithSixAliveCellsVerticallyShouldReturnFiveAlive() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{0, 0});
        aliveCells.add(new int[]{1, 0});
        aliveCells.add(new int[]{1, 1});
        aliveCells.add(new int[]{2, 0});
        aliveCells.add(new int[]{2, 1});
        aliveCells.add(new int[]{2, 2});

        game.initializeActiveCellsOnBoard(aliveCells);
        game.update();
        GameBoard gameBoard = game.getGameBoard();
        LOGGER.info("Test of alive cell in coordinates (0,0), (1,0), (1,1) (2,0) (2,1) and (2, 2)");
        //first row
        assertTrue(gameBoard.getCellValue(0,0));
        assertTrue(gameBoard.getCellValue(0,1));
        assertFalse(gameBoard.getCellValue(0,2));
        //second row
        assertFalse(gameBoard.getCellValue(1,0));
        assertFalse(gameBoard.getCellValue(1,1));
        assertTrue(gameBoard.getCellValue(1,2));
        //last row
        assertTrue(gameBoard.getCellValue(2,0));
        assertFalse(gameBoard.getCellValue(2,1));
        assertTrue(gameBoard.getCellValue(2,2));
    }


    /**
     *
     * [true,false,false]
     * [true,true,false]   --> (1,1) = 5 etc..
     * [true,true, true]
     */
    @Test
    void testCountLiveNeighbours() {
        ArrayList<int[]> aliveCells = new ArrayList<>();
        aliveCells.add(new int[]{0, 0});
        aliveCells.add(new int[]{1, 0});
        aliveCells.add(new int[]{1, 1});
        aliveCells.add(new int[]{2, 0});
        aliveCells.add(new int[]{2, 1});
        aliveCells.add(new int[]{2, 2});

        game.initializeActiveCellsOnBoard(aliveCells);

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
