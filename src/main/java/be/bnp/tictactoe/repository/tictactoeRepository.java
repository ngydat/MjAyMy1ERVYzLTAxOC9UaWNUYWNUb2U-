package be.bnp.tictactoe.repository;

import be.bnp.tictactoe.entity.TicTacToe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tictactoeRepository extends JpaRepository<TicTacToe, Long> {
}
