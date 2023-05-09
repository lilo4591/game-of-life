package com.github.lilo4591;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        System.out.println("Welcome to game of life!");
        ArrayList<int[]> cordinates = new ArrayList<>();
        int[] tuple = {1,1};
        //int[] tuple2 = {5,9};
        cordinates.add(tuple);
        //cordinates.add(tuple2);
        GameImpl game = new GameImpl();
        game.initializeSimulation(3, 3, cordinates);
        game.setActive();
        while (game.isActive()) {
            game.getGameBoard().printBoard();
            game.update();
            System.out.println("____");
        }


    }
}