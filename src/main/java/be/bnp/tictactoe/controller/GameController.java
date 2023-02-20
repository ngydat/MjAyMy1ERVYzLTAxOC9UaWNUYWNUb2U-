package be.bnp.tictactoe.controller;

import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;
import be.bnp.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/game/{status}")
    public Game findGameByStatus(@PathVariable Status status){
        return gameService.findByStatus(status);
    }

    @GetMapping("/game/{id}")
    public Game findGameById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @PostMapping("/game")
    public Game saveGame(@RequestBody Game game){
        return gameService.saveGame(game);
    }

}
