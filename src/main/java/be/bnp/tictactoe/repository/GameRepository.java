package be.bnp.tictactoe.repository;

import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository()
public interface GameRepository extends CrudRepository<Game, Long> {

    Optional<Game> findByStatus(Status status);
}
