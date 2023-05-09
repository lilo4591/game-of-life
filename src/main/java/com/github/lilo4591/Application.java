package com.github.lilo4591;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("Welcome to game of life!");
        ArrayList<int[]> cordinates = new ArrayList<>();
        int[] tuple = {1,1};
        //int[] tuple2 = {5,9};
        cordinates.add(tuple);
        //cordinates.add(tuple2);
        GameImpl game = new GameImpl();
        game.initializeSimulation(3, 3, cordinates);
        //game.setActive();
        while (game.isActive()) {
            game.getGameBoard().printBoard();
            game.update();
            System.out.println("____");
        }

        SpringApplication.run(Application.class, args);
    }

}