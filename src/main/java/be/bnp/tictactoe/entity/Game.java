package be.bnp.tictactoe.entity;

import be.bnp.tictactoe.enums.Player;
import be.bnp.tictactoe.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "games")
@Getter
@Setter
public class Game {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_seq")
    @SequenceGenerator(name="game_id_seq", sequenceName = "game_id_seq", allocationSize = 1)
    @Column(name = "game_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Column(name = "nb_moves")
    private int nbMoves;

    @Column
    @Enumerated(EnumType.STRING)
    private Player winner;

    @Column(name = "current_player")
    @Enumerated(EnumType.STRING)
    private Player currentPlayer;

    @Transient
    private Player[][] board;

    public Game(){
        this.status = Status.WAITING;
        this.currentPlayer = Player.X;
        board = new Player[3][3];
        for (Player[] row: board){
            Arrays.fill(row, Player.EMPTY);
        }
    }

    public Game(Long id){
        this();
        this.id = id;
    }

    public void makeMove(int indexRow, int indexCol){
        if (nbMoves == 0) this.status = Status.ACTIVE;
        if (this.board[indexRow][indexCol] != Player.EMPTY){
            throw new IllegalArgumentException("This space has already been played. Please put your X or O elsewhere");
        }
        this.board[indexRow][indexCol] = this.currentPlayer;
        nbMoves++;
        if (isWinner(indexRow, indexCol)){
            victory();
        } else {
            rotate();
        }

    }

    private void rotate() {
        this.currentPlayer = this.currentPlayer == Player.X ? Player.O: Player.X;
    }

    /**
     * From the latest move, check if it provides the win
     *
     * @param indexRow the position on the row
     * @param indexCol the position on the column
     * @return true if the latest move provided the win,
     * false otherwise
     */

    private boolean isWinner(int indexRow, int indexCol){

        //check diagonale
        if (indexRow == indexCol){
            return sameInDiagonale(indexRow, indexCol);
        }

        /*
         * if the last move was done
         * in the middle of a row or
         * in the middle of a column (center excluded)
         */
        if (indexRow == 1 || indexCol == 1){
            return sameInARow(indexRow, indexCol) || sameInACol(indexRow, indexCol);
        }

        //other positions
        return sameInARow(indexRow, indexCol) ||
                sameInACol(indexRow, indexCol) ||
                sameInDiagonale(indexRow, indexCol);
    }

    private boolean sameInDiagonale(int indexRow, int indexCol){
        Player[] descendingDiagonale = new Player[3];
        for (int i = 0; i < this.board.length; i++){
            descendingDiagonale[i] = this.board[i][i];
        }
        boolean sameInDescDiagonale = Arrays
                .stream(descendingDiagonale).allMatch(s -> s.equals(this.board[indexRow][indexCol]));

        Player[] ascendingDiagonale = new Player[3];
        ascendingDiagonale[0] = this.board[2][0];
        ascendingDiagonale[1] = this.board[1][1];
        ascendingDiagonale[2] = this.board[0][2];
        boolean sameInAscDiagonale = Arrays
                .stream(ascendingDiagonale).allMatch(s -> s.equals(this.board[indexRow][indexCol]));

        return sameInAscDiagonale || sameInDescDiagonale;
    }

    private boolean sameInARow(int indexRow, int indexCol){
        Player[] row = this.board[indexRow];
        return Arrays.stream(row).allMatch(s -> s.equals(row[indexCol]));
    }

    private boolean sameInACol(int indexRow, int indexCol){
        Player[] column = Arrays.stream(this.board).map(o -> o[indexCol]).toArray(Player[]::new);
        return Arrays.stream(column).allMatch(s -> s.equals(column[indexRow]));
    }

    public void victory(){
        this.status = Status.FINISHED;
        this.winner = this.currentPlayer;
    }



}
