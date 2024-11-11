package main;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.Board;
import main.Commons;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

public class BoardTest {

    /* CP1 Normal Strong Equivalence Testing (comprueba entradas válidas)*/
    @Test
    @DisplayName("Debería inicializar la partida correctamente")
    void testGameInit() {
        Board board = new Board();

        // CP1.1 Verificar que los aliens están inicializados correctamente
        assertNotNull(board.getAliens());
        assertEquals(Commons.NUMBER_OF_ALIENS_TO_DESTROY, board.getAliens().size()); // 4 filas x 6 columnas = 24 aliens

        // CP1.2 Verifica el alien de la posicion 0 (primer alien i=0, j=0)
        Alien firstAlien = board.getAliens().get(0);
        assertEquals(Commons.ALIEN_INIT_Y, firstAlien.getX());
        assertEquals(Commons.ALIEN_INIT_Y, firstAlien.getY());

        // CP1.3 Verifica el alien de la posicion 23 (último alien i=3, j=5)
        Alien lastAlien = board.getAliens().get(23);
        assertEquals(Commons.ALIEN_INIT_Y + 18 * 5, lastAlien.getX());
        assertEquals(Commons.ALIEN_INIT_Y + 18 * 3, lastAlien.getY());

        // CP1.4 Verificar que el jugador está inicializado correctamente
        assertNotNull(board.getPlayer());
        assertTrue(board.getPlayer().isVisible());

        // CP1.5 Verificar que el disparo está inicializado correctamente
        assertNotNull(board.getShot());
    }


    @Test
    @DisplayName("Debería finalizar el juego cuando se han destruido todos los alienígenas")
    void testGameWon() {
        Board board = new Board();

        // Simular que todos los alienígenas han sido destruidos
        board.setDeaths(Commons.CHANCE);

        // Actualizar el estado del juego
        board.update();

        // Verificar que el juego ha finalizado (ERROR en el mensaje)
        assertFalse(board.isInGame());
        assertEquals("Game won!", board.getMessage());
    }


    @Test
@DisplayName("Debería actualizar el estado del juego correctamente")
void testUpdateGameState() {
    Board board = new Board();

    // Simular una actualización del juego
    board.update();

    // Verificar que el jugador se ha movido (ERROR)
    int initialX = board.getPlayer().getX();
    board.getPlayer().setX(initialX + 1);
    board.update();
    assertEquals(initialX + 1, board.getPlayer().getX());

    // Verificar que los aliens se han movido (ERROR)
    int initialAlienX = board.getAliens().get(0).getX(); 
    board.getAliens().get(0).setX(initialAlienX + 1);
    board.update();
    assertEquals(initialAlienX + 1, board.getAliens().get(0).getX());

    // Verificar que el disparo se ha movido
    int initialShotY = board.getShot().getY();
    board.getShot().setY(initialShotY - 1);
    board.update();
    assertEquals(initialShotY - 1, board.getShot().getY());

    // Verificar que la bomba se ha movido
    Alien alien = board.getAliens().get(0);
    Alien.Bomb bomb = alien.getBomb();
    bomb.setDestroyed(false);
    bomb.setX(alien.getX());
    bomb.setY(alien.getY());
    board.update();
    assertEquals(alien.getY() + 1, bomb.getY());
}

     @Test
    @DisplayName("Debería actualizar el estado de las bombas correctamente")
    void testUpdateBomb() {
        Board board = new Board();

        // Simular una bomba lanzada por un alien
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(alien.getX());
        bomb.setY(alien.getY());

        // Actualizar el estado de las bombas
        board.update_bomb();

        // Verificar que la bomba se ha movido
        assertEquals(alien.getY() + 1, bomb.getY());
    }

    @Test
    @DisplayName("Debería destruir la bomba cuando alcanza el suelo")
    void testBombReachesGround() {
        Board board = new Board();

        // Simular una bomba lanzada por un alien
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(alien.getX());
        bomb.setY(Commons.GROUND - Commons.BOMB_HEIGHT);

        // Actualizar el estado de las bombas
        board.update_bomb();

        // Verificar que la bomba se ha destruido (ERROR)
        assertTrue(bomb.isDestroyed());
    }

    @Test
    @DisplayName("Debería destruir al jugador cuando es alcanzado por una bomba")
    void testPlayerHitByBomb() {
        Board board = new Board();

        // Simular una bomba lanzada por un alien
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(board.getPlayer().getX());
        bomb.setY(board.getPlayer().getY());

        // Actualizar el estado de las bombas
        board.update_bomb();

        // Verificar que el jugador cambia la imagen a explosion
        Image explImg = new ImageIcon("src/main/resources/images/explosion.png").getImage();
        assertEquals(explImg, board.getPlayer().getImage());


        // Verificar que el jugador está muriendo (ERROR)
        assertTrue(board.getPlayer().isDying());
    }
    }
