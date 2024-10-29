package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

class BoardTest {

    /* PRUEBAS UNITARIAS
        Robust Weak Equivalence Class Testing: controlar entradas válidas y no válidas (null)
            - Player
            - Aliens
            - Dimension
            - Shot 
            - Timer
        Normal Strong Equivalence Class Testing: controlar únicamente entradas válidas
            - Direction
            - Deaths
            - InGame
            - ExplImg
            - Message
    */
    @Test
    @DisplayName("Debería obtener y establecer el jugador correctamente")
    void shouldGetandSetPlayer() {
        Board board = new Board();
        Player player = new Player();
        board.setPlayer(player);
        assertEquals(player, board.getPlayer());
    }

    @Test
    @DisplayName("Debería manejar correctamente un jugador nulo")
    void shouldHandleNullPlayer() {
        Board board = new Board();
        board.setPlayer(null);
        assertNull(board.getPlayer());
    }

    @Test
    @DisplayName("Debería obtener y establecer los aliens correctamente")
    void shouldGetandSetAliens() {
        Board board = new Board();
        List<Alien> aliens = new ArrayList<>();
        board.setAliens(aliens);
        assertEquals(aliens, board.getAliens());
    }

    @Test
    @DisplayName("Debería manejar correctamente una lista de aliens nula")
    void shouldHandleNullAliens() {
        Board board = new Board();
        board.setAliens(null);
        assertNull(board.getAliens());
    }

    @Test
    @DisplayName("Debería obtener y establecer la dimensión correctamente")
    void shouldGetAndSetDimension() {
        Board board = new Board();
        Dimension dimension = new Dimension(100, 100);
        board.setD(dimension);
        assertEquals(dimension, board.getD());
    }

    @Test
    @DisplayName("Debería manejar correctamente una dimensión nula")
    void shouldHandleNullDimension() {
        Board board = new Board();
        board.setD(null);
        assertNull(board.getD());
    }

    @Test
    @DisplayName("Debería establecer y obtener el disparo correctamente")
    void shouldGetandSetShot() {
        Board board = new Board();
        Shot shot = new Shot();
        board.setShot(shot);
        assertEquals(shot, board.getShot());
    }

    @Test
    @DisplayName("Debería manejar correctamente un disparo nulo")
    void shouldHandleNullShot() {
        Board board = new Board();
        board.setShot(null);
        assertNull(board.getShot());
    }

    @Test
    @DisplayName("Debería obtener y establecer la dirección correctamente")
    void shouldGetAndSetDirection() {
        Board board = new Board();
        board.setDirection(1);
        assertEquals(1, board.getDirection());
    }

    @Test
    @DisplayName("Debería obtener y establecer las muertes correctamente")
    void shouldGetAndSetDeaths() {
        Board board = new Board();
        board.setDeaths(5);
        assertEquals(5, board.getDeaths());
    }

    @Test
    @DisplayName("Debería obtener y establecer el estado del juego correctamente")
    void shouldGetAndSetInGame() {
        Board board = new Board();
        board.setInGame(false);
        assertFalse(board.isInGame());
        board.setInGame(true);
        assertTrue(board.isInGame());
    }

    @Test
    @DisplayName("Debería obtener y establecer la imagen de explosión correctamente")
    void shouldGetAndSetExplImg() {
        Board board = new Board();
        String explImg = "new/path/to/explosion.png";
        board.setExplImg(explImg);
        assertEquals(explImg, board.getExplImg());
    }

    @Test
    @DisplayName("Debería obtener y establecer el mensaje correctamente")
    void shouldGetAndSetMessage() {
        Board board = new Board();
        String message = "New Message";
        board.setMessage(message);
        assertEquals(message, board.getMessage());
    }

    @Test
    @DisplayName("Debería obtener y establecer el temporizador correctamente")
    void shouldGetAndSetTimer() {
        Board board = new Board();
        Timer timer = new Timer(1000, null);
        board.setTimer(timer);
        assertEquals(timer, board.getTimer());
    }

    @Test
    @DisplayName("Debería manejar correctamente un temporizador nulo")
    void shouldHandleNullTimer() {
        Board board = new Board();
        board.setTimer(null);
        assertNull(board.getTimer());
    }
}