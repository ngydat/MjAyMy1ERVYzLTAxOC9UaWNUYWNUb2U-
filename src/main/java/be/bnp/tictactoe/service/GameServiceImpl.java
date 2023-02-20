package be.bnp.tictactoe.service;

import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;
import be.bnp.tictactoe.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;
    @Override
    public Game findByStatus(Status status) {
        return gameRepository.findByStatus(status).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }
}
