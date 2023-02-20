package be.bnp.tictactoe.service;

import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;

public interface GameService {

    public Game findByStatus(Status status);
}
