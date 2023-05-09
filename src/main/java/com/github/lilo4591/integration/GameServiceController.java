package com.github.lilo4591.integration;

import com.github.lilo4591.integration.mapper.Mapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class GameServiceController {

    Mapper mapper = new Mapper();

    @GetMapping("/game")
    public ResponseEntity<GameOfLifeInitBoardResponse> gameInit() {
        return ResponseEntity.ok(mapper.mapInitBoardResponse());
    }


    @PostMapping(
            value = "/game", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameOfLifeResponse> gameTick(@RequestBody GameOfLifeRequest request) {
        System.out.println(request.getId());
        System.out.println(request.getCoordinates().toString());
        System.out.println(request.getHeight());
        System.out.println(request.getWidth());
        return ResponseEntity.ok(mapper.handleRequest(request));
    }




}
