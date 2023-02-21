package be.bnp.tictactoe.controller;

import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;
import be.bnp.tictactoe.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GameController {


    private final GameService gameService;

    @GetMapping("/game/{status}")
    public ResponseEntity<Game> findGameByStatus(@PathVariable Status status){
        return ResponseEntity.ok(gameService.findByStatus(status));
    }

    @GetMapping("/game")
    public ResponseEntity<Game> findGameById(@RequestParam Long id) {

        return ResponseEntity.ok(gameService.findById(id));
    }

    @PostMapping("/game/{gameId}/{row}/{column}")
    public ResponseEntity<Game> saveGame(@PathVariable Long gameId, @PathVariable Integer row, @PathVariable Integer column){
        Game foundGame = gameService.findById(gameId);
        foundGame.makeMove(row,column);

        return ResponseEntity.ok(gameService.saveGame(foundGame));
    }

}
