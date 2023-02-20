package be.bnp.tictactoe.service;

import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;

public interface GameService {

    Game findByStatus(Status status);

    Game findById(Long id);

    Game saveGame(Game game);
}
